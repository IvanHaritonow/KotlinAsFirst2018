@file:Suppress("UNUSED_PARAMETER", "UNREACHABLE_CODE")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0) return 1
    var i = 0
    var num = n
    if (n < 0) {
        num = abs(n)
    }
    while (num > 0) {
        i++
        num /= 10
    }
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 2) return 1
    var a = 1
    var b = 1

    for (i in 3..n) {
        val c = b
        b += a
        a = c
    }
    return b
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun nod(m: Int, n: Int): Int {
    var a = abs(m)
    var b = abs(n)
    while (a != b) {
        if (a > b) a -= b
        else b -= a
    }
    return a
}

fun lcm(m: Int, n: Int): Int {
    val c = maxOf(m, n)
    val d = minOf(m, n)
    return c / nod(m, n) * d
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var check = 0
    for (i in 2..n) {
        if (n % i == 0) {
            check = i
            break
        }
    }
    return check
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var check = 0
    for (i in 1..n) {
        if (m % i == 0 && n % i == 0) check = i
    }
    return check == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var check = 0
    for (i in 1..sqrt(n.toDouble()).toInt()) {
        if ((sqr(i) <= n) && (sqr(i) >= m)) check = i
    }
    return check != 0
}

private operator fun Boolean.compareTo(n: Int): Int {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var check = 0
    var n = x
    while (n != 1) {
        if (n % 2 == 0) n /= 2
        else n = 3 * n + 1
        check++
    }
    return check
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var a = x
    var i = 5.0
    a -= Math.pow(a, 3.0) / 3
    while (a >= eps) {
        a += Math.pow(a, i) / factorial(i.toInt())
        i += 2
        a -= Math.pow(a, i) / factorial(i.toInt())
        i += 2
    }
    return a
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var a = x
    var i = 4.0
    a -= 1 - Math.pow(a, 2.0) / factorial(2)
    while (a >= eps) {
        a += Math.pow(a, i) / factorial(i.toInt())
        i += 2
        a -= Math.pow(a, i) / factorial(i.toInt())
        i += 2
    }
    return a
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0
    var num = n

    while (num >= 1) {
        result *= 10
        result += (num % 10)
        num /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val check = n % 10
    var num = n

    while (num >= 1) {
        if (num % 10 != check)
            return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun resultIs(a: Int, b: Int, c: Int, d: Int): Int {
    var t = d
    var k = c
    for (i in 1..b - a) {
        t /= 10
        k = t % 10
    }
    if (b == a) return t % 10
    return k
}

fun squareSequenceDigit(n: Int): Int {
    val num = 0
    var length = 0
    var square = 0
    var check = 0
    while (length < n) {
        check++
        square = sqr(check)
        length += digitNumber(square)
    }
    return resultIs(n, length, num, square)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var length = 0
    var check = 0
    val num = 0
    var fibo: Int
    do {
        check++
        fibo = fib(check)
        length += digitNumber(fibo)
    } while (length < n)
    fibo = fib(check)

    return resultIs(n, length, num, fibo)
}