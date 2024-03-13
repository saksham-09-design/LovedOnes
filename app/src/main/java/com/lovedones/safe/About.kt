package com.lovedones.safe

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lovedones.safe.databinding.ActivityAboutBinding

class About : AppCompatActivity() {
    private val binding : ActivityAboutBinding by lazy {
        ActivityAboutBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        instagram()
        linkedin()
        website()
        gmail()
        github()
        feedback()
    }


    private fun feedback(){
        val feed = binding.feedback
        feed.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://forms.gle/9qWC5LMgh6mKUDae8"))
            startActivity(intent)
        }
    }


    private fun instagram(){
        val img = binding.igb
        img.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://www.instagram.com/saksham_loona/"))
            startActivity(intent)
        }
    }


    private fun linkedin(){
        val img = binding.linkb
        img.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://linkedin.com/in/saksham-loona-716248291/"))
            startActivity(intent)
        }
    }


    private fun website(){
        val img = binding.webb
        img.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://sakshamloona9.wixsite.com/my-site/"))
            startActivity(intent)
        }
    }


    private fun gmail(){
        val img = binding.gmailb
        img.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("mailto:sakshamloona9@gmail.com"))
            startActivity(intent)
        }
    }


    private fun github(){
        val img = binding.githubb
        img.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://github.com/saksham-09-design"))
            startActivity(intent)
        }
    }
}