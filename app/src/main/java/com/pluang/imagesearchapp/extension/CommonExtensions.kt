package com.pluang.imagesearchapp.extension
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

private const val TITLE_PATTERN = "^[\\w\\d_.-]+$"
//private const val EMAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,4}$"
private const val ADDRESS_PATTERN = ""
private const val DESCRIPTION_PATTERN = ""
private const val PHONE_PATTERN = "^(?:\\+8801|01)(?:\\d{9})$"



fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

val Int.dp: Int get() {
    return (this/Resources.getSystem().displayMetrics.density).toInt()
}

val Float.dp: Float get() {
    return (this/Resources.getSystem().displayMetrics.density)
}

val Int.sp: Int get() {
    return (this/Resources.getSystem().displayMetrics.scaledDensity).toInt()
}

val Float.sp: Float get() {
    return (this/Resources.getSystem().displayMetrics.scaledDensity)
}

val Int.px: Int get() {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

val Float.px: Float get() {
    return (this * Resources.getSystem().displayMetrics.density)
}

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

fun Long.toFormattedDate(): String{
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Dhaka"))
    val cal = Calendar.getInstance(TimeZone.getDefault())
    cal.timeInMillis = this
    val dateGMT = cal.time
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    return sdf.format(dateGMT)
}
/*========= Hide Keyboard ===============*/
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showeKeyboard() {
    view?.let { activity?.showKeypad(it) }


}

fun Fragment.keypadShow() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

public val onSoftKeyboardDonePress =
    View.OnKeyListener { v, keyCode, event ->
        if (event.keyCode === KeyEvent.KEYCODE_ENTER) {
        }
        false
    }
public val onSoftKeyboardNextPress =
    View.OnKeyListener { v, keyCode, event ->
        if (event.keyCode === KeyEvent.KEYCODE_MEDIA_NEXT) {
        }
        false
    }

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeypad(view: View) {
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}


