package com.example.data

import com.example.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImpl(
    db: CoroutineDatabase
) : MessageDataSource {

    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessages(): List<Message> {
        return messages.find()
            .descendingSort(Message::timeStamp)
            .toList()
    }

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }
}