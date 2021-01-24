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

fun agoToText(seconds: Int = 0) = when {
    ((seconds >= 0) && (seconds <= 59)) -> "только что"
    ((seconds >= 60) && (seconds <= (60 * 60 - 1))) -> "${formOfOutputMinutes(seconds)} назад"
    ((seconds >= (60 * 60)) && (seconds <= (60 * 60 * 24 - 1))) -> "${formOfOutputHours(seconds)} назад"
    ((seconds >= (24 * 60 * 60)) && (seconds <= (24 * 60 * 60 * 2 - 1))) -> "сегодня"
    ((seconds >= (24 * 60 * 60 * 2)) && (seconds <= (24 * 60 * 60 * 3 - 1))) -> "вчера"
    else -> "давно"
}

fun convertToMin(seconds: Int): Int = (seconds / 60)

fun formOfOutputMinutes(seconds: Int = 0): String {
    val countMinutes = convertToMin(seconds)
    return when {
        ((countMinutes == 1) || ((countMinutes - 1) % 10 == 0)) -> "$countMinutes минуту"
        ((countMinutes >= 2) && (countMinutes <= 4) || ((countMinutes - 2) % 10 == 0) ||
                ((countMinutes - 3) % 10 == 0) || ((countMinutes - 4) % 10 == 0)) -> "$countMinutes минуты"
        else -> "$countMinutes минут"
    }
}

fun convertToHours(seconds: Int): Int = (seconds / (60 * 60))

fun formOfOutputHours(seconds: Int = 0): String {
    val countHours = convertToHours(seconds)
    return when {
        ((countHours == 1) || ((countHours - 1) % 10 == 0)) -> "$countHours час"
        ((countHours >= 2) && (countHours <= 4) || ((countHours - 2) % 10 == 0) ||
                ((countHours - 3) % 10 == 0) || ((countHours - 4) % 10 == 0)) -> "$countHours часа"
        else -> "$countHours часов"
    }
}