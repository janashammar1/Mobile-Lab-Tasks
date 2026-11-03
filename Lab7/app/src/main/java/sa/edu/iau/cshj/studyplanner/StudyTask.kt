package sa.edu.iau.cshj.studyplanner

data class StudyTask(
    val id: Int,
    val title: String,
    val course: String,
    val isDone: Boolean = false
)