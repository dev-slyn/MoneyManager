package com.example.moneymanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    lateinit var monthYearText: TextView
    lateinit var selectedDate: LocalDate
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthYearText = findViewById(R.id.monthYearText)
        recyclerView = findViewById(R.id.recyclerView)


        val prevBtn: Button = findViewById(R.id.preBtn)
        val nextBtn: Button = findViewById(R.id.nextBtn)

        selectedDate = LocalDate.now()

        setMonthView()

        prevBtn.setOnClickListener{
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }
        nextBtn.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }


    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")
        return date.format(formatter)

    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val dayList : ArrayList<String> = daysInMonthArray(selectedDate)
        val adapter = CalendarAdapter(dayList)
        val manager : RecyclerView.LayoutManager = GridLayoutManager(applicationContext,7)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String>{
        val dayList : ArrayList<String> = arrayListOf()
        val yearMonth : YearMonth = YearMonth.from(date)

        val lastDay = yearMonth.lengthOfMonth()
        val firstDay : LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstDay.dayOfWeek.value

        for (i in 1..41 step 1){
            if(i<=dayOfWeek || i > lastDay + dayOfWeek)
                dayList.add("")
            else
                dayList.add((i-dayOfWeek).toString())
        }

        return dayList
    }
}

