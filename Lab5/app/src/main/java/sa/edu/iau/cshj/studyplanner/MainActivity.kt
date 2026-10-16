
package sa.edu.iau.cshj.studyplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

// P2.3 - Navigation Routes
@Serializable
object PlannerHome

@Serializable
data class TaskDetail(val title: String)

// P2.4 - UI State
data class PlannerUiState(
    val greeting: String = "Hello, Jana!",
    val totalTasks: Int = 7,
    val completedTasks: Int = 0,
    val tasks: List<String> = emptyList()
)

// P2.4 - Stateless Home Screen
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onMarkDone: () -> Unit,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Your Study Progress",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "${uiState.completedTasks} of ${uiState.totalTasks} done"
        )

        LinearProgressIndicator(
            progress = {
                if (uiState.totalTasks > 0) {
                    uiState.completedTasks.toFloat() / uiState.totalTasks
                } else {
                    0f
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onMarkDone,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text("Mark Task as Done")
        }

        HorizontalDivider()

        Text(
            text = "My Study Tasks",
            style = MaterialTheme.typography.titleLarge
        )

        uiState.tasks.forEach { task ->
            OutlinedButton(
                onClick = { onTaskClick(task) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(task)
            }
        }
    }
}

// P2.5 - Detail UI State
data class TaskDetailUiState(
    val title: String = ""
)

// P2.5 - Stateless Detail Screen
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = uiState.title,
            style = MaterialTheme.typography.titleLarge
        )

        Button(onClick = onShareClick) {
            Text("Share")
        }

        OutlinedButton(onClick = onBackClick) {
            Text("Back")
        }
    }
}

// P2.6 - Share Intent in Route
@Composable
fun TaskDetailRoute(
    title: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    TaskDetailScreen(
        uiState = TaskDetailUiState(title = title),
        onBackClick = onBackClick,
        onShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Today I'm studying: $title"
                )
            }

            context.startActivity(
                Intent.createChooser(shareIntent, "Share Task")
            )
        }
    )
}

// P2.7 - Navigation Host
@Composable
fun StudyPlannerApp() {
    val navController = rememberNavController()

    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                tasks = listOf(
                    "Complete Lab 5",
                    "Review Kotlin",
                    "Study Navigation Compose"
                )
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = PlannerHome
    ) {
        composable<PlannerHome> {
            PlannerHomeScreen(
                uiState = uiState,
                onMarkDone = {
                    if (uiState.completedTasks < uiState.totalTasks) {
                        uiState = uiState.copy(
                            completedTasks = uiState.completedTasks + 1
                        )
                    }
                },
                onTaskClick = { title ->
                    navController.navigate(TaskDetail(title))
                }
            )
        }

        composable<TaskDetail> { backStackEntry ->
            val task = backStackEntry.toRoute<TaskDetail>()

            TaskDetailRoute(
                title = task.title,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Main Activity - Single Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StudyPlannerTheme {
                StudyPlannerApp()
            }
        }
    }
}

// P2.5 - Detail Preview 1
@Preview(showBackground = true, name = "Task Detail 1")
@Composable
private fun TaskDetailPreview1() {
    StudyPlannerTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState(
                title = "Complete Lab 5"
            ),
            onShareClick = {},
            onBackClick = {}
        )
    }
}

// P2.5 - Detail Preview 2
@Preview(showBackground = true, name = "Task Detail 2")
@Composable
private fun TaskDetailPreview2() {
    StudyPlannerTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState(
                title = "Review Kotlin"
            ),
            onShareClick = {},
            onBackClick = {}
        )
    }
}

// Home Preview - Nothing Done
@Preview(showBackground = true, name = "Nothing Done")
@Composable
private fun NothingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 0,
                tasks = listOf(
                    "Complete Lab 5",
                    "Review Kotlin",
                    "Study Navigation Compose"
                )
            ),
            onMarkDone = {},
            onTaskClick = {}
        )
    }
}

// Home Preview - Everything Done
@Preview(showBackground = true, name = "Everything Done")
@Composable
private fun EverythingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 7,
                tasks = listOf(
                    "Complete Lab 5",
                    "Review Kotlin",
                    "Study Navigation Compose"
                )
            ),
            onMarkDone = {},
            onTaskClick = {}
        )
    }
}
