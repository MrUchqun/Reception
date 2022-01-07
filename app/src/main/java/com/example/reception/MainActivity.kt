package com.example.reception

import android.annotation.SuppressLint
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextTime: EditText
    private lateinit var button: Button
    private lateinit var containerView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        containerView = findViewById(R.id.container_view)
        editTextName = findViewById(R.id.et_name)
        editTextTime = findViewById(R.id.et_time)
        button = findViewById(R.id.btn_add_item)
        button.setOnClickListener {
            if (editTextName.text.isNotEmpty() && editTextTime.text.isNotEmpty()) {
                val item = Item(editTextName.text.toString(), editTextTime.text.toString())
                containerView.addView(addNewItem(item))
                editTextClean()
            }else{
                Snackbar.make(containerView,"Bo'sh joylarni to'ldiring",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun editTextClean() {
        editTextName.text = null
        editTextTime.text = null
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addNewItem(item: Item): LinearLayout{
        val newLinerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val params = LinearLayout.LayoutParams(-1,-2)
            params.setMargins(30,30,30,0)
            background = getDrawable(R.drawable.rounded_background)
            layoutParams = params
        }

        val nameTextView = TextView(this).apply {
            textSize = 20f
            val params = LinearLayout.LayoutParams(-1,-2)
            params.setMargins(30,30,30,0)
            layoutParams = params
            text = item.name
        }

        val timeTextView = TextView(this).apply {
            textSize = 20f
            val params = LinearLayout.LayoutParams(-1,-2)
            params.setMargins(30,0,30,30)
            layoutParams = params
            text = item.time
        }

        newLinerLayout.addView(nameTextView)
        newLinerLayout.addView(timeTextView)

        return newLinerLayout
    }
}