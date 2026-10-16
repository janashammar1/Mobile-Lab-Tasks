package sa.edu.iau.cshj.studyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

// P1.3 - UI State
data class PlannerUiState(
    val greeting: String = "Hello, Jana!",
    val totalTasks: Int = 7,
    val completedTasks: Int = 0
)

// P1.4 - Stateless Home Screen
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onMarkDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
                if (uiState.totalTasks > 0)
                    uiState.completedTasks.toFloat() / uiState.totalTasks
                else 0f
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onMarkDone,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text("Mark Task as Done")
        }
    }
}

// P1.5 - Preview 1: Nothing Completed
@Preview(showBackground = true, name = "Nothing Done")
@Composable
private fun NothingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 0
            ),
            onMarkDone = {}
        )
    }
}

// P1.5 - Preview 2: Everything Completed
@Preview(showBackground = true, name = "Everything Done")
@Composable
private fun EverythingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 7
            ),
            onMarkDone = {}
        )
    }
}

// P1.6 - Route Owns the State
@Composable
fun PlannerHomeRoute() {
    var uiState by remember {
        mutableStateOf(PlannerUiState())
    }

    PlannerHomeScreen(
        uiState = uiState,
        onMarkDone = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        }
    )
}

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StudyPlannerTheme {
                PlannerHomeRoute()
            }
        }
    }
}
