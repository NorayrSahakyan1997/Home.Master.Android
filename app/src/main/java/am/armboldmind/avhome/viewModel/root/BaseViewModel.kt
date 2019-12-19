package am.armboldmind.avhome.viewModel.root

import am.armboldmind.avhome.shared.networking.NetworkError
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

open class BaseViewModel() : ViewModel() {

    private val mServerErrorLiveData by lazy { LiveEvent<Boolean>() }
    private val mNetworkErrorLiveData by lazy { LiveEvent<Boolean>() }
    private val mToastMessageLiveData by lazy { LiveEvent<String>() }
    private val mSnackBarMessageLiveData by lazy { LiveEvent<String>() }
    private val mIsLoadingLiveDada: LiveEvent<Boolean> by lazy { LiveEvent<Boolean>() }

    fun errorView(error: Throwable?) {
        error?.let {
            if (NetworkError.isServerError(error = error)) {
                error.message?.let { mServerErrorLiveData.value = true }
                return
            }
            if (NetworkError.isNetworkError(error = error)) {

                mNetworkErrorLiveData.value = true
                return
            }

            error.message?.let {
                errorToast(it)
            }
        }
    }

    private fun errorToast(errorMessage: String?) {
        mToastMessageLiveData.value = errorMessage
    }

    protected fun errorSnackBar(errorMessage: String) {
        mSnackBarMessageLiveData.value = errorMessage
    }

    protected fun isLoading(isLoading: Boolean) {
        mIsLoadingLiveDada.value = isLoading
    }

    fun getIsLoadingLiveData(): LiveEvent<Boolean> {
        return mIsLoadingLiveDada; }

    fun getServerErrorLiveData(): LiveEvent<Boolean> {
        return mServerErrorLiveData
    }

    fun getNetworkErrorLiveData(): LiveEvent<Boolean> {
        return mNetworkErrorLiveData
    }

    fun getToastMessageLiveData(): LiveEvent<String> {
        return mToastMessageLiveData
    }

    fun getSnackBarMessageLiveData(): LiveEvent<String> {
        return mSnackBarMessageLiveData
    }
}