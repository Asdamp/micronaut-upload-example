package com.test.storage.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.uri.UriBuilder
import io.swagger.v3.oas.annotations.Hidden

@Controller
class HomeController {

    companion object {
        private val SWAGGER_UI = UriBuilder.of("/swagger-ui").path("index.html").build()
    }

    @Get
    @Hidden
    fun home(): HttpResponse<Any?> {
        return HttpResponse.redirect(SWAGGER_UI)
    }
}