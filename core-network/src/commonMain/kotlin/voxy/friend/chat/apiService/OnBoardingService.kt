package voxy.friend.chat.apiService

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import voxy.friend.chat.constants.ApiConstant
import voxy.friend.chat.model.basemodel.NetworkResult
import voxy.friend.chat.model.basemodel.safeRequest
import voxy.friend.chat.model.signup.SignUpResponse

class OnBoardingService(
    private val client: HttpClient
) {
    suspend fun getOnBoardingData(): NetworkResult<SignUpResponse> = safeRequest(client) {
        post(ApiConstant.SIGNUP)
    }
}