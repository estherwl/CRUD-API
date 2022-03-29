package com.example.demo.repository

import com.example.demo.entities.Message
import org.springframework.data.jpa.repository.JpaRepository

//1) conectar api em banco de dados h2
//2)criar a interface repository que devera substituir a lista estatica de mensagens
interface MessageRepository: JpaRepository<Message, Int> {

}