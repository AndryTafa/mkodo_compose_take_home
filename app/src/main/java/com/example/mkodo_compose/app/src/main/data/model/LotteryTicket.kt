package com.example.mkodo_compose.app.src.main.data.model

data class LotteryTicket(
    val numbers: List<Int>,
    val bonusBall: Int
) {
    companion object {
        fun generateRandomTicket(): LotteryTicket {
            val numbers = (1..59).shuffled().take(6).sorted()
            val bonusBall = (1..59).filterNot { it in numbers }.random()
            return LotteryTicket(numbers, bonusBall)
        }
    }

    fun isWinningTicket(draw: LotteryDraw): Boolean {
        val drawNumbers = listOf(draw.number1, draw.number2, draw.number3, draw.number4, draw.number5, draw.number6).map { it.toInt() }
        val matchingNumbers = numbers.count { it in drawNumbers }
        val bonusBallMatches = bonusBall == draw.bonusBall.toInt()
        return matchingNumbers == 6 || (matchingNumbers == 5 && bonusBallMatches)
    }
}