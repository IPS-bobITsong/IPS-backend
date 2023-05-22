package smu.it.ips2

object QuizArray {
    fun getQuiz(): ArrayList<QuizData> {
        val qList: ArrayList<QuizData> = arrayListOf()

        val q1 = QuizData(
            1,
            "탄수화물 퀴즈",
            "1. 콜레스테롤",
        "2. 탄수화물",
        "3. 칼슘",
        "4. 비타민 C",
            2
        )
        val q2 = QuizData(
            2,
            "단백질 퀴즈",
            "1. 콩",
            "2. 닭고기",
            "3. 현미",
            "4. 두부",
            2
        )
        val q3 = QuizData(
            3,
            "당류 퀴즈",
            "1. 포도당",
            "2. 자일로스",
            "3. 콜레스테롤",
            "4. 라이푸스",
            3
        )
        val q4 = QuizData(
            4,
            "건강기사 1",
            "1. 5잔",
            "2. 6잔",
            "3. 7잔",
            "4. 8잔",
            4
        )
        val q5 = QuizData(
            5,
            "건강기사 2",
            "1. 충분한 숙면 취하기",
            "2. 새로운 취미 찾기",
            "3. 체중관리를 위해 식사 거르기",
            "4. 규칙적인 운동하기",
            3
        )
        val q6 = QuizData(
            6,
            "건강기사 3",
            "1. 바른 자세 유지하기",
            "2. 낮잠 많이 자기",
            "3. 가벼운 스트레칭 하기",
            "4. 자극적인 음식 피하기",
            2
        )
        qList.add(q1)
        qList.add(q2)
        qList.add(q3)
        qList.add(q4)
        qList.add(q5)
        qList.add(q6)

        return qList
    }
}