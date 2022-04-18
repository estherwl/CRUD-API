package com.example.demo.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class MessageEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var date: String,
    var text: String
) {
    constructor() : this(0, "", "")
}