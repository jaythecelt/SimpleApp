package d.htp.simpleapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.WindowManager

class AnotherActivity : AppCompatActivity() {
    private val TAG = "AnotherActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mark = SystemClock.uptimeMillis()
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_another)
        var mark2 = SystemClock.uptimeMillis() - mark
        Log.i(TAG, "=========== onCreate call to setContentView took: ${mark2/1000.0} sec ========")
    }
}
