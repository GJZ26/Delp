package up.chiapas.delp.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import up.chiapas.delp.core.network.TokenManager

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}