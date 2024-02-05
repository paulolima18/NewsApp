package com.paulolima.bbc

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.paulolima.bbc.navigation.Router
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runAuth()

        setContent { Router() }
    }

    private fun runAuth() {
        val authenticationCallback: BiometricPrompt.AuthenticationCallback =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    finish()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                }
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            BiometricPrompt.Builder(this)
                .setTitle(getString(R.string.use_biometrics))
                .setSubtitle(getString(R.string.login_with_fingerprint))
                .setNegativeButton(getString(R.string.cancel), this.mainExecutor) { _: DialogInterface?, _: Int ->
                    finish()
                }
                .build()
                .authenticate(
                    CancellationSignal(), this.mainExecutor, authenticationCallback
                )
        }
    }
}