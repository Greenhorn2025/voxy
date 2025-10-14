package voxy.friend.chat.apiService

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import voxy.friend.chat.constants.ApiConstant.OTP_VERIFY
import voxy.friend.chat.model.basemodel.NetworkResult
import voxy.friend.chat.model.basemodel.safeRequest
import voxy.friend.chat.model.verifyOTP.OTPVerifyResponseDTO

class OTPVerifyService(
    private val client: HttpClient
) {
    suspend fun getVerifyOTP(token: String) : NetworkResult<OTPVerifyResponseDTO> = safeRequest(client) {
        post(OTP_VERIFY){
            setBody(mapOf("token" to token))
        }
    }
}