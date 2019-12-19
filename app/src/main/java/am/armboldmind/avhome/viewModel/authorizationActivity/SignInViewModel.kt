package am.armboldmind.avhome.viewModel.authorizationActivity
import am.armboldmind.avhome.model.PhoneNumberModel
import am.armboldmind.avhome.model.VerificationCodeModel
import am.armboldmind.avhome.shared.data.services.AuthorizationService
import am.armboldmind.avhome.shared.networking.ResponseResult
import am.armboldmind.avhome.viewModel.root.BaseViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch


class SignInViewModel : BaseViewModel() {

    private val repository: AuthorizationService = AuthorizationService()
    val mVerificationLiveData: LiveEvent<VerificationCodeModel> by lazy { LiveEvent<VerificationCodeModel>() }

    fun getVerificationCode(phoneNumberModel: PhoneNumberModel) {
        viewModelScope.launch {
            isLoading(true)
            val response = repository.getVerificationCode(phoneNumberModel)
            when (response.status) {
                ResponseResult.Status.SUCCESS -> mVerificationLiveData.postValue(response.data)
                ResponseResult.Status.ERROR -> errorView(response.error)
            }
            isLoading(false)
        }

    }
}