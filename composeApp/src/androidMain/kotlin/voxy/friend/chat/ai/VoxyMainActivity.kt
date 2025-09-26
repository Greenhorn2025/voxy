package voxy.friend.chat.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import voxy.friend.chat.ai.phonehint.AndroidPhoneHintDataSource
import voxy.friend.chat.ai.phonehint.PhoneHintCallback

class VoxyMainActivity : ComponentActivity() {

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
        ActivityContextProvider.setActivity(this)
        AndroidPhoneHintDataSource.getInstance()
        AndroidPhoneHintDataSource.setActivityResultLauncher(phoneHintLauncher)

        setContent {
            MainViewApp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityContextProvider.clear()
    }
}
