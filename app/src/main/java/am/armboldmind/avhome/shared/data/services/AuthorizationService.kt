package am.armboldmind.avhome.shared.data.services

import am.armboldmind.avhome.BuildConfig
import am.armboldmind.avhome.model.PhoneNumberModel
import am.armboldmind.avhome.shared.data.api.IAuthorizationService
import am.armboldmind.avhome.shared.networking.BaseDataSource
import am.armboldmind.avhome.shared.networking.NetworkModule

class AuthorizationService : BaseDataSource() {
    suspend fun getVerificationCode(phoneNumberModel: PhoneNumberModel) = getResult(call = {
        NetworkModule.retrofit(BuildConfig.BASE_URL).create(IAuthorizationService::class.java)
            .getVerificationCodeAsync(phoneNumberModel)
    })
//
//    suspend fun resendVerificationCode(phoneNumberModel: PhoneNumberModel) = getResult(call = {
//        NetworkModule.retrofit(BuildConfig.BASE_URL).create(IAuthorizationService::class.java)
//            .resendVerificationCodeAsync(phoneNumberModel)
//    })
//    suspend fun verificationRequest(createAccountModel: CreateAccountModel) = getResult(call = {
//        NetworkModule.retrofit(BuildConfig.BASE_URL).create(IAuthorizationService::class.java)
//            .verificationRequest(createAccountModel)
//    })
}