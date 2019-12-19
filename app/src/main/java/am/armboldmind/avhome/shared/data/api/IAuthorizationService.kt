package am.armboldmind.avhome.shared.data.api
import am.armboldmind.avhome.model.PhoneNumberModel
import am.armboldmind.avhome.model.VerificationCodeModel
import am.armboldmind.avhome.shared.networking.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthorizationService {
    @POST("api/auth/driver")
    suspend fun getVerificationCodeAsync(@Body phoneModel: PhoneNumberModel): Response<ResponseModel<VerificationCodeModel>>

//    @POST("auth/manager")
//    suspend fun getVerificationCodeAsync(@Body phoneModel: PhoneNumberModel): Response<ResponseModel<VerificationCodeModel>>
//    @POST("auth/resend")
//    suspend fun resendVerificationCodeAsync(@Body phoneModel: PhoneNumberModel): Response<ResponseModel<VerificationCodeModel>>
//    @POST("auth/verify")
//    suspend fun verificationRequest(@Body createAccountModel: CreateAccountModel): Response<ResponseModel<VerificationModel>>
}