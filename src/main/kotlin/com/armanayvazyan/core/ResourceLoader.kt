package com.armanayvazyan.core

import java.net.URL
import java.nio.file.InvalidPathException

object ResourceLoader {
    fun loadCollection(path: String): String {
        val resource: URL?
        try {
            resource = ResourceLoader::class.java.classLoader.getResource(path)
        } catch (e: NullPointerException) {
            throw InvalidPathException(null, "${path} for resource is invalid")
        }
        return resource.readText()
    }
}