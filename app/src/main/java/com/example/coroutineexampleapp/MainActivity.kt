package com.example.coroutineexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            setNewText("Click!")

            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }

    private fun setNewText(input: String){
        val newText = text.text.toString() + "\n$input"
        text.text = newText
    }

    private suspend fun fakeApiRequest(){
        val res1 = getResultFromApi1()
        setTextOnMainThread(res1)

        val res2 = getResultFromApi2()
        setTextOnMainThread(res2)
    }

    private suspend fun getResultFromApi1(): String{
        delay(1000)
        return "Result From Api 1"
    }

    private suspend fun getResultFromApi2(): String{
        delay(1000)
        return "Result From Api 2"
    }

    suspend fun setTextOnMainThread(str: String){
        withContext(Main){
            setNewText(str)
        }
    }
}