package corp.umbrella.discountapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import corp.umbrella.discountapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetResult.setOnClickListener {
            calculationResult()
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