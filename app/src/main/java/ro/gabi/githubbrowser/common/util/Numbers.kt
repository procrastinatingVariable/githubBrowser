package ro.gabi.githubbrowser.common.util

import java.util.*
import kotlin.math.floor

object FunNumberFormatter {
    
    private val suffixes = TreeMap(mapOf(
        1_000L to 'k',
        1_000_000L to 'M',
        1_000_000_000L to 'G',
        1_000_000_000_000L to 'T',
        1_000_000_000_000_000L to 'P',
        1_000_000_000_000_000_000L to 'E'
    ))

    fun format(number: Long): String {
        return when {
            number == Long.MIN_VALUE           -> format(Long.MIN_VALUE + 1)
            number < 0 -> "-${format(-number)}"
            number < 1000L -> number.toString()
            else -> _format(number)
        }
    }

    private fun _format(number: Long): String {
        val e = suffixes.floorEntry(number) ?: throw IllegalArgumentException("couldn't find suffix for $number")
        val divideBy = e.key
        val suffix = e.value

        val truncated = number / (divideBy / 10)
        val hasDecimal = truncated < 100 && (truncated / 10f) != floor((truncated / 10f))
        return if (hasDecimal) "${(truncated / 10f)}$suffix"  else "${(truncated / 10)}$suffix"
    }
    
}