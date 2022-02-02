package corp.umbrella.discountapp

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private const val PREF_NAME = "prefName"
        private const val KEY_COUNTER = "counter"
    }

    override fun onCreate() {
        super.onCreate()
        val shredPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val newCounterValue = shredPref.getInt(KEY_COUNTER, 0) + 1
        shredPref.edit().putInt(KEY_COUNTER, newCounterValue).apply()
    }
}