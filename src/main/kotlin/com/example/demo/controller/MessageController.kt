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

    @GetMapping("/sortAsc")
    fun findAllMessagesOrderByTextAsc(): MutableList<MessageEntity> = service.findAllMessagesOrderByTextAsc()

    @GetMapping("/sortDesc")
    fun findAllMessagesOrderByTextDesc(): MutableList<MessageEntity> = service.findAllMessagesOrderByTextDesc()

    @GetMapping("/{id}")
    fun findMessageById(@PathVariable id: Int): MessageEntity {
        return service.findMessageById(id)
    }

    @PostMapping
    fun postMessage(@RequestBody messageInDto: MessageInDto) {
        service.createMessage(messageInDto)
    }

    @PostMapping("/saveAll")
    fun postAllMessages(@RequestBody listMessageInDto: Iterable<MessageInDto>) {
        service.createAllMessages(listMessageInDto)
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
