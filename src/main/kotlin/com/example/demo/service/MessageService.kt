package com.example.demo.service

import com.example.demo.dto.`in`.MessageInDto
import com.example.demo.dto.out.MessageOutDto
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class MessageService(var listMessages: MutableList<MessageOutDto>) {
    private val formatterDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private val dateNow: String = LocalDateTime.now().format(formatterDate)

    init {
        listMessages = mutableListOf(
            MessageOutDto(1, dateNow, "Olá mundo!"),
            MessageOutDto(2, dateNow, "Hello world!"),
            MessageOutDto(3, dateNow, "¡Hola mundo!"),
            MessageOutDto(4, dateNow, "Salut tout le monde!")
        )
    }

    fun findAllMessages(): List<MessageOutDto> {
        return listMessages
    }

    fun findMessageById(id: Int): List<MessageOutDto> {
        return listMessages.filter { MessageDto -> MessageDto.id == id }
    }

    fun createMessage(dto: MessageInDto) {
        listMessages = listMessages.plus(
            MessageOutDto(
                id = listMessages.size + 1,
                date = dateNow,
                text = dto.text
            )
        ).toMutableList()
    }

    fun deleteAll() {
        listMessages.clear()
    }

   @Throws(ArrayIndexOutOfBoundsException::class)
   fun deleteId(id: Int) {
       if(id > listMessages.size) {
           throw ArrayIndexOutOfBoundsException("Index not found")
       }
       listMessages.removeAt(id - 1)
    }

    fun updateMessage(id: Int, dto: MessageInDto) {
        val message = findMessageById(id)
        listMessages = listMessages.minus(message).plus(MessageOutDto(
            id = id,
            date = dateNow,
            text = dto.text
        )).toMutableList()
    }
}