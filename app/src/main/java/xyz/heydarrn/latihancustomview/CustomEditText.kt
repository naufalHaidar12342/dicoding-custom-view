package xyz.heydarrn.latihancustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class CustomEditText :AppCompatEditText, View.OnTouchListener {

    lateinit var removeTextIcon:Drawable

    fun initializeEditText(){
        removeTextIcon=ContextCompat.getDrawable(context,R.drawable.ic_baseline_close_24) as Drawable
        setOnTouchListener(this)
    }

    override fun addTextChangedListener(watcher: TextWatcher?) {
        super.addTextChangedListener(watcher)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }

    fun afterTextChanged(s:Editable){

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (compoundDrawables[2]!=null){
            val removeTextButtonStart:Float
            val removeTextButtonEnd:Float

            var isRemoveTextBtnClicked=false
            when(layoutDirection){
                View.LAYOUT_DIRECTION_RTL ->{
                    removeTextButtonEnd=(removeTextIcon.intrinsicWidth +paddingStart).toFloat()
                    when{
                        event!!.x<removeTextButtonEnd -> isRemoveTextBtnClicked=true
                    }
                }
                else ->{
                    removeTextButtonStart=(width-paddingEnd-removeTextIcon.intrinsicWidth).toFloat()
                    when{
                        event!!.x>removeTextButtonStart ->isRemoveTextBtnClicked=true
                    }
                }
            }

            when{
                isRemoveTextBtnClicked ->when{

                    event!!.action == MotionEvent.ACTION_DOWN ->{
                        removeTextIcon=ContextCompat.getDrawable(context,R.drawable.ic_baseline_close_24) as Drawable
                        showRemoveTextButton()
                        return true
                    }

                    event.action==MotionEvent.ACTION_UP ->{
                        removeTextIcon=ContextCompat.getDrawable(context,R.drawable.ic_baseline_close_24) as Drawable
                        when{
                            text!=null -> text?.clear()
                        }
                        hideRemoveTextButton()
                        return true
                    }
                }
                else -> return false
            }

        }else{
            return false
        }

        return false
    }

    fun showRemoveTextButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,removeTextIcon,null)
    }

    fun hideRemoveTextButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
    }

    constructor(context: Context) : super(context){
        initializeEditText()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initializeEditText()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initializeEditText()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint="Masukkan nama kamu!"
        textAlignment=View.TEXT_ALIGNMENT_VIEW_START
    }
}