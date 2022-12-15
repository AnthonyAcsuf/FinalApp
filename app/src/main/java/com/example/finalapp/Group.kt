package com.example.finalapp
import java.io.Serializable
import java.util.*


class Group : Serializable {


    val id: UUID = UUID.randomUUID()
    var title: String = ""
    var description: String = ""
    var date: Date = Date()
    var isFinished: Boolean = false
}
