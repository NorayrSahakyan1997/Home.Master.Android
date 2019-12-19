package am.armboldmind.avhome.shared.networking

import am.armboldmind.avhome.shared.configurations.AppConstants
import am.armboldmind.avhome.shared.managers.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = PreferencesManager.getString(AppConstants.TOKEN)

        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
        token?.let {
            request.addHeader("Authorization", it)
        }

        return chain.proceed(request.build())
    }
}
