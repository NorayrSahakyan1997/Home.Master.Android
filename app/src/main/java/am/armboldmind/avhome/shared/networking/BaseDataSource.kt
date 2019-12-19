package am.armboldmind.avhome.shared.networking

import am.armboldmind.avhome.AvHome
import am.armboldmind.avhome.R
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseDataSource {

    @Suppress("UNREACHABLE_CODE")
    protected suspend fun <T> getResult(call: suspend () -> Response<ResponseModel<T>>): ResponseResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body =  response.body()
                return if(  body != null && body.getSuccess()) ResponseResult.success(response.body()!!.getData())
                else {
                    val message:String = if (response.body() != null) response.body()!!.getMessage().toString()
                    else  AvHome.instance.resources.getString(R.string.default_error_message)
                    error(throw Exception(message))
                }

            }
            return error(throw HttpException(response))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(response: Throwable): ResponseResult<T> {
        return ResponseResult.error(response)
    }
}
