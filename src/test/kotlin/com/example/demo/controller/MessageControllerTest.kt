package com.example.demo.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest(
    @Autowired val mockMvc: MockMvc,
) {

    @Test
    fun findAllMessagesWithSucess() {
        mockMvc.get("/message")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun findMessageByIdWithSucess() {
        mockMvc.get("/message/1")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun postMessageWithSucess() {
        val body = StringBuilder()
        body.append("{")
        body.append("\"text\" : \"ola mundo\" ")
        body.append("}")

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/message")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun postMessageWithError() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/message")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun deleteAllWithSucess() {
        mockMvc.delete("/message/clear")
            .andExpect {
                status { isOk() }
            }
    }

}

