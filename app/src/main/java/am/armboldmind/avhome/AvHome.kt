package am.armboldmind.avhome

import androidx.multidex.MultiDexApplication
import java.util.*

class AvHome :MultiDexApplication(){
    companion object {
        lateinit var instance: AvHome
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        creteLanguages()

    }
//    private fun creteLanguages() {
//        val supportedLocales = ArrayList<Locale>()
//        supportedLocales.add(Locale("en", "GB"))
//        LocaleChanger.initialize(applicationContext, supportedLocales)
//    }
}