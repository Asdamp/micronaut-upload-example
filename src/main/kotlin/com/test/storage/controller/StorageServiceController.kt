package com.test.storage.controller


import com.test.storage.service.StorageService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.util.*
import kotlin.io.path.Path

@Controller("/storage-service")
@ExecuteOn(TaskExecutors.IO)
class StorageServiceController(private val storageService: StorageService) {

    @Operation(
        summary = "Upload a file",
        description = "Upload a file to the storage service",
        requestBody = RequestBody(
            description = "File and data useful for the upload.",
            required = true,
            content = [
                Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA,
                    schema = Schema(
                        description = "The file to upload",
                        type = "object",
                        format = "file",
                        required = true
                    )
                )
            ]
        )

    )
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Post("/storage")
    fun uploadFile(
        @Parameter(description = "File to upload.") file: StreamingFileUpload,
        @Parameter(description = "Version of the file. E.g. 1.0.0") version: String,
        @Parameter(description = "Destination path of the file. E.g. /firmware/JT22/") destinationPath: String
    ): String {
        val clearedPath = destinationPath.removePrefix("/").removeSuffix("/")
        val key = if (clearedPath.isEmpty())
            file.filename
        else
            "$clearedPath/${file.filename ?: UUID.randomUUID().toString()}"
        return "Created ${storageService.storeObject(file)}"
    }


}