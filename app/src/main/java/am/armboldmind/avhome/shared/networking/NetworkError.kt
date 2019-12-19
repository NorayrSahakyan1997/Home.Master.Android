package am.armboldmind.avhome.shared.networking

import am.armboldmind.avhome.AvHome
import am.armboldmind.avhome.shared.managers.PreferencesManager
import am.armboldmind.avhome.view.AuthorizationActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException


class NetworkError {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context = AvHome.instance.applicationContext

        fun isServerError(error: Throwable): Boolean {
            return isAuthFailure(error) || error is SocketTimeoutException
        }

        fun isNetworkError(error: Throwable): Boolean {
            return isAuthFailure(error) || error is IOException
        }

        private fun isAuthFailure(error: Throwable): Boolean {
            return if (error is HttpException) {
                if (error.code() == HttpURLConnection.HTTP_UNAUTHORIZED || error.code() == HttpURLConnection.HTTP_FORBIDDEN) {
                    PreferencesManager.clear()
                    val intent = Intent(mContext, AuthorizationActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    mContext.startActivity(intent)
                    true
                } else
                    false
            } else
                false
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}