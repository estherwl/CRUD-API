package com.example.demo.dto.out

import com.example.demo.dto.`in`.MessageInDto
import com.example.demo.entities.MessageEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class MessageOutDto(
    val date: String,
    val text: String
) {
    fun convertMessageOutDtoToEntity(): MessageEntity {
        return MessageEntity(0, date, text)
    }
}









