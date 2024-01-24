package com.example.hashcalculator.hashAlgorithm

data class AlgorithmData(
    val name: String,
    val isAvailable: Boolean = true
)

val  algorithmList = listOf(
    AlgorithmData(name = "MD5"),
    AlgorithmData("SHA1"),
    AlgorithmData("SHA256"),
    AlgorithmData("SHA512")
)