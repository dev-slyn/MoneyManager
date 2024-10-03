package com.example.moneymanager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moneymanager.CalendarAdapter.CalendarViewHolder
import java.time.LocalDate

@SuppressLint("NotConstructor")
class CalendarAdapter(var dayList: ArrayList<String>,context: Context) : RecyclerView.Adapter<CalendarViewHolder>() {

    var onItemListener: OnItemListener? = null

    fun CalendarAdapter(dayList: ArrayList<String>, onItemListener: OnItemListener?) {
        this.dayList = dayList
        this.onItemListener = onItemListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)

        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {

        val day :String = dayList.get(position)
//        if(day==null)
//            holder.dayText.text = ""
//        else
//            holder.dayText.text = day.dayOfMonth.toString()

        holder.dayText.text = day

//        holder.dayText.text = dayList[position]
        val context = holder.dayText.context
        if((position+1)%7==0)
            holder.dayText.setTextColor(ContextCompat.getColor(context,R.color.purple_700))
        else if (position==0||position%7==0)
            holder.dayText.setTextColor(ContextCompat.getColor(context,R.color.purple_200))

        holder.itemView.setOnClickListener {
            onItemListener?.onItemClick(day, position)
            Toast.makeText(context,day,Toast.LENGTH_SHORT).show()
        }
//        holder.itemView.setOnClickListener(View.OnClickListener {
//            fun onClick(view: View){
//                val iYear = day.year
//                val iMonth = day.monthValue
//                val iDay = day.dayOfMonth
//
//                val yearMonDay: String = iYear.toString()+"년"+iMonth+"월"+iDay+"일"
//                Toast.makeText(holder.itemView.context,yearMonDay,Toast.LENGTH_SHORT)
//            }
//        })

    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    inner class CalendarViewHolder(itemView: View) : ViewHolder(itemView) {

        var dayText: TextView = itemView.findViewById(R.id.dayText)
    }
}
