package com.codialstudent.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.dialogs.databinding.ActivityMainBinding
import com.codialstudent.dialogs.databinding.ItemDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.sql.Time

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.alert.setOnClickListener(this)
        binding.custom.setOnClickListener(this)
        binding.fragment.setOnClickListener(this)
        binding.datePicker.setOnClickListener(this)
        binding.timePicker.setOnClickListener(this)
        binding.bottomSheet.setOnClickListener(this)
        binding.snackbar.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val alertDialog = AlertDialog.Builder(this)
        when (p0!!.id) {
            R.id.alert -> {
                alertDialog.setTitle("Hey")
                    .setMessage("Do you really want to exit?")
                    .setPositiveButton("yes") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("no") { _, _ ->

                    }
                    .show()
            }
            R.id.custom -> {
                val customDialog = AlertDialog.Builder(this).create()
                customDialog.setCancelable(false)
                val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                itemDialogBinding.btnYes.setOnClickListener {
                    customDialog.cancel()
                    finish()
                }
                itemDialogBinding.btnNo.setOnClickListener {
                    customDialog.cancel()
                }
                customDialog.setView(itemDialogBinding.root)
                customDialog.show()

            }
            R.id.fragment -> {
                val myDialogFragment = MyDialogFragment()

                myDialogFragment.show(supportFragmentManager, "")

            }
            R.id.date_picker -> {
               DatePickerDialog(this,
                   { _, p1, p2, p3 -> Toast.makeText(this@MainActivity, "$p1/${p2+1}/$p3", Toast.LENGTH_SHORT).show() }, 2022, 11, 26).show()
            }
            R.id.time_picker ->{
                TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        Toast.makeText(this@MainActivity, "$p1:$p2", Toast.LENGTH_SHORT).show()
                    }
                },12,0,true).show()
            }
            R.id.bottom_sheet ->{
                val bottomSheetDialog = BottomSheetDialog(this)
                bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.bottom_dialog, binding.root, false))
                bottomSheetDialog.show()
            }
            R.id.snackbar ->{
                Snackbar.make(binding.root,"This is Snackbar",Snackbar.LENGTH_SHORT)
                    .setAction("Got it",object: View.OnClickListener{
                        override fun onClick(p0: View?) {
                            Toast.makeText(this@MainActivity, "Snackbar", Toast.LENGTH_SHORT).show()
                        }
                    }).show()
            }
        }
    }
}