package com.example.ip_test_task.common

import android.content.Context
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Samim
 * Функция для копирования базы данных
 */
fun copyDatabase(context: Context) {
    val dbFile = context.getDatabasePath("data.db")
    if (!dbFile.exists()) {
        val inputStream: InputStream = context.assets.open("data.db")
        val outputStream: OutputStream = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }
}