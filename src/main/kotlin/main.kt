package ru.netology.lesson3

fun main() {

    val seconds: Int

    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()

    if (line != null) {
        seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
            return
        } else {
            println("Прошло секунд с момента, когда человек последний раз был онлайн: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
        return
    }

    val stringToPrint = agoToText(seconds)
    println("------------------------")
    println("  был(а) $stringToPrint")
    println("------------------------")

}

fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    try {
        for (part in parts) {
            val number = part.toInt()
            result = result * 60 + number
        }
        return result
    } catch (e: NumberFormatException) {
        return -1
    }
}

fun agoToText(seconds: Int = 0) = when (seconds) {
    in 0 until 60 -> "только что"
    in 60 until 60 * 60 -> "${formOfOutputMinutes(seconds)} назад"
    in 60 * 60 until 60 * 60 * 24 -> "${formOfOutputHours(seconds)} назад"
    in 24 * 60 * 60 until 24 * 60 * 60 * 2 -> "сегодня"
    in 24 * 60 * 60 * 2 until 24 * 60 * 60 * 3 -> "вчера"
    else -> "давно"
}

fun convertToMin(seconds: Int): Int = (seconds / 60)

fun formOfOutputMinutes(seconds: Int = 0): String {
    val countMinutes = convertToMin(seconds)
    return when (countMinutes % 10) {
        1 -> "$countMinutes минуту"
        2, 3, 4 -> "$countMinutes минуты"
        else -> "$countMinutes минут"
    }
}

fun convertToHours(seconds: Int): Int = (seconds / (60 * 60))

fun formOfOutputHours(seconds: Int = 0): String {
    val countHours = convertToHours(seconds)
    return when (countHours) {
        1, 21 -> "$countHours час"
        2, 3, 4, 22, 23 -> "$countHours часа"
        else -> "$countHours часов"
    }
}