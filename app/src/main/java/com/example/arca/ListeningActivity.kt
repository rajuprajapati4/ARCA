package com.example.arca

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_listening.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.UnsupportedEncodingException
import java.lang.Error
import java.lang.Exception
import java.net.URL
import java.net.URLConnection
import java.security.Timestamp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class ListeningActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    // Used for Assistant Feature
    var chatview: ChatView? = null
    private val REQ_CODE_SPEECH_INPUT = 100
    var btnSpeak: Button? = null
    var txtSpeechInput: String? = null
    var outputText: String? = null
    private var tts: TextToSpeech? = null
    val API_KEY: String = "c01c0bd1ba364b62be67b0524d039d5a"
    var com_recipeName: String? =null
    var com_ingredients: String? = null
    var com_instructions: String? = null
    var timeStamp : Long? = null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)
        AndroidThreeTen.init(this);

        com_recipeName = intent.getStringExtra("ResrecName")
        com_ingredients = intent.getStringExtra("ResRecIngredients")
        com_instructions = intent.getStringExtra("ResRecInstructions")

        Log.d("Started","Just came on the Listening Activity")
        btnSpeak = findViewById<Button>(R.id.resrec_voice)
        chatview = findViewById<ChatView>(R.id.chat_view)

        btnSpeak?.isEnabled = false
        tts = TextToSpeech(this, this)
        promptSpeechInput(chatview)

        chatview?.setOnSentMessageListener(object: ChatView.OnSentMessageListener {
            override fun sendMessage(chatMessage:ChatMessage):Boolean {
                count = 1
                txtSpeechInput = chatMessage.message
                val task = RetrieveFeedTask()
                /**Passing the user query to a new class which connects,
                 * receives and filtering operations on the data from api.ai
                 */
                task.execute(txtSpeechInput)
                return true
            }
        })
    }

    /**
     * Showing google speech input dialog
     */
    fun promptSpeechInput(view: View?) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(    //Prompting user to say something
            RecognizerIntent.EXTRA_PROMPT,
            "Say Something"
        )
        try {   //"If user said something then proceed" action
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                "Sorry! Your device doesn\\'t support speech input",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> { // if the request code sent is the same request code
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result: ArrayList<String> = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val userQuery = result[0]
                    txtSpeechInput = userQuery
                    val task = RetrieveFeedTask()
                    /**Passing the user query to a new class which connects,
                     * receives and filtering operations on the data from api.ai
                     */
                    task.execute(userQuery)
                }
            }
        }
    }

    // Create GetText Method to request the user query to api.ai agent
    @Throws(UnsupportedEncodingException::class)
    fun GetText(query: String?): String? {
        var text = ""
        var reader: BufferedReader? = null

        // Send data
        try {
            // Defined URL  where to send data
            val url = URL("https://api.api.ai/v1/query?v=20150910")

            // Send POST data request
            val conn: URLConnection = url.openConnection()
            conn.setDoOutput(true)
            conn.setDoInput(true)
            conn.setRequestProperty(
                "Authorization",
                "Bearer $API_KEY"     //Client Acces Token for the api.ai token
            )
            conn.setRequestProperty("Content-Type", "application/json")

            //Create JSONObject here
            val jsonParam = JSONObject()
            val queryArray = JSONArray()
            queryArray.put(query)
            jsonParam.put("query", queryArray)
            //            jsonParam.put("name", "order a medium pizza");
            jsonParam.put("lang", "en")
            jsonParam.put("sessionId", "1234567890")

            val wr = OutputStreamWriter(conn.getOutputStream())
            Log.d("karma", "after conversion is $jsonParam")
            wr.write(jsonParam.toString())
            wr.flush()
            Log.d("karma", "json is $jsonParam")

            // Get the server response
            reader = BufferedReader(InputStreamReader(conn.getInputStream()))
            val sb = StringBuilder()
            var line: String? = null

            // Read Server Response
            while (reader.readLine().also { line = it } != null) {
                // Append server response in string
                sb.append("""$line""".trimIndent())
            }
            text = sb.toString() //returned data from the api.ai agent

            val object1 = JSONObject(text)
            val `object` = object1.getJSONObject("result")
            var fulfillment: JSONObject? = null
            var speech: String? = null
            //            if (object.has("fulfillment")) {
            fulfillment = `object`.getJSONObject("fulfillment")
            //                if (fulfillment.has("speech")) {
            speech = fulfillment.optString("speech")
            //                }
//            }

            Log.d("karma ", "response is $text")

            speakOut(speech.toString())
            return speech // returning the value needed after filtering from the received data

        } catch (ex: Exception) {
            Log.d("karma", "exception at last $ex")
        } finally {
            try {
                reader?.close()
            } catch (ex: Exception) {
            }
        }
        return null
    }

    internal inner class RetrieveFeedTask :
        AsyncTask<String?, Void?, String?>() {
        override fun doInBackground(vararg voids: String?): String? {
            var s: String? = null
            try {
                s = GetText(voids[0])
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                Log.d("karma", "Exception occurred $e")
            }
            return s
        }

        override fun onPostExecute(s: String?) {
            super.onPostExecute(s)
            chatview = findViewById<ChatView>(R.id.chat_view)
            outputText = s
        }
    }
    // speak out the answer we got from the api.ai agent
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                btnSpeak?.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut(speakText: String) {
        this@ListeningActivity.runOnUiThread(java.lang.Runnable { //as every time it was giving error because we were performing actions on different thread
            timeStamp = System.currentTimeMillis()
            if (count != 1) {
                chatview = findViewById(R.id.chat_view)
                val userMessage = ChatMessage(txtSpeechInput, timeStamp!!, ChatMessage.Type.SENT)
                chatview?.addMessage(userMessage)
            }
            var temp: String? = null
            val sb : StringBuilder = java.lang.StringBuilder("")
            val text = speakText
            try {
                when (speakText){
                    "COMMAND-INGREDIENT" -> {
                        count = 2
                        sb.append("Ingredients for this dish are as follows\nIngredient ")
                        val ingList: List<String> = com_ingredients!!.split("]. ")
                        for (i in 0 until ingList.size) {
                            temp = ingList[i]
                            sb.append(temp.toString())
                            when(i){
                                0 -> { sb.append("\n") }
                                1 -> { sb.append("nd Ingredient").append("\n") }
                                2 -> { sb.append("rd Ingredient").append("\n") }
                                ingList.size -> {}
                            }
                            if (i>2 && i<ingList.size-1) { sb.append("th Ingredient").append("\n") }
                        }
                        outputText = sb.toString()
                        tts!!.speak(outputText, TextToSpeech.QUEUE_ADD, null, "")
                        sb.clear()
                        val arcaMessage = ChatMessage(outputText, timeStamp!!, ChatMessage.Type.RECEIVED)
                        chatview?.addMessage(arcaMessage)
                    }
                    "COMMAND-INSTRUCTION" -> {
                        count = 2
                        sb.append("Method for preparing this dish are as follows\nStep ")
                        val insList: List<String> = com_instructions!!.split("]. ")
                        for (i in 0 until insList.size) {
                            temp = insList[i]
                            sb.append(temp.toString())
                            when(i){
                                0 -> { sb.append("\n") }
                                1 -> { sb.append("nd Step").append("\n") }
                                2 -> { sb.append("rd Step").append("\n") }
                                insList.size -> {  }
                            }
                            if (i>2 && i<insList.size-1) { sb.append("th Step").append("\n") }
                        }
                        outputText = sb.toString()
                        tts!!.speak(outputText, TextToSpeech.QUEUE_ADD, null, "")
                        sb.clear()
                        val arcaMessage = ChatMessage(outputText, timeStamp!!, ChatMessage.Type.RECEIVED)
                        chatview?.addMessage(arcaMessage)
                    }
                    "COMMAND-RECIPE" -> {
                        outputText = "We are preparing $com_recipeName"
                        tts!!.speak(outputText, TextToSpeech.QUEUE_ADD, null,"")
                        val arcaMessage = ChatMessage(outputText, timeStamp!!, ChatMessage.Type.RECEIVED)
                        chatview?.addMessage(arcaMessage)
                    }
                    "COMMAND-STOP" -> {
                        count= 2
                        outputText = "Okay, stopped.\nPlease record or send me a message to start again"
                        stopNarration()
                        tts!!.speak(outputText, TextToSpeech.QUEUE_ADD, null,"")
                        val arcaMessage = ChatMessage(outputText, timeStamp!!, ChatMessage.Type.RECEIVED)
                        chatview?.addMessage(arcaMessage)
                    }
                    text -> {
                        outputText = text
                        tts!!.speak(outputText, TextToSpeech.QUEUE_ADD, null,"")
                        val arcaMessage = ChatMessage(outputText, timeStamp!!, ChatMessage.Type.RECEIVED)
                        chatview?.addMessage(arcaMessage)
                    }
                }
                TextToSpeech.QUEUE_FLUSH
                if(count!=1 && count!=2) { //when the input was using the voice
                    Timer("DelayedMic", false).schedule(4000) {
                        promptSpeechInput(chatview)
                    }
                }
                when (count) {
                    1 -> { //when the input was using text
                        count = 0
                    }
                    2 -> { //when the user asks about recipe ingredients or instructions details
                        count = 0
                    }
                }
            }catch (e: Exception){
                Log.e("bada KARMA","\n \n Exception: $e\n")
                Log.e("Data","\nData in outputText- \n $outputText \n")
            }
        })
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    fun stopNarration(){
        if (tts != null) {
            tts!!.stop()
        }
    }
}