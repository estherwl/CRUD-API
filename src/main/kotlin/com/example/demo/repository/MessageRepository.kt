package com.example.demo.repository

import com.example.demo.entities.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<MessageEntity, Int> {

}