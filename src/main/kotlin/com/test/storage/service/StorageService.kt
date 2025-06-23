package com.test.storage.service

import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.objectstorage.ObjectStorageEntry
import io.micronaut.objectstorage.ObjectStorageOperations
import io.micronaut.objectstorage.request.UploadRequest
import jakarta.inject.Singleton
import java.io.BufferedInputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

@Singleton
class StorageService(private val objectStorage: ObjectStorageOperations<*, *, *>) {

    fun storeObject(file: StreamingFileUpload): String {

        val fileOutputStream = FileOutputStream(File("test.txt"))
        val asInputStream = file.asInputStream()
        asInputStream.transferTo(fileOutputStream)
        fileOutputStream.close()
        asInputStream.close()
        return "OK"
    }


}