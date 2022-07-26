package com.aulmrd.github

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private val pref by lazy { PrefHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.title = "Setting"

        switch_dark.isChecked = pref.getBoolean("pref_is_dark_mode")

        switch_dark.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    pref.put("pref_is_dark_mode", true)
                    Log.d("theme", "current toggle ${pref.getBoolean("pref_is_dark_mode")}")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false -> {
                    pref.put("pref_is_dark_mode", false)
                    Log.d("theme", "current toggle ${pref.getBoolean("pref_is_dark_mode")}")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

    }
}