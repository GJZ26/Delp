package up.chiapas.delp.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import up.chiapas.delp.core.network.interceptor.AuthInterceptor
import up.chiapas.delp.core.network.interceptor.HeaderInterceptor
import up.chiapas.delp.core.network.interceptor.LoggingInterceptor
import up.chiapas.delp.post.data.source.PostService
import up.chiapas.delp.login.data.source.LoginService
import up.chiapas.delp.post.domain.FindPostUseCase
import up.chiapas.delp.register.data.source.RegisterService

object RequestHelper {
    private const val BASE_URL = "https://delp.protectify.shop/"

    private val publicClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .build()

    private val privateClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(AuthInterceptor())
        .build()

    private val publicRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(publicClient)
            .build()
    }

    private val protectedRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(privateClient)
            .build()
    }

    val registerService: RegisterService by lazy {
        publicRetrofit.create(RegisterService::class.java)
    }

    val loginService: LoginService by lazy {
        publicRetrofit.create(LoginService::class.java)
    }

    val postService: PostService by lazy{
        protectedRetrofit.create(PostService::class.java)
    }

}