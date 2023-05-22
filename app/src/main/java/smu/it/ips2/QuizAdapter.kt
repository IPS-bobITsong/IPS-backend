package smu.it.ips2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class QuizAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<Quizes>
) : ArrayAdapter<Quizes>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.activity_quiz_list, null)
        }
        val row = tempRow!!

        val data = mList[position]
        val number = row.findViewById<TextView>(R.id.quizNum)
        val name = row.findViewById<TextView>(R.id.quizName)
        number.text = "${data.quiznum}"
        name.text = "${data.quizname}"

        return row
    }

}