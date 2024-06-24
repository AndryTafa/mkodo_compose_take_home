package com.example.mkodo_compose.app.src.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw
import com.example.mkodo_compose.app.src.main.data.model.LotteryTicket
import com.example.mkodo_compose.ui.theme.Mkodo_composeTheme
import com.google.gson.Gson

@Composable
fun DrawsList(draws: List<LotteryDraw>, modifier: Modifier = Modifier, navController: NavController) {
    LazyColumn(modifier = modifier) {
        items(draws) { draw ->
            Column {
                DrawItem(draw = draw, onClick = {
                    val json = Gson().toJson(draw)
                    navController.navigate("drawDetails/$json")
                })
            }
        }

        items(draws) { draw ->
            Column {
                val ticket = remember { LotteryTicket.generateRandomTicket() }
                val isWinner = ticket.isWinningTicket(draw)
                TicketView(ticket = ticket, isWinner = isWinner)
            }
        }
    }
}

@Composable
fun DrawItem(draw: LotteryDraw, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }, // Make the Card clickable
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Draw Date: ${draw.drawDate}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Numbers: ${draw.number1}, ${draw.number2}, ${draw.number3}, ${draw.number4}, ${draw.number5}, ${draw.number6}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawItemPreview() {
    Mkodo_composeTheme {
        DrawItem(
            draw = LotteryDraw(
                id = "1",
                drawDate = "2023-06-24",
                number1 = "1",
                number2 = "2",
                number3 = "3",
                number4 = "4",
                number5 = "5",
                number6 = "6",
                bonusBall = "7",
                topPrize = 1000000L
            ),
            onClick = {} // Placeholder no-op function for onClick
        )
    }
}