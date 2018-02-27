package iii_conventions

import com.google.common.collect.ComparisonChain

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

//operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()
operator fun MyDate.rangeTo(endInclusive: MyDate): DateRange = DateRange(this, endInclusive)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

data class RepeatedTimeInterval(val interval: TimeInterval, val count: Int)


class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.iterator(): Iterator<MyDate> =
        object: Iterator<MyDate> {
            var current = start
            override fun hasNext(): Boolean = current <= endInclusive

            override fun next(): MyDate {
                if (hasNext()) {
                    val r = current
                    current = current.nextDay()
                    return r
                } else {
                    throw NoSuchElementException()
                }
            }
        }

operator fun MyDate.compareTo(other: MyDate): Int =
        ComparisonChain.start().
                compare(year, other.year)
                .compare(month, other.month)
                .compare(dayOfMonth, other.dayOfMonth)
                .result()

operator fun MyDate.plus(interval: TimeInterval): MyDate =
        this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
        this.addTimeIntervals(repeatedTimeInterval.interval, repeatedTimeInterval.count)

operator fun DateRange.contains(date: MyDate): Boolean = start <= date && date <= endInclusive