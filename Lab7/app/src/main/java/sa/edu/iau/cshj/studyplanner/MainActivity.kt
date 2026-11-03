
package sa.edu.iau.cshj.studyplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

@Serializable
object PlannerHome

@Serializable
data class TaskDetail(val id: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StudyPlannerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    StudyPlannerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StudyPlannerApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PlannerHome,
        modifier = modifier
    ) {
        composable<PlannerHome> {
            PlannerHomeRoute(
                onTaskClick = { id ->
                    navController.navigate(TaskDetail(id))
                }
            )
        }

        composable<TaskDetail> { backStackEntry ->
            val destination = backStackEntry.toRoute<TaskDetail>()
            val task = sampleTasks().firstOrNull {
                it.id == destination.id
            }

            TaskDetailRoute(
                title = task?.title ?: "Task not found",
                course = task?.course ?: "",
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun TaskDetailRoute(
    title: String,
    course: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    TaskDetailScreen(
        title = title,
        course = course,
        onShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Today I'm studying: $title ($course)"
                )
            }

            context.startActivity(
                Intent.createChooser(shareIntent, "Share Task")
            )
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
fun TaskDetailScreen(
    title: String,
    course: String,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = course,
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = onShareClick) {
            Text("Share")
        }

        OutlinedButton(onClick = onBackClick) {
            Text("Back")
        }
    }
}