package com.tolgaerbas.chatlistadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tolgaerbas.chatlistadapter.databinding.ChatMessageReceiverItemBinding
import com.tolgaerbas.chatlistadapter.databinding.ChatMessageSenderItemBinding

class ChattingAdapter() :
    ListAdapter<ChatMessage, RecyclerView.ViewHolder>(DiffCallback) {
    companion object {

        private var listMessages = listOf<ChatMessage>()


        private val DiffCallback = object : DiffUtil.ItemCallback<ChatMessage>() {

            override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun submitList(list: List<ChatMessage>?) {
        if (list != null) {
            listMessages = list
        }
        super.submitList(list)
    }

    override fun getItemViewType(position: Int): Int {
        return when (listMessages[position].sender) {
            1 -> R.layout.chat_message_sender_item
            2 -> R.layout.chat_message_receiver_item
            else -> R.layout.chat_message_sender_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.chat_message_sender_item -> {
                ViewHolderSender(inflater.inflate(viewType, parent, false))
            }

            R.layout.chat_message_receiver_item -> {
                ViewHolderReceiver(inflater.inflate(viewType, parent, false))
            }

            else -> {
                ViewHolderReceiver(inflater.inflate(viewType, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MessageViewHolder).bindView(getItem(position))
    }

    class ViewHolderSender(itemView: View) : RecyclerView.ViewHolder(itemView), MessageViewHolder {

        private val binding = ChatMessageSenderItemBinding.bind(itemView)

        override fun bindView(item: ChatMessage) {
            binding.apply {
                tvMessage.text = item.message
                tvMessageTime.text = item.createdDate
            }
        }
    }

    class ViewHolderReceiver(itemView: View) : RecyclerView.ViewHolder(itemView),
        MessageViewHolder {

        private val binding = ChatMessageReceiverItemBinding.bind(itemView)

        override fun bindView(item: ChatMessage) {
            binding.apply {
                tvMessage.text = item.message
                tvTime.text = item.createdDate
            }
        }
    }

    interface MessageViewHolder {
        fun bindView(item: ChatMessage)
    }

}