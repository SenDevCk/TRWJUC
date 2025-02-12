import android.content.Context
import android.content.SharedPreferences

class CommanPref private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("session_data", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var sSharedPrefs: CommanPref? = null

        fun getInstance(context: Context): CommanPref? {
            return sSharedPrefs ?: synchronized(this) {
                sSharedPrefs ?: CommanPref(context.applicationContext).also { sSharedPrefs = it }
            }
        }
    }

    // Your shared preferences methods go here
    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}