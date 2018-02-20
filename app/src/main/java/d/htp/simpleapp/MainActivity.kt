package d.htp.simpleapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate called.")

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        Handler().postDelayed(({ this.runActivity() }), 5000)
    }

    private fun runActivity() {
        Log.i(TAG, "before startActivity clock time in milli-seconds: " + SystemClock.uptimeMillis())
        val fsIntent = Intent(this@MainActivity, AnotherActivity::class.java)
        startActivity(fsIntent)
//        finish()
    }

}
