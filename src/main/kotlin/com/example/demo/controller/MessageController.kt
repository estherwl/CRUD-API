package com.example.demo.controller

import com.example.demo.dto.`in`.MessageInDto
import com.example.demo.entities.MessageEntity
import com.example.demo.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController(val service: MessageService) {

    @GetMapping
    fun findAllMessages(): MutableList<MessageEntity> = service.findAllMessages()

    @GetMapping("/{id}")
    fun findMessageById(@PathVariable id: Int): MessageEntity {
        return service.findMessageById(id)
    }

    @PostMapping
    fun postMessage(@RequestBody messageInDto: MessageInDto) {
        service.createMessage(messageInDto)
    }

    @DeleteMapping("/clear")
    fun deleteAll() {
        service.deleteAll()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int) {
        service.deleteId(id)
    }

    @PutMapping("/{id}")
    fun updateById(
        @RequestBody messageInDto: MessageInDto,
        @PathVariable id: Int
    ) {
        service.updateMessage(id, messageInDto)
    }

}
