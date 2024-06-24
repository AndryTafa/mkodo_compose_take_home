package com.example.mkodo_compose.app.src.main.repository

import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw

interface LotteryDrawsRepository {
    suspend fun getLotteryDraws(): List<LotteryDraw>
}