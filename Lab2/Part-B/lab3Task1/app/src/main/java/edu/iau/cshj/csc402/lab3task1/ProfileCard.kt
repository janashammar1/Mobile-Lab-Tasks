package edu.iau.cshj.csc402.lab3task1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    // TODO: Task 2 fills this in
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun StudentCardPreview() {
    StudentCard(
        student = Student(
            name = "jana-shammary",
            program = "Computer Science",
            gpa = "4.62",
            email = "2240006642@iau.edu.sa",
            city = "Dhahran, Eastern Province"
        )
    )
}