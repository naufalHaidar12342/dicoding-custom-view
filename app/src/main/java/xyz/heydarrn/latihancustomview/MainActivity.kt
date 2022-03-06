package xyz.heydarrn.latihancustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    lateinit var tombolBuatanku:CustomButton
    lateinit var editTextBuatanku:CustomEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tombolBuatanku=findViewById(R.id.button_buatanku)
        editTextBuatanku=findViewById(R.id.edit_text_buatanku)

        setCustomButtonEnable()

        editTextBuatanku.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setCustomButtonEnable()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        tombolBuatanku.setOnClickListener {
            Toast.makeText(this@MainActivity,editTextBuatanku.text,Toast.LENGTH_SHORT).show()
        }
    }

    fun setCustomButtonEnable(){
        val result=editTextBuatanku.text
        tombolBuatanku.isEnabled=result!=null && result.toString().isNotEmpty()
    }
}