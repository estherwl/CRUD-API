package com.example.demo.dto.out

import com.example.demo.entities.MessageEntity

data class MessageOutDto(
    val id: Int,
    val date: String,
    val text: String
){
    fun convertMessageOutDto(): MessageEntity{
        return MessageEntity(id, date, text)
    }
}









