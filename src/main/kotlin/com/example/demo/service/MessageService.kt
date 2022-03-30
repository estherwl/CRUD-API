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

    fun createMessage(dtoIn: MessageInDto) {
        repository.save(
            MessageOutDto(
                id = repository.findAll().size + 1,
                date = dateNow,
                text = dtoIn.text
            ).convertMessageOutDto()
        )
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun deleteId(id: Int) {
        repository.deleteById(id)
    }

    fun updateMessage(id: Int, dtoIn: MessageInDto) {
        var message = findMessageById(id)
        message = repository.save(
            MessageOutDto(
                id = id,
                date = dateNow,
                text = dtoIn.text
            ).convertMessageOutDto()
        )
    }
}
