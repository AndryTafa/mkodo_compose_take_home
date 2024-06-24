package com.example.mkodo_compose.app.src.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw

@Composable
fun DrawDetailsScreen(draw: LotteryDraw) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Draw Date: ${draw.drawDate}",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Numbers: ${listOf(draw.number1, draw.number2, draw.number3, draw.number4, draw.number5, draw.number6).joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Bonus Ball: ${draw.bonusBall}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Top Prize: ${draw.topPrize}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
