package voxy.friend.chat.repository

import voxy.friend.chat.model.signup.SignUpResponse

interface SignUpRepository {
    suspend fun signUp(): Result<SignUpResponse>
}