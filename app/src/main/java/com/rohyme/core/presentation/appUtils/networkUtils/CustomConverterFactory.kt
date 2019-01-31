package com.rohyme.core.presentation.appUtils.networkUtils

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type



class CustomConverterFactory(gson: Gson) : Converter.Factory() {
    companion object {
        const val SUCCESS = 200
        const val NULL_RESPONSE = 931
    }
    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        val wrappedType = object : ParameterizedType {
            override fun getRawType(): Type {
                return GenericResponse::class.java
            }

            override fun getOwnerType(): Type? {
                return null
            }

            override fun getActualTypeArguments(): Array<Type> {
                return arrayOf(type)
            }

        }
        val gsonConverter: Converter<ResponseBody, *>? = gsonConverterFactory.responseBodyConverter(wrappedType, annotations, retrofit)
        return ResponseConverter(gsonConverter as Converter<ResponseBody, GenericResponse<Any>>)

    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
}

class ResponseConverter<T>(private val converter: Converter<ResponseBody, GenericResponse<T>>) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = converter.convert(value)
        return if (response.status ==CustomConverterFactory.SUCCESS && response.body!=null) {
            response.body!!
        } else if (response.body ==null){
            throw ApiException(response.status, response.message?:"backend send empty message")
        }else{
            throw ApiException(CustomConverterFactory.NULL_RESPONSE,if (!response.message.isNullOrEmpty())response.message+""
            else {"backend send empty message with response code ${response.code}"})
        }
    }

}