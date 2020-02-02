package ro.gabi.githubbrowser.network.interceptors

import android.util.Log
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject

object JsonBodyLoggerInterceptor : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {

        val logName = "OkHttp"
        if (!message.startsWith("{")) {
            Log.d(logName, message)
            return
        }
        try {
            val prettyPrintJson =
                JSONObject(message).toString(4)
            Log.d(logName, prettyPrintJson)
        } catch (m: JsonSyntaxException) {
            Log.d(logName, message)
        }

    }
}