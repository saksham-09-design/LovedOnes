package com.lovedones.safe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.lovedones.safe.databinding.ActivityNumberAddBinding

class NumberAdd : AppCompatActivity() {
    private val binding: ActivityNumberAddBinding by lazy {
        ActivityNumberAddBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        numberSave()
        numberClear()
    }
    private fun numberClear(){
        val cl = binding.clear
        cl.setOnLongClickListener {
            val sharedPreferences = getSharedPreferences("Number", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putLong("no1",101)
            editor.putLong("no2",101)
            editor.putLong("no3",101)
            editor.apply()
            toastShow("Numbers Removed")
            true
        }
    }
    private fun numberSave(){
        val save = binding.save
        save.setOnClickListener {
            var n1 = binding.gn1.text.toString()
            var n2 = binding.gn2.text.toString()
            var n3 = binding.gn3.text.toString()
            if(n1.contains("+91 ") || n2.contains("+91 ") || n3.contains("+91 ")
                || n1.contains(" ") || n2.contains(" ") || n3.contains(" ")
                || n1.contains("-") || n2.contains("-") || n3.contains("-")
                || n1.contains("(") || n2.contains("(") || n3.contains("(")
                || n1.contains(")") || n2.contains(")") || n3.contains(")")){
                n1 = n1.replace("+91 ","").replace(" ","").replace("-","").replace("(","").replace(")","")
                n2 = n2.replace("+91 ","").replace(" ","").replace("-","").replace("(","").replace(")","")
                n3 = n3.replace("+91 ","").replace(" ","").replace("-","").replace("(","").replace(")","")
            }
            val nm1 = n1.toLongOrNull()
            val nm2 = n2.toLongOrNull()
            val nm3 = n3.toLongOrNull()
            if (nm1 !in 1000000000..9999999999 && nm2 !in 1000000000..9999999999 && nm3 !in 1000000000..9999999999) {
                binding.gn1.text = Editable.Factory.getInstance().newEditable("")
                binding.gn2.text = Editable.Factory.getInstance().newEditable("")
                binding.gn3.text = Editable.Factory.getInstance().newEditable("")
                toastShow("Please Input a Valid Number")
            }
            else if(nm1 != null && nm2 != null && nm3 != null){
                val sharedPreference = getSharedPreferences("Number", MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putLong("no1",nm1)
                editor.putLong("no2",nm2)
                editor.putLong("no3",nm3)
                editor.apply()
                binding.gn1.text = Editable.Factory.getInstance().newEditable("")
                binding.gn2.text = Editable.Factory.getInstance().newEditable("")
                binding.gn3.text = Editable.Factory.getInstance().newEditable("")
                toastShow("Saved!")
            } else if(nm1 != null && nm2 != null && nm3 == null){
                val sharedPreference = getSharedPreferences("Number", MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putLong("no1",nm1)
                editor.putLong("no2",nm2)
                editor.putLong("no3",101)
                editor.apply()
                binding.gn1.text = Editable.Factory.getInstance().newEditable("")
                binding.gn2.text = Editable.Factory.getInstance().newEditable("")
                toastShow("Saved!")
            } else if(nm1 != null && nm2 == null && nm3 == null){
                val sharedPreference = getSharedPreferences("Number", MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putLong("no1",nm1)
                editor.putLong("no2",101)
                editor.putLong("no3",101)
                editor.apply()
                binding.gn1.text = Editable.Factory.getInstance().newEditable("")
                toastShow("Saved!")
            } else{
                binding.gn1.text = Editable.Factory.getInstance().newEditable("")
                binding.gn2.text = Editable.Factory.getInstance().newEditable("")
                binding.gn3.text = Editable.Factory.getInstance().newEditable("")
                toastShow("Please Input in Correct Order")
            }
        }
    }
    private fun toastShow(message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}