package edu.iau.cshj.csc402.lab3


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.iau.cshj.csc402.lab3.ui.theme.CSC402_Lab3Theme

data class Student(
    val name: String,
    val program: String,
    val gpa: String,
    val email: String,
    val city: String
)

@Composable
fun StudentCard(
    student: Student,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "JA",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.BottomEnd)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = student.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "${student.program} · Level 4",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Text(
                    text = student.gpa,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            HorizontalDivider()

            Text(
                text = student.email,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = student.city,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(
    name = "Light",
    showBackground = true
)
@Preview(
    name = "Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun StudentCardPreview() {
    CSC402_Lab3Theme {
        StudentCard(
            student = Student(
                name = "Jana Al-Shammari",
                program = "Computer Science",
                gpa = "4.62",
                email = "2240006642@iau.edu.sa",
                city = "Dhahran, Eastern Province"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}