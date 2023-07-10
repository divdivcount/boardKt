package com.demo.dto

data class BoardReplyAddDto(
    val replyId: Long,
    val content: String,
    val boardId: Long
)