package com.armanayvazyan.filter

import com.armanayvazyan.core.Config
import com.armanayvazyan.core.Endpoint
import io.restassured.filter.Filter
import io.restassured.filter.FilterContext
import io.restassured.response.Response
import io.restassured.specification.FilterableRequestSpecification
import io.restassured.specification.FilterableResponseSpecification

class RestAssuredPostmanCollectionCoverageFilter(config: Config) : Filter {

    companion object {
        val listOfUrls: ArrayList<Endpoint> = ArrayList()
        lateinit var _config: Config
    }

    init {
        _config = config
    }

    override fun filter(
        requestSpec: FilterableRequestSpecification,
        responseSpec: FilterableResponseSpecification,
        ctx: FilterContext
    ): Response {
        listOfUrls.add(Endpoint(requestSpec.method, requestSpec.uri))
        return ctx.next(requestSpec, responseSpec);
    }
}