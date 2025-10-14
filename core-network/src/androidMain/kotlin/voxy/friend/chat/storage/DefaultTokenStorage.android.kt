package voxy.friend.chat.storage

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.core.content.edit
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

actual class SecureStorage(context: Context) {

    companion object {
        private const val PREFS_NAME = "voxy_secure_prefs"
        private const val KEY_ALIAS = "VoxySecretKey"
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val GCM_TAG_LENGTH = 128
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    private val keyStore: KeyStore by lazy {
        KeyStore.getInstance(ANDROID_KEYSTORE).apply {
            load(null)
        }
    }

    private fun getOrCreateSecretKey(): SecretKey {
        // Check if key exists
        if (keyStore.containsAlias(KEY_ALIAS)) {
            return keyStore.getKey(KEY_ALIAS, null) as SecretKey
        }

        // Create new key
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            ANDROID_KEYSTORE
        )

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    private fun encrypt(value: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getOrCreateSecretKey())

        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(value.toByteArray(Charsets.UTF_8))

        // Combine IV and encrypted data
        val combined = iv + encryptedBytes
        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    private fun decrypt(encryptedValue: String): String {
        val combined = Base64.decode(encryptedValue, Base64.NO_WRAP)

        // Extract IV (first 12 bytes for GCM)
        val iv = combined.copyOfRange(0, 12)
        val encryptedBytes = combined.copyOfRange(12, combined.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        cipher.init(Cipher.DECRYPT_MODE, getOrCreateSecretKey(), spec)

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }

    actual fun save(key: String, value: String) {
        try {
            val encrypted = encrypt(value)
            sharedPreferences.edit { putString(key, encrypted) }
        } catch (e: Exception) {
            // Fallback to unencrypted if encryption fails
            sharedPreferences.edit { putString(key, value) }
        }
    }

    actual fun get(key: String): String? {
        val storedValue = sharedPreferences.getString(key, null) ?: return null
        return try {
            decrypt(storedValue)
        } catch (e: Exception) {
            // If decryption fails, return stored value (might be unencrypted)
            storedValue
        }
    }

    actual fun remove(key: String) {
        sharedPreferences.edit { remove(key) }
    }

    actual fun clear() {
        sharedPreferences.edit { clear() }
    }
}