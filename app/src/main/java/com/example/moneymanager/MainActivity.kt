package com.example.moneymanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
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


        var prevBtn: Button = findViewById(R.id.preBtn)
        var nextBtn: Button = findViewById(R.id.nextBtn)

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

    fun monthYearFromDate(date: LocalDate): String? {
        var formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")
        return date.format(formatter)

    }

    fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
    }
}

