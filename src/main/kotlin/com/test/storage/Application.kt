package com.test.storage

import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "storage-service",
        description = "Storage service",
        version = "0.0"
    )
)
object Api {
}

fun main(args: Array<String>) {
    run(*args)
}

