package smu.it.ips2

data class QuizData (
    var id: Int,
    var question: String,
    var option_one: String,
    var option_two: String,
    var option_three: String,
    var option_four: String,
    var correct_answer: Int
)