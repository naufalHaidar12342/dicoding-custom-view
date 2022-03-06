package xyz.heydarrn.latihancustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class CustomButton : AppCompatButton {
    var bg_enabled: Drawable? =null
    var bg_disabled: Drawable? =null
    var txtColor:Int=0

    fun initialize(){
        txtColor=ContextCompat.getColor(context, com.google.android.material.R.color.background_material_light)
        bg_enabled=ContextCompat.getDrawable(context,R.drawable.bg_button)
        bg_disabled=ContextCompat.getDrawable(context,R.drawable.bg_button_disabled)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background=when{
            isEnabled -> bg_enabled
            else -> bg_disabled
        }

        setTextColor(txtColor)
        textSize=16f
        gravity= Gravity.CENTER
        text=when{
            isEnabled -> "Kirim"
            else -> "Isi dulu, ya!"
        }

    }

    constructor(context: Context) : super(context) {
        initialize()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()

    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

}