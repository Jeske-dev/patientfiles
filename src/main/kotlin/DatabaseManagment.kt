import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type

var filename = "database.txt"

fun saveDatabase() {
    val json = Gson().toJson(patients)
    File(filename).writeText(json)
}

fun loadDatabase() {
    val type: Type = object : TypeToken<MutableList<Patient>>() {}.type
    val raw = File(filename).readText()
    patients = Gson().fromJson(raw, type)
}