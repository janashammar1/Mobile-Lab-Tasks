package sa.edu.iau.cshj.studyplanner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun TaskItem(
    task: StudyTask,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task.id) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { checked ->
                onDoneChange(task.id, checked)
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = task.course,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true, name = "Task - Not Done")
@Composable
private fun TaskItemNotDonePreview() {
    StudyPlannerTheme {
        TaskItem(
            task = StudyTask(
                id = 1,
                title = "Complete Lab 7",
                course = "Mobile Apps"
            ),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Task - Done")
@Composable
private fun TaskItemDonePreview() {
    StudyPlannerTheme {
        TaskItem(
            task = StudyTask(
                id = 1,
                title = "Complete Lab 7",
                course = "Mobile Apps",
                isDone = true
            ),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

