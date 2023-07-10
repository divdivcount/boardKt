package com.demo.dto

import java.time.LocalDateTime

data class BoardDto (
    val id: Long = 0,
    var title: String = "",
    var content: String = "",
    val hit: Int = 0,
    val createTime: LocalDateTime,
)