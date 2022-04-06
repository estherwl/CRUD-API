package com.example.demo.controller

import com.example.demo.entities.MessageEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest(
    @Autowired val mockMvc: MockMvc,
) {
//TODO: criar métodos de teste que faltam pra completar todos endpoinst testados
//TODO: criar constante para ID nao encontrado

    @Test
    fun findAllMessagesWithSucess() {
        mockMvc.get("/message")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun findAllMessagesOrderedByAsc() {
        val mockList: MutableList<MessageEntity> = mutableListOf(
            MessageEntity(1, "05/04/2022", "aaa"),
            MessageEntity(2, "05/04/2022", "bbb"))

        mockMvc.perform(MockMvcRequestBuilders
            .get("/message")
            .content(mockList.toString())
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect { mockList.sortedBy { mockList -> mockList.text } }
            .andExpect(status().isOk)
    }

    @Test
    fun findAllMessagesOrderedByDsc() {
        val mockList: MutableList<MessageEntity> = mutableListOf(
            MessageEntity(1, "05/04/2022", "aaa"),
            MessageEntity(2, "05/04/2022", "bbb"))

        mockMvc.perform(MockMvcRequestBuilders
            .get("/message")
            .content(mockList.toString())
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect { mockList.sortedByDescending { mockList -> mockList.text } }
            .andExpect(status().isOk)
    }

    @Test
    fun findMessageByIdNotFound(){
        mockMvc.get("/message/{id}", 500)
            .andExpect {
                status { isNotFound() }
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
    fun postAllMessagesWithSucess() {
        val body = StringBuilder()
        body.append("[")
        body.append("{")
        body.append("\"text\" : \"ola\" ")
        body.append("}")
        body.append(",")
        body.append("{")
        body.append("\"text\" : \"oi\" ")
        body.append("}")
        body.append("]")

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/message/saveAll")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun postAllMessagesWithError() {
        val body = StringBuilder()
        body.append("{")
        body.append("\"text\" : \"ola\" ")
        body.append("}")
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/message/saveAll")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun updateByIdNotFound(){
        val body = StringBuilder()
        body.append("{")
        body.append("\"text\" : \"ola\" ")
        body.append("}")
        mockMvc.perform(MockMvcRequestBuilders
            .put("/message/{id}", 500)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(body.toString()))
            .andExpect(status().isNotFound)
    }

    @Test
    fun deleteAllWithSucess() {
        mockMvc.delete("/message/clear")
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun deleteByIdWithSucess() {
        mockMvc.delete("/message/1")
                //adc message id1
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun deleteByIdNotFound() {
        mockMvc.delete("/message/{id}", 500)
            .andExpect {
                status { isNotFound() }
            }
    }

}

