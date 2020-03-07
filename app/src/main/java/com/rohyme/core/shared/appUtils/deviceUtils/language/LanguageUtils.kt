package com.rohyme.core.shared.appUtils.deviceUtils.language

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohyme.core.shared.appUtils.deviceUtils.SharedPreferenceUtil
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class LanguageUtils @Inject constructor(val preferenceUtils: SharedPreferenceUtil) {

    private val _currentLanguage = MutableLiveData<Languages>()
    val currentLanguage: LiveData<Languages> = _currentLanguage

    private var language = AtomicReference(Languages.ARABIC)
    set(value) {
        _currentLanguage.postValue(value.get())
        field = value
    }

    infix fun Languages.isLanguage(language: Languages) = this == language

    init {
        preferenceUtils.language.also { lang ->
            language.set(lang)
        }
    }

    fun updateLanguage(newLocality: Languages) {
        if (newLocality == language.get()) return
        language.set(newLocality)
    }

    fun invert() {
        if (language.get() == Languages.ARABIC) language.set(Languages.ENGLISH)
        if (language.get() == Languages.ENGLISH)language.set(Languages.ARABIC)
    }

    private fun persist(language: Languages) {
        preferenceUtils.language = language
    }

    fun setLocale(context: Context, language: Languages): Context {
        persist(language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language.value)
        } else updateResourcesLegacy(context, language.value)
    }

    fun attach(context: Context): Context {
        return setLocale(context, language.get())
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    @SuppressWarnings("deprecation")
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}