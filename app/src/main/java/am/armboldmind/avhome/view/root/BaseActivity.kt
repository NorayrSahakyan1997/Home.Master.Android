package am.armboldmind.avhome.view.root

import am.armboldmind.avhome.R
import am.armboldmind.avhome.viewModel.root.BaseViewModel
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar


@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), IBaseView {



    inline fun <reified T : BaseViewModel> createViewModel(
        modelClass: Class<T>,
        view: IBaseView
    ): T {
        val viewModel = ViewModelProvider(this).get(modelClass)
        viewModel.getServerErrorLiveData().observe(this, Observer { view.showServerError() })
        viewModel.getNetworkErrorLiveData().observe(this, Observer { view.showNetworkError() })
        viewModel.getToastMessageLiveData().observe(this, Observer { text -> view.onToast(text) })
        viewModel.getSnackBarMessageLiveData()
            .observe(this, Observer { text -> view.onSnackBar(text) })
        return viewModel
    }

    override fun onToast(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.default_error_message, Toast.LENGTH_SHORT).show()
        }
    }

//    fun popUpBack() {
//        super.onBackPressed()
//        overridePendingTransition(
//            R.anim.slide_in_left,
//            R.anim.slide_out_right
//        )
//    }
//    fun loadActivity(loadActivity:BaseActivity){
//        val intent = Intent(this, loadActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(
//            R.anim.slide_in_right,
//            R.anim.slide_out_left
//        )
//    }

    override fun onSnackBar(message: String?) {
        var s: String? = message
        if (s == null) s = resources.getString(R.string.default_error_message)

        val snackbar = Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        snackbar.show()
    }

    override fun showServerError() {
        onSnackBar(resources.getString(R.string.default_error_message))
    }

    override fun showNetworkError() {
        onSnackBar(resources.getString(R.string.network_error_message))
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode!!)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //KeyboardUtil.hideSoftInput(this)
        return super.onTouchEvent(event)
    }
}