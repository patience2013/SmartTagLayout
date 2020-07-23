package com.xiong.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xiong.mylibrary.ColorTextView
import com.xiong.mylibrary.SmartTagAdapter
import com.xiong.mylibrary.SmartTagLayout

class MainActivity : AppCompatActivity() {
    private val mVals = mutableListOf<String>(
        "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
        "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
        "Android", "Weclome Hello", "Button Text", "TextView"
    )
    lateinit var smartTagLayout: SmartTagLayout<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inflater = LayoutInflater.from(this)

        smartTagLayout = findViewById(R.id.smartTaglayout)
        smartTagLayout.setAdapter(object :SmartTagAdapter<String>(mVals){
            override fun getView(
                parent: SmartTagLayout<String>,
                position: Int,
                data: String
            ): View {
                var childView =
                    inflater.inflate(R.layout.item_view, smartTagLayout, false) as ColorTextView
                childView.text = data.toString()
                return childView
            }

        })
    }
}
