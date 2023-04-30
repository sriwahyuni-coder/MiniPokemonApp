package com.sriwahyuni.minipokemonapp.utils

import com.google.gson.*
import com.google.gson.JsonDeserializer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

object Helpers {

    fun getDefaultGson(): Gson? {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS 'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
                val formatServer =
                    SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS 'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                val formatServer = SimpleDateFormat("", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null) {
                    JsonPrimitive(formatServer.format(src))
                } else {
                    null
                }
            }).create()

    }

    fun subscribeOnBackground(function: () -> Unit) {
        Single.fromCallable {
            function()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getImageUrl(url: String): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}