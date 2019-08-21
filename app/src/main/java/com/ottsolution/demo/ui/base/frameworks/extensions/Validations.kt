package com.ottsolution.demo.ui.base.frameworks.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.regex.Pattern


fun EditText.matches(editText: EditText, message: String): Boolean{
    return if(text.toString().equals(editText.text.toString())){
        true
    }else{
        alert(message)
        false
    }
}

fun EditText.containsSpecialCharacter(message:String): Boolean{
    return if(text.toString().containsSpecialChar()){
        true
    }else{
        alert(message)
        false
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

private fun String.containsSpecialChar(): Boolean{
    val p = Pattern.compile("[^a-z A-Z0-9]")
    val m = p.matcher(this)
    return m.find()
}
