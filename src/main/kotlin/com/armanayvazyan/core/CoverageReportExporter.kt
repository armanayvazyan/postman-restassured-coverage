package com.armanayvazyan.core

import com.armanayvazyan.filter.RestAssuredPostmanCollectionCoverageFilter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File

object CoverageReportExporter {

    @JvmStatic
    fun export(exportType: ExportType) {
        val config = RestAssuredPostmanCollectionCoverageFilter._config

        val map = ResultCalculator.compareAndReturnResult(
            config,
            RestAssuredPostmanCollectionCoverageFilter.listOfUrls
        )
        val root = JsonArray()
        map.keys.forEach {
            val innerObject = JsonObject()
            innerObject.addProperty("url", it.second)
            innerObject.addProperty("method", it.first)
            innerObject.add("used", Gson().toJsonTree(map[it]).asJsonArray)
            innerObject.addProperty("count", map[it]?.size ?: 0)
            root.add(innerObject)
        }
        when(exportType) {
            ExportType.JSON -> {
                val jsonString = Gson().toJson(root)
                val outputFile = File("${config.exportPath}${config.exportFilename}.json")
                outputFile.writeText(jsonString)
            }
        }
    }
}
