package voxy.friend.chat.ai

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject
import voxy.friend.chat.ai.phonehint.AndroidPhoneHintDataSource
import voxy.friend.chat.ai.phonehint.PhoneHintCallback
import voxy.friend.chat.common.di.ActivityContextProvider
import voxy.friend.chat.otpless.OTPLessManager
import voxy.friend.chat.viewmodel.OTPLessViewModel
import voxy.friend.chat.viewmodel.OnBoardingViewModel

class VoxyMainActivity : AppCompatActivity() {

    private val contextProvider: ActivityContextProvider by inject()
    private val onBoardingViewModel by inject<OnBoardingViewModel>()
    private lateinit var otplessManager: OTPLessManager
    private val otplessViewModel: OTPLessViewModel by viewModels()

    private val phoneHintLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        PhoneHintCallback.handleActivityResult(
            resultCode = result.resultCode,
            data = result.data,
            activity = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        contextProvider.setActivity(this)
        onBoardingViewModel.checkExistingUser()
        setupOTPlessSDK()
        AndroidPhoneHintDataSource.getInstance()
        AndroidPhoneHintDataSource.setActivityResultLauncher(phoneHintLauncher)
        setContent {
            MainViewApp()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupOTPlessSDK() {
        try {
            otplessManager = OTPLessManager(contextProvider)
            otplessManager.initialize(appId = "VTN9PPA9S8CG6CSELI7E")
            otplessManager.setCallback { response ->
                otplessViewModel.handleOTPLessResponse(response)
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Failed to initialize OtplessSDK", e)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        otplessManager.handleNewIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        contextProvider.clear()
    }
}
