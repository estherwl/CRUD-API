package com.example.demo.service

import com.example.demo.dto.`in`.MessageInDto
import com.example.demo.dto.out.MessageOutDto
import com.example.demo.entities.MessageEntity
import com.example.demo.repository.MessageRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class MessageService(private val repository: MessageRepository) {
    private val formatterDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private val dateNow: String = LocalDateTime.now().format(formatterDate)

    fun findAllMessages(): MutableList<MessageEntity> {
        return repository.findAll()
    }

    fun findMessageById(id: Int): MessageEntity {
        return repository.getById(id)
    }

//    fun createMessage(dto: MessageInDto) {
//        listMessages = listMessages.plus(
//            MessageOutDto(
//                id = listMessages.size + 1,
//                date = dateNow,
//                text = dto.text
//            )
//        ).toMutableList()
//    }

    fun deleteAll() {
        repository.deleteAll()
    }

   fun deleteId(id: Int) {
       repository.deleteById(id)
   }

//    fun updateMessage(id: Int, dto: MessageInDto) {
//        val message = findMessageById(id)
//        listMessages = listMessages.minus(message).plus(MessageOutDto(
//            id = id,
//            date = dateNow,
//            text = dto.text
//        )).toMutableList()
//    }
}