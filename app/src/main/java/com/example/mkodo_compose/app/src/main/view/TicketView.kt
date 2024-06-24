package com.example.mkodo_compose.app.src.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mkodo_compose.app.src.main.data.model.LotteryTicket

@Composable
fun TicketView(ticket: LotteryTicket, isWinner: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Lottery Ticket",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                ticket.numbers.forEach { number ->
                    Text(text = number.toString(), modifier = Modifier.padding(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Bonus Ball: ${ticket.bonusBall}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isWinner) "This ticket is a winner!" else "This ticket is not a winner.",
                color = if (isWinner) Color.Green else Color.Red
            )
        }
    }
}