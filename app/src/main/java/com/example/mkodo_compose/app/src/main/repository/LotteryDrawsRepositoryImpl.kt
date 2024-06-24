package com.example.mkodo_compose.app.src.main.repository

import com.example.mkodo_compose.app.src.main.data.mockdata.LotteryDrawsData
import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LotteryDrawsRepositoryImpl : LotteryDrawsRepository {
    override suspend fun getLotteryDraws(): List<LotteryDraw> = withContext(Dispatchers.IO) {
        val jsonObject = JSONObject(LotteryDrawsData.jsonData)
        val draws = mutableListOf<LotteryDraw>()

        if (jsonObject.has("draws") && !jsonObject.isNull("draws")) {
            val drawsArray = jsonObject.getJSONArray("draws")
            for (i in 0 until drawsArray.length()) {
                val drawObject = drawsArray.getJSONObject(i)
                draws.add(
                    LotteryDraw(
                        id = drawObject.optString("id", ""),
                        drawDate = drawObject.optString("drawDate", ""),
                        number1 = drawObject.optString("number1", ""),
                        number2 = drawObject.optString("number2", ""),
                        number3 = drawObject.optString("number3", ""),
                        number4 = drawObject.optString("number4", ""),
                        number5 = drawObject.optString("number5", ""),
                        number6 = drawObject.optString("number6", ""),
                        bonusBall = drawObject.optString("bonus-ball", ""),
                        topPrize = drawObject.optLong("topPrize", 0)
                    )
                )
            }
        }
        draws
    }
}