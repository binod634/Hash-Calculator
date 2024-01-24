package com.example.hashcalculator.hashAlgorithm

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.ByteArrayOutputStream
import java.security.MessageDigest


class CalculateHashImpl {
    @OptIn(ExperimentalStdlibApi::class)
    fun fromText(str:String, algorithmData: AlgorithmData): String {
        val digest = MessageDigest.getInstance(algorithmData.name)
        return try {
            digest.update(str.toByte())
            digest.digest().toHexString(HexFormat.Default)
        } catch (e:Exception) {
            "Error"
        }

    }

    @OptIn(ExperimentalStdlibApi::class)
    fun fromFile(context: Context, uri: Uri, algorithmData: AlgorithmData): String {
        val digest = MessageDigest.getInstance(algorithmData.name)
        val inputStream = context.contentResolver.openInputStream(uri)
        if (inputStream != null) {
            try {
                val outputStream = ByteArrayOutputStream()
                inputStream.use {
                    val buffer = ByteArray(1024)
                    var buffCount: Int
                    while (inputStream.read(buffer).also { buffCount = it } != -1) {
                        outputStream.write(buffer, 0, buffCount)
                    }
                }
                digest.update(outputStream.toByteArray())
                return digest.digest().toHexString(HexFormat.Default)
            } catch (e: Exception) {
                return "Error"
            }
        } else {
            Log.e("TAG-Error","Can't Open input Steam..")
            return "Can't Read File."
        }
    }
}