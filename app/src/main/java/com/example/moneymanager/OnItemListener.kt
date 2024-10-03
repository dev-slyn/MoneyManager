package com.example.moneymanager

import android.icu.text.Transliterator.Position

interface OnItemListener {
    fun onItemClick(dayText:String,position: Int)
}