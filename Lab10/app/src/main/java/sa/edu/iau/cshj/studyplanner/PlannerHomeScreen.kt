package sa.edu.iau.cshj.studyplanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

data class PlannerUiState(
    val greeting: String = "Hello, Jana!",
    val tasks: List<StudyTask> = emptyList()
)

fun sampleTasks(): List<StudyTask> = listOf(
    StudyTask(1, "Complete Lab 7", "Mobile Apps"),
    StudyTask(2, "Review Kotlin", "Mobile Apps"),
    StudyTask(3, "Study Navigation Compose", "Mobile Apps"),
    StudyTask(4, "Review image filters", "Image Processing"),
    StudyTask(5, "Prepare project proposal", "Graduation Project")
)

// Save task values so the list survives rotation.
private val taskListSaver = listSaver<List<StudyTask>, Any>(
    save = { tasks ->
        tasks.flatMap { task ->
            listOf(task.id, task.title, task.course, task.isDone)
        }
    },
    restore = { values ->
        values.chunked(4).map { task ->
            StudyTask(
                id = task[0] as Int,
                title = task[1] as String,
                course = task[2] as String,
                isDone = task[3] as Boolean
            )
        }
    }
)

@Composable
fun PlannerHomeRoute(
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var tasks by rememberSaveable(stateSaver = taskListSaver) {
        mutableStateOf(sampleTasks())
    }

    PlannerHomeScreen(
        uiState = PlannerUiState(tasks = tasks),
        onTaskClick = onTaskClick,
        onDoneChange = { id, done ->
            tasks = tasks.map { task ->
                if (task.id == id) {
                    task.copy(isDone = done)
                } else {
                    task
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalTasks = uiState.tasks.size
    val completedTasks = uiState.tasks.count { it.isDone }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Your Study Progress",
            style = MaterialTheme.typography.titleLarge
        )

        Text("$completedTasks of $totalTasks done")

        LinearProgressIndicator(
            progress = {
                if (totalTasks > 0) {
                    completedTasks.toFloat() / totalTasks
                } else {
                    0f
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "My Study Tasks",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = uiState.tasks,
                key = { it.id }
            ) { task ->
                TaskItem(
                    task = task,
                    onTaskClick = onTaskClick,
                    onDoneChange = onDoneChange
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Home - Five Tasks")
@Composable
private fun PlannerHomeScreenPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(tasks = sampleTasks()),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}
