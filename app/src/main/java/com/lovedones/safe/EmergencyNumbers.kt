package com.lovedones.safe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lovedones.safe.databinding.ActivityEmergencyNumbersBinding


class EmergencyNumbers : AppCompatActivity() {
    private val binding: ActivityEmergencyNumbersBinding by lazy {
        ActivityEmergencyNumbersBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        police()
        ambulance()
        child()
        women()
        lpg()
        antiCrr()
        disaster()
        cyber()
    }
    private fun police(){
        val no = "100"
        val ca = binding.police
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun ambulance(){
        val no = "102"
        val ca = binding.ambulance
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun child(){
        val no = "1098"
        val ca = binding.child
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun women(){
        val no = "1091"
        val ca = binding.women
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun lpg(){
        val no = "1906"
        val ca = binding.lpg
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun antiCrr(){
        val no = "9501200200"
        val ca = binding.anticorr
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun disaster(){
        val no = "1078"
        val ca = binding.disaster
        ca.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$no"))
            startActivity(intent)
        }
    }
    private fun cyber(){
        val cyber = binding.cyber
        cyber.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://www.cybercrime.gov.in/Default.aspx"))
            startActivity(intent)
        }
    }
}