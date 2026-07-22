package edu.cit.gaane.caresync.mobile.features.authentication.network

import android.content.Context
import edu.cit.gaane.caresync.mobile.features.patients.network.PatientApi
import edu.cit.gaane.caresync.mobile.shared.storage.SessionManager
import edu.cit.gaane.caresync.mobile.features.consultation.network.ConsultationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL =
        "https://caresync-sia.onrender.com/api/"

    private lateinit var retrofit: Retrofit

    fun initialize(context: Context) {

        val sessionManager = SessionManager(context)

        val client = OkHttpClient.Builder()

            .addInterceptor(
                AuthInterceptor(sessionManager)
            )

            .build()

        retrofit = Retrofit.Builder()

            .baseUrl(BASE_URL)

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

    }

    val api: AuthenticationApi

        get() = retrofit.create(AuthenticationApi::class.java)

    val patientApi: PatientApi

        get() = retrofit.create(PatientApi::class.java)

    val consultationApi: ConsultationApi by lazy {

        retrofit.create(ConsultationApi::class.java)

    }

}