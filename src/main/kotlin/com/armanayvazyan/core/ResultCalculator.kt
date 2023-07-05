package com.armanayvazyan.core

import com.google.gson.JsonParser
import iterateOverItem

object ResultCalculator {
    fun compareAndReturnResult(
        config: Config,
        restAssuredUrls: ArrayList<Endpoint>
    ): Map<Pair<String, String>, List<Endpoint>> {
        val map = mutableMapOf<Pair<String, String>, ArrayList<Endpoint>>()
        val postmanUrls = CollectionUtils.getAllEndpointsFromGivenFile(
            JsonParser.parseString(ResourceLoader.loadCollection(config.collectionFilePath)).iterateOverItem()
        )

        val restAssuredEndpoints = restAssuredUrls
            .map { Endpoint(it.method, it.url.removeSuffix("/")) }

        val collectionEndpoints = postmanUrls.map { Endpoint(it.method, it.url.removeSuffix("/")) }

        collectionEndpoints.forEach {
            map[Pair(it.method, it.url)] = ArrayList()
        }

        restAssuredEndpoints.forEach { it ->
            val path = it.url.substringBefore('?')
            val endpoint = collectionEndpoints
                .map { i ->
                    Endpoint(i.method, i.url.replace(config.baseUrlPattern, config.baseUrl))
                }
                .findLast { item ->
                    item.url
                        .replace("\\{\\{\\w+\\}\\}".toRegex(), config.regexForId)
                        .toRegex()
                        .matches(path) && it.method == item.method
                }
            if (endpoint != null) {
                map[Pair(endpoint.method, endpoint.url.replace(config.baseUrl, config.baseUrlPattern))]?.add(it)
            }
        }
        return map;
    }

}