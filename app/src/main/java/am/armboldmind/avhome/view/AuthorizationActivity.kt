package am.armboldmind.avhome.view
import am.armboldmind.avhome.R
import am.armboldmind.avhome.view.root.BaseActivity
import android.os.Bundle
import android.util.Log

class AuthorizationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

    }
     fun binding(){
        Log.d("N_TAG","BindingWorksAsWell")
    }
}
