package com.example.demo.controller

import com.example.demo.dto.`in`.MessageInDto
import com.example.demo.dto.out.MessageOutDto
import com.example.demo.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController(val service: MessageService) {

    @GetMapping
    fun findAllMessages(): List<MessageOutDto> = service.findAllMessages()

    @GetMapping("/{id}")
    fun findMessageById(@PathVariable id: Int): List<MessageOutDto> {
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
