package sa.edu.iau.cshj.studyplanner

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskCount(count: Int) {
    Text(
        "You have $count study tasks",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun AddTaskRow(
    text: String,
    onTextChange: (String) -> Unit,
    onAdd: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            // TODO 7a — Show the received text.
            value = text,
            // TODO 7b — Pass the event on.
            onValueChange = onTextChange,
            label = { Text("New task") },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // TODO 7c — Pass the Add event on.
        Button(onClick = onAdd) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskRowPreview() {
    AddTaskRow(
        text = "Read Ch. 2",
        onTextChange = {},
        onAdd = {}
    )
}