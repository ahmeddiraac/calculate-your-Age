package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

   private var tvSelectedDate :TextView ? = null
   private var tvAgeInminutes :TextView ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvselectedDate)
        tvAgeInminutes = findViewById(R.id.tvAgeInminutes)

        btnDatePicker.setOnClickListener {
            clickDatepicker()
        }
        val reset = findViewById<Button>(R.id.myreset)
//        var tim<es = 0
        reset.setOnClickListener {
            tvSelectedDate?.text = "00.0.0000";
            tvAgeInminutes?.text = "0000000";
        }


    }
    fun clickDatepicker(){
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val Day = mycalender.get(Calendar.DAY_OF_MONTH)
       var dpd = DatePickerDialog(this, {view, Selectedyear, Selectedmonth, SelecteddayOfMonth ->

            Toast.makeText(applicationContext,
                "Year is $Selectedyear Month is ${Selectedmonth+1} Day is $SelecteddayOfMonth", Toast.LENGTH_LONG).show()

            var SelectedDate = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"
            tvSelectedDate?.text = SelectedDate

            val SDF = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = SDF.parse(SelectedDate)
           theDate?.let {
               val SelectedDateInMinute = theDate.time/60000

               val currentDate = SDF.parse(SDF.format(System.currentTimeMillis()))

               currentDate?.let {
                   var currentDateInMinutes = currentDate.time/60000

                   val DifferenceInMinutes = currentDateInMinutes -SelectedDateInMinute

                   tvAgeInminutes?.text = DifferenceInMinutes.toString()
               }

           }


        },

        year,
        month,
        Day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

}