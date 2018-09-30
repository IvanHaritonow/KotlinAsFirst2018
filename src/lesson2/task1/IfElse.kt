@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int) = when {
    age in 6..19 || age % 100 in 6..19 -> "$age лет"
    age % 10 == 1 -> "$age год"
    age % 10 in 2..4 -> "$age года"
    else -> {
        "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val halfTime: Double
    val firstWay: Double = t1 * v1
    val secondWay: Double = t2 * v2
    val thirdWay: Double = t3 * v3
    val fullWay: Double = firstWay + secondWay + thirdWay
    val halfWay: Double = fullWay / 2
    halfTime = when {
        firstWay >= halfWay -> halfWay / v1
        firstWay + secondWay >= halfWay -> (halfWay - firstWay) / v2 + t1
        else -> (halfWay - firstWay - secondWay) / v3 + t1 + t2
    }
    return halfTime
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return when {
        kingX == rookX1 && kingX == rookX2 -> 3
        kingX == rookX1 && kingY == rookY2 -> 3
        kingY == rookY1 && kingY == rookY2 -> 3
        kingY == rookY1 && kingX == rookX2 -> 3

        kingX == rookX1 -> 1
        kingY == rookY1 -> 1

        kingX == rookX2 -> 2
        kingY == rookY2 -> 2

        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    return when {
        (kingX == rookX || kingY == rookY) && (abs(kingY - kingX) == abs(bishopY - bishopX)) -> 3
        kingX == rookX || kingY == rookY -> 1
        abs(kingX - kingY) == abs(bishopX - bishopY) || abs(kingY - kingX) == abs(bishopY - bishopX) ||
                abs(kingY - kingX) == abs(bishopX - bishopY) || abs(kingX - kingY) == abs(bishopY - bishopX) -> 2
        abs(kingX + kingY) == abs(bishopY + bishopX) -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return when {
        a + b < c || a + c < b || b + c < a -> -1
        sqr(a) == sqr(b) + sqr(c) || sqr(b) == sqr(a) + sqr(c) || sqr(c) == sqr(b) + sqr(a) -> 1
        ((a < b && a < c) || (b < c && b < a) || (c < a && c < b)) && (a == b || a == c || b == c) -> 0
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (a <= c) && (b <= d) -> b - c
        (a <= c) && (b >= d) -> d - c
        (a >= c) && (b <= d) -> b - a
        (a >= c) && (b >= d) && (d >= a) -> d - a
        else -> -1
    }
}
