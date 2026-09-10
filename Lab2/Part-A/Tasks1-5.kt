fun task1() {
    val university = "Imam Abdulrahman Bin Faisal University"
    val courseCode = "CSC 402"
    val creditHours = 3
    var studentsEnrolled = 28
    val isElective = false

    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    studentsEnrolled += 2

    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    println("University name length: ${university.length}")

    // courseCode = "CSC 403"
}
fun greetStudent(name: String, course: String = "CSC 402"): String {
    return "Welcome to $course, $name!"
}

fun finalMark(
    quizzes: Double,
    project: Double,
    finalExam: Double
): Double = quizzes * 0.10 + project * 0.70 + finalExam * 0.20

fun printBanner(title: String, width: Int = 40) {
    println("-".repeat(width))
    println(title)
    println("-".repeat(width))
}

fun task2() {
    printBanner("CSC 402 Lab 2")

    println(greetStudent("Sara"))
    println(greetStudent("Sara", "CSC 311"))

    val result1 = finalMark(80.0, 90.0, 70.0)

    val result2 = finalMark(
        finalExam = 70.0,
        quizzes = 80.0,
        project = 90.0
    )

    println("Positional result: $result1")
    println("Named arguments result: $result2")
}
fun letterGrade(mark: Int): String = when (mark) {
    in 95..100 -> "A+"
    in 90..94 -> "A"
    in 85..89 -> "B+"
    in 80..84 -> "B"
    in 75..79 -> "C+"
    in 70..74 -> "C"
    in 65..69 -> "D+"
    in 60..64 -> "D"
    in 0..59 -> "F"
    else -> "Invalid"
}

fun status(mark: Int): String =
    if (mark in 60..100) "Pass" else "Fail"

fun task3() {
    val marks = listOf(97, 88, 74, 61, 45, 130)

    for (mark in marks) {
        println(
            "Mark ${mark.toString().padStart(3)} -> " +
            "${letterGrade(mark).padEnd(7)} (${status(mark)})"
        )
    }
}

fun describeTeam(teamName: String?): String {
    return teamName?.let {
        "Team: $it (${it.length} characters)"
    } ?: "Team not registered yet"
}

fun task4() {
    val nickname: String? = null
    val fullName: String = "Abdullah Al-Qahtani"

    println(fullName.length)
    println(nickname?.length)

    println(nickname ?: "no nickname set")

    println(describeTeam("Team Falcon"))
    println(describeTeam(null))

    // println(nickname!!.length)
}

data class Course(
    val code: String,
    val title: String,
    val credits: Int,
    val days: String
)

fun findCourse(list: List<Course>, code: String): String {
    return list.firstOrNull { it.code == code }?.title ?: "Course not found"
}

fun task5() {

    val courses = listOf(
        Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
        Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
        Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
        Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
        Course("ENG 214", "Technical Writing", 2, "Tue")
    )

    println("All courses:")
    courses.forEach {
        println(it)
    }

    println("\nCourses with 3 credits or more:")
    courses.filter { it.credits >= 3 }.forEach {
        println(it)
    }

    val courseCodes = courses.map { it.code }
    println("\nCourse codes: $courseCodes")

    val totalCredits = courses.sumOf { it.credits }
    println("Total credits: $totalCredits")

    println("Find CSC 402: ${findCourse(courses, "CSC 402")}")
    println("Find CSC 999: ${findCourse(courses, "CSC 999")}")

    val updatedCourse = courses.first { it.code == "CSC 402" }.copy(credits = 4)

    println("Copied CSC 402: $updatedCourse")
    println("Original CSC 402: ${courses.first { it.code == "CSC 402" }}")
}

fun main() {
    task1()
    task2()
    task3()
    task4()
    task5()
}
