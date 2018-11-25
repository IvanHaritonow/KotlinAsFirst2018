@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import lesson3.task1.resultIs
import java.lang.Math.pow
import java.lang.Math.toRadians
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = Math.sqrt(v.sumByDouble { pow(it, 2.0) })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val middle = list.sum() / list.size
    for (it in list.indices) list[it] -= middle
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double =
        (a zip b).fold(0.0) { result, (first, second) -> result + first * second }

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double = p.withIndex().fold(0.0) { result, (index, value) ->
    result + value * pow(x, index.toDouble())
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        for (i in 1 until list.size) {
            list[i] += list[i - 1]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var num = n
    var i = 2
    val list = mutableListOf<Int>()
    while (num > 1) {
        if (num % i == 0) {
            list.add(i)
            num /= i
        } else i++
    }
    return list.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var i = n
    val list = mutableListOf<Int>()
    if (i == 1) list.add(1) else {
        while (i > 0) {
            list.add(i % base)
            i /= base
        }
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun valName(a: Int): Char = when (a) {
    in 0..9 -> a.toChar()
    else -> (a + 87).toChar()
}

fun convertToString(n: Int, base: Int): String {
    var num = n
    var result = ""

    if (num == 0) result = "0" else {
        while (num > 0) {
            val i = num % base
            result += if (i < 10) i.toString() else valName(i)
            num /= base
        }
    }
    return result.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val systemCount = base.toDouble()
    var result = 0.0
    for (i in 1..digits.size) {
        result += (digits[i - 1] * Math.pow(systemCount, (digits.size - i).toDouble()))
    }
    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun valNameOf(a: String): Int {
    return when (a) {
        "a" -> 10
        "b" -> 11
        "c" -> 12
        "d" -> 13
        "e" -> 14
        "f" -> 15
        "g" -> 16
        "h" -> 17
        "i" -> 18
        "j" -> 19
        "k" -> 20
        "l" -> 21
        "m" -> 22
        "n" -> 23
        "o" -> 24
        "p" -> 25
        "q" -> 26
        "r" -> 27
        "s" -> 28
        "t" -> 29
        "u" -> 30
        "v" -> 31
        "w" -> 32
        "x" -> 33
        "y" -> 34
        "z" -> 35
        "aa" -> 36
        else -> a.toInt()
    }
}

fun decimalFromString(str: String, base: Int): Int {
    var k: Int
    val list = mutableListOf<Int>()
    for (i in 0 until str.length) {
        k = valNameOf(str[i].toString())
        list.add(k)
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var i = 0
    var num = n
    var word = ""
    val euroVal = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val romanVal = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

    while (num > 0) {
        while (num >= euroVal[i]) {
            word += romanVal[i]
            num -= euroVal[i]
        }
        i++
    }
    return word
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun russian(n: Int): String {
    val num = n
    val num2 = num / 1000
    var i = 0
    var res1 = ""
    var res2 = ""
    var res3 = ""
    var res4 = ""
    var word = ""
    val words = listOf("девятьсот", "восемьсот", "семьсот", "шестьсот", "пятьсот", "четыреста", "триста",
            "двести", "сто", "девяносто", "восемьдесят", "семьдесят", "шестьдесят", "пятьдесят", "сорок",
            "тридцать", "двадцать", "", "девять тысяч", "восемь тысяч", "семь тысяч", "шесть тысяч",
            "пять тысяч", "четыре тысячи", "три тысячи", "две тысячи", "одна тысяча", "")
    val digit = listOf(900, 800, 700, 600, 500, 400, 300, 200, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 9, 8,
            7, 6, 5, 4, 3, 2, 1, 0)

    val first = listOf("двадцать", "девятнадцать", "восемнадцать", "семнадцать", "шестнадцать", "пятнадцать",
            "четырнадцать", "тринадцать", "двенадцать", "одиннадцать", "десять")
    val second = listOf(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10)

    val words2 = listOf("девятьсот", "восемьсот", "семьсот", "шестьсот", "пятьсот", "четыреста", "триста",
            "двести", "сто", "девяносто", "восемьдесят", "семьдесят", "шестьдесят", "пятьдесят", "сорок",
            "тридцать", "двадцать", "", "девять", "восемь", "семь", "шесть",
            "пять", "четыре", "три", "два", "один", "")

    while (num2 - num2 % 100 != digit[i]) i++
    res4 += words[i]

    if (num2 % 100 !in 10..20) {
        i = 10
        if (num2 % 100 / 10 > 0) {
            while (num2 % 100 - num2 % 10 != digit[i]) i++
            word += words[i] + " "
        }
        i = 19
        while (num2 % 10 != digit[i]) i++
        word += words[i]
        res3 += word
    } else {
        i = 0
        while (num2 % 100 != second[i]) i++
        res3 += first[i] + " тысяч"
    }
    if (num2 % 100 == 0 || num2 % 10 == 0) res3 += " тысяч"

    word = ""
    i = 0

    while (num % 1000 - num % 100 != digit[i]) i++
    res2 += words2[i]

    if (num % 100 in 10..20) {
        i = 0
        while (num % 100 != second[i]) i++
        res1 += first[i]
    } else {
        i = 10
        word += if ((num % 100 > 0) || (num % 10 > 0) && num % 100 >= num % 10) {
            while ((num % 100 - num % 10) != digit[i]) i++
            words2[i] + " "
        } else {
            " "
        }
        i = 19
        while (num % 10 != digit[i]) i++
        word += words2[i]
        res1 += word
    }

    word = when {
        num.toString().length == 1 -> res1
        num.toString().length == 2 -> res1
        num.toString().length == 3 -> "$res2 $res1"
        num.toString().length == 4 -> "$res3 $res2 $res1"
        num.toString().length == 5 -> "$res3 $res2 $res1"
        num.toString().length == 6 -> "$res4 $res3 $res2 $res1"
        else -> {
            "ноль"
        }
    }

    while (word.contains("  ")) {
        val replace = word.replace("  ", " ")
        word = replace
    }

    return word.trim()
}