package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.AndroidGreen
import edu.iau.cshj.csc402.lab2.ui.theme.Navy
import edu.iau.cshj.csc402.lab2.ui.theme.ScreenBackground

data class TeamTask(
    val id: Int,
    val title: String,
    val owner: String,
    val isDone: Boolean = false
)

@Composable
fun TaskTrackerScreen() {

    val tasks = remember {
        mutableStateListOf(
            TeamTask(
                id = 1,
                title = "Create GitHub repository",
                owner = "Fatimah",
                isDone = true
            ),
            TeamTask(
                id = 2,
                title = "Write project proposal",
                owner = "Omar",
                isDone = true
            ),
            TeamTask(
                id = 3,
                title = "Design the login screen",
                owner = "Noura"
            ),
            TeamTask(
                id = 4,
                title = "Set up Android Studio",
                owner = "Yusuf"
            )
        )
    }

    val done = tasks.count { it.isDone }
    val total = tasks.size
    val open = total - done

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Navy)
                .padding(16.dp)
        ) {
            Text(
                text = "Team Task Tracker",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Task 11 - Challenge",
                color = AndroidGreen,
                fontSize = 12.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Navy
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    SummaryItem(
                        value = done,
                        label = "Done",
                        color = AndroidGreen
                    )

                    SummaryItem(
                        value = open,
                        label = "Open",
                        color = Color.White
                    )

                    SummaryItem(
                        value = total,
                        label = "Total",
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = tasks,
                    key = { it.id }
                ) { task ->

                    TaskRow(
                        task = task,
                        onToggle = {

                            val index =
                                tasks.indexOfFirst { it.id == task.id }

                            if (index != -1) {
                                tasks[index] =
                                    task.copy(isDone = !task.isDone)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    val nextId =
                        (tasks.maxOfOrNull { it.id } ?: 0) + 1

                    tasks.add(
                        TeamTask(
                            id = nextId,
                            title = "New task",
                            owner = "Jana"
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AndroidGreen,
                    contentColor = Navy
                )
            ) {
                Text(
                    text = "+   Add task",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SummaryItem(
    value: Int,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 10.sp
        )
    }
}

@Composable
fun TaskRow(
    task: TeamTask,
    onToggle: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = task.isDone,
                onCheckedChange = {
                    onToggle()
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = AndroidGreen,
                    checkmarkColor = Navy
                )
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp)
            ) {

                Text(
                    text = task.title,
                    color =
                        if (task.isDone)
                            Color.Gray
                        else
                            Navy,
                    fontWeight = FontWeight.Medium,
                    textDecoration =
                        if (task.isDone)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None
                )

                Text(
                    text = task.owner,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTrackerScreenPreview() {
    TaskTrackerScreen()
}