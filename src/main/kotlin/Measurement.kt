// Replaced by 6-digit random number
var measurementNumberCounter = -1
fun generateMesurementNumber(): Int {
    measurementNumberCounter++
    return measurementNumberCounter
}

data class Measurement (
    val title: String,
    val value: Int,
    val patientNumber: Int,
    val remarks: String,
    val measurementNumber: Int = getRandomNumber()) {

    companion object {
        fun byUserInput(patientNumber: Int): Measurement {
            println("+++ ADD MEASUREMENT +++")
            val title = getInput<String>("title", 20)
            val value = getInput<Int>("value", 3)
            val remarks = getInput<String>("remarks", 200)
            return Measurement(title, value, patientNumber, remarks)
        }
    }

    override fun toString(): String {
        return """ --- $title ---
            | | value: $value
            | | remarks: $remarks
            | | measurementNumber: $measurementNumber
            | ---
        """.trimMargin()
    }

}
