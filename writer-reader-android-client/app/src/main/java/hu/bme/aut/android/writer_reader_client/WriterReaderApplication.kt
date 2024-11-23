package hu.bme.aut.android.writer_reader_client

import android.app.Application
import android.util.Log
import androidx.activity.result.launch
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class WriterReaderApplication: Application() {
    companion object {
        lateinit var api: WriterReaderApi

    }

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(WriterReaderApi::class.java)

        Log.d("WriterReaderApplication", "ANYÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁÁD")


    }
}

/*

CoroutineScope(Dispatchers.IO).launch {
    try {
        val loadedWorks = api.getWorks(1, 16)
        Log.d("HomeViewModel", "Loaded works: ${loadedWorks.body()}")
    } catch (e: Exception) {
        Log.d("HomeViewModel", "Error loading works", e)
    }
}
*/


