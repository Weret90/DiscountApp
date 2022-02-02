package corp.umbrella.discountapp

import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class DiscountTest {

    @Test
    fun testWithCorrectData1() {
        val correctArrayWithPrices = arrayOf(5, 100, 20, 66, 16)
        val actual = Discount.execute(correctArrayWithPrices, 50, 1, 3)
        val expected = arrayOf(50, 10, 33)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testWithCorrectData2() {
        val correctArrayWithPrices = arrayOf(2, 4, 6, 8, 10, 12, 14, 16)
        val actual = Discount.execute(correctArrayWithPrices, 50, 3, 4)
        val expected = arrayOf(4, 5, 6, 7)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testWithCorrectData3() {
        val correctArrayWithPrices = arrayOf(30, 40, 25, 27)
        val actual = Discount.execute(correctArrayWithPrices, 30, 0, 4)
        val expected = arrayOf(21, 28, 17, 18)
        assertArrayEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun testWithInCorrectData1() {
        //отрицательная цена в массиве
        val incorrectArrayWithPrices = arrayOf(30, 40, -25, 27)
        Discount.execute(incorrectArrayWithPrices, 30, 0, 4)
    }

    @Test(expected = Exception::class)
    fun testWithInCorrectData2() {
        //нулевой размер скидки
        val correctArrayWithPrices = arrayOf(30, 40, 25, 27)
        Discount.execute(correctArrayWithPrices, 0, 2, 4)
    }

    @Test(expected = Exception::class)
    fun testWithInCorrectData3() {
        //индекс элемента со скидкой больше размера массива
        val correctArrayWithPrices = arrayOf(30, 40, 25, 27)
        Discount.execute(correctArrayWithPrices, 20, 7, 3)
    }

    @Test(expected = Exception::class)
    fun testWithInCorrectData4() {
        //отрицательное количество цен подлежащих скидке
        val correctArrayWithPrices = arrayOf(30, 40, 25, 27)
        Discount.execute(correctArrayWithPrices, 20, 0, -1)
    }
}