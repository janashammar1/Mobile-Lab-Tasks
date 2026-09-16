package edu.iau.cshj.csc402.lab3

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.iau.cshj.csc402.lab3.ui.theme.CSC402_Lab3Theme

private val students = listOf(
    Student(
        name = "Jana Al-Shammari",
        program = "Computer Science",
        gpa = "4.62",
        email = "2240006642@iau.edu.sa",
        city = "Dhahran, Eastern Province"
    ),
    Student(
        name = "Fajer Alfajri",
        program = "Computer Science",
        gpa = "4.50",
        email = "fajer@iau.edu.sa",
        city = "Dammam, Eastern Province"
    ),
    Student(
        name = "Maha",
        program = "Computer Science",
        gpa = "4.40",
        email = "maha@iau.edu.sa",
        city = "Khobar, Eastern Province"
    ),
    Student(
        name = "Jawaher",
        program = "Computer Science",
        gpa = "4.35",
        email = "jawaher@iau.edu.sa",
        city = "Dammam, Eastern Province"
    ),
    Student(
        name = "Nawal",
        program = "Computer Science",
        gpa = "4.30",
        email = "nawal@iau.edu.sa",
        city = "Dhahran, Eastern Province"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("My Profile")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit profile"
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Students",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "${students.size} students",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            items(students) { student ->
                StudentCard(
                    student = student
                )
            }
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
private fun ProfileScreenPreview() {
    CSC402_Lab3Theme {
        ProfileScreen()
    }
}