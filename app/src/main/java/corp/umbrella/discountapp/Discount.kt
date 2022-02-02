package corp.umbrella.discountapp

import androidx.annotation.IntRange
import java.lang.Exception
import kotlin.math.floor

class Discount {

    companion object {

        fun execute(
            prices: Array<Int>,
            @IntRange(from = 1, to = 99) discount: Int,
            @IntRange(from = 0) offset: Int,
            @IntRange(from = 1) readLength: Int,
        ): Array<Int> {

            //проверяем правильность входных данных
            prices.forEach {
                if (it < 1) {
                    throw Exception("Цена в массиве не может быть отрицательной или равной 0")
                }
            }

            if (discount < 1 || discount > 99) {
                throw Exception("Процент скидки не может меньше или равно 0 и больше или равно 100")
            }
            if (offset < 0 || offset >= prices.size) {
                throw Exception("Позиция не может быть меньше 0 и больше или равно размеру массива")
            }
            if (readLength < 1 || readLength > prices.size - offset) {
                throw Exception("Количество цен не может быть равно 0 и больше количества оставшихся цен")
            }

            //находим диапазон индексов цен подлежащих скидке
            val indicesWithDiscount = prices.indices.filter {
                it in offset until offset + readLength
            }
            val newArrayList = mutableListOf<Int>()

            //циклом пробегаемся по этим индексам и возвращаем новый массив с новыми ценами
            indicesWithDiscount.forEach {
                newArrayList.add(getNewPriceWithDiscount(prices[it], discount))
            }
            return newArrayList.toTypedArray()
        }

        private fun getNewPriceWithDiscount(price: Int, discount: Int): Int {
            val newPrice = price - (price.toDouble() * discount) / 100
            return floor(newPrice).toInt() //округление вниз, всегда в меньшую сторону
        }
    }
}