package com.rohyme.core.presentation.appUtils.dataUtils

import java.lang.Integer.MAX_VALUE
import java.util.regex.Pattern

const val PATT_USER_NAME_AR_ENG = "^[a-zA-Z\u0621-\u0664\\s]{2,60}$"
const val PATT_USER_NAME_AR = "^[\u0621-\u0664\\s]{2,60}$"
const val PATT_USER_NAME_ENG = "^[a-zA-Z\\s]{2,60}$"
const val PATT_MAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
const val PATT_SAUDI_PHONE = "^(009665|05|5)([503649187])([0-9]{7})$"
const val PATT_PASSWORD = "^[a-zA-Z0-9._!@#$%^&*-+=]{5,30}$"

/**
 *
 * user Name Validation
 * Language : Arabic , English
 * length = 2:60 letter
 */
fun String.isValidUserName() = matches(PATT_USER_NAME_AR_ENG.toRegex())

/**
 *
 * user Name Validation
 * Language : Arabic
 * length = 2:60 letter
 */
fun String.isValidArabicUserName() = matches(PATT_USER_NAME_AR.toRegex())

/**
 *
 * user Name Validation
 * Language : English
 * length = 2:60 letter
 */
fun String.isValidEnglishUserName() = matches(PATT_USER_NAME_ENG.toRegex())

/**
 *
 * text length validation
 * @param min is the minimum Integer , default value = 0
 * @param max is the maximum Integer , default value = 0x7fffffff
 */
fun String.isValidLength(min: Int = 0, max: Int = MAX_VALUE) = length in (min + 1)..(max - 1)

/**
 * mail validation
 * language :English
 * e.g : Rohyme@gmail.com
 */
fun String.isValidMail(): Boolean {
    val mailRegex = Pattern.compile(PATT_MAIL, Pattern.CASE_INSENSITIVE)
    val matcher = mailRegex.matcher(this)
    return matcher.matches()
}

/**
 * saudi phone validation
 * digits
 * start with 009665 or 05 or 5
 * length =  0 : 9
 */
fun String.isSaudiPhone(): Boolean {
    val saudiPhone = Pattern.compile(PATT_SAUDI_PHONE)
    val matcher = saudiPhone.matcher(this)
    return matcher.matches()
}

/**
 * Password validation
 * english letters and digits may have some symbols ( ._!@#$%^&*-+= )
 * length = 5 : 9
 *
 */

fun String.isValidPassword() = matches(PATT_PASSWORD.toRegex())


