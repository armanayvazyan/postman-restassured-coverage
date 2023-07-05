package com.armanayvazyan.core

class Config @JvmOverloads constructor(
    val baseUrl: String,
    val baseUrlPattern: String = "{{url}}",
    val regexForId: String = "\\\\w+",
    val collectionFilePath: String,
    val exportPath: String = "",
    val exportFilename: String = "coverage"
)