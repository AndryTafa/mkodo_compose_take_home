package com.example.mkodo_compose.app.src.main.data.model

data class LotteryDraw(
    val id: String,
    val drawDate: String,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    val bonusBall: String,
    val topPrize: Long
)
