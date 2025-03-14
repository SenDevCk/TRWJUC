import android.content.Context
import android.content.SharedPreferences
import com.bih.nic.bsphcl.trwjuc.data.User
import com.google.gson.Gson


class CommanPref private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("session_data", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var sSharedPrefs: CommanPref? = null

        fun getInstance(context: Context): CommanPref { // ✅ Changed return type to NON-NULL
            return sSharedPrefs ?: synchronized(this) {
                sSharedPrefs ?: CommanPref(context.applicationContext).also { sSharedPrefs = it }
            }
        }
    }

    // ✅ Save String Data
    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    // ✅ Retrieve String Data
    fun getData(key: String): String? {
        return sharedPreferences.getString(key.trim(), null)
    }

    // ✅ Save Boolean Data
    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    // ✅ Retrieve Boolean Data
    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    // ✅ Remove a Key
    fun removeData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    // ✅ Clear All Data
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveUser(data: User?) {
        val gson = Gson()
        val json = gson.toJson(data)
        sharedPreferences.edit().putString("userdata", json).apply()
    }
    fun getUser():User {
        val gson = Gson()
        val json: String = sharedPreferences.getString("userdata", null)!!
        val user: User = gson.fromJson(json, User::class.java)
        return user
    }

}
