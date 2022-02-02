package corp.umbrella.discountapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import corp.umbrella.discountapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREF_NAME = "prefName"
        private const val KEY_COUNTER = "counter"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkCounter()

        binding.buttonGetResult.setOnClickListener {
            calculationResult()
        }
    }

    private fun checkCounter() {
        val value = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getInt(KEY_COUNTER, 0)
        if (value == 3) {
            showToast("Третий холодный запуск приложения")
        }
    }

    private fun calculationResult() {
        try {
            with(binding) {
                val prices = renderPrices(prices.text.toString())
                val discount = discount.text.toString().toInt()
                val offset = offset.text.toString().toInt()
                val readLength = readLength.text.toString().toInt()
                result.text = Discount.execute(prices, discount, offset, readLength)
                    .toList().toString()
            }
        } catch (e: NumberFormatException) {
            showToast("Заполните все поля. Используйте только целые числа, никаких букв и символов. В массиве цен: только одинарные пробелы")
        } catch (e: Exception) {
            showToast(e.message)
        }
    }

    private fun renderPrices(pricesText: String): Array<Int> {
        val prices = pricesText.split(" ")
        return prices.map { it.toInt() }.toTypedArray()
    }

    private fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}