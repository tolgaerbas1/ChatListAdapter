package com.tolgaerbas.chatlistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import com.tolgaerbas.chatlistadapter.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listOfMessages = listOf<ChatMessage>(
            ChatMessage(1, 1, "Hey, my name is Tolga", "21.07"),
            ChatMessage(2, 2, "Nice to meet you Tolga", "21.08"),
            ChatMessage(3, 2, "My name is Jessie", "21.08"),
            ChatMessage(1, 1, "Nice to meet you Jessie", "21.09"),
        )
        binding.apply {
            val chatAdapter = ChattingAdapter()
            rvMessages.adapter = chatAdapter
            chatAdapter.submitList(listOfMessages)
        }

    }


}

data class ChatMessage(
    val id: Int?,
    val sender: Int,
    val message: String,
    val createdDate: String
)