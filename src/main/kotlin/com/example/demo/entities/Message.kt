package com.example.demo.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Message(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val date: String,
    val text: String,
) {
    constructor() : this(0, "", "")
}
