package com.example.five2six

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.five2six.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val liveText = MutableLiveData<String>()
    var changeText : MutableLiveData<String> = MutableLiveData()
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        liveText.observe(this, Observer {
        })

        changeText.observe(this, Observer {
            index_change.text = changeText.toString()
        })

        binding.apply {
            lifecycleOwner = this@MainActivity

            activity = this@MainActivity

            btnChange.setOnClickListener {
                when (liveText.value) {
                    "Hello, DataBinding" -> {
                        liveText.value = "Hello, LiveData"
                    }
                    "Hello, LiveData" -> {
                        liveText.value = "Hello, DataBinding"
                    }
                    else -> liveText.value = "Hello, DataBinding"
                }
            }

            btn_idx.setOnClickListener{
                changeText.value = "몇 번 눌렀냐구? 이만큼! -> ${++count}"
            }



        }

    }
}