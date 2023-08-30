
// Replaced by 6-digit random number
var patientNumberCounter = -1
fun generatePatientNumber(): Int {
    patientNumberCounter++
    return patientNumberCounter
}

data class Patient(
    val surname: String,
    val firstname: String,
    val patientNumber: Int = getRandomNumber(),
    val measurements: MutableList<Measurement> = mutableListOf()) {

    companion object {
        fun byUserInput(): Patient {
            println("+++ ADD PATIENT +++")
            val surname = getInput<String>("surname", 20)
            val firstname = getInput<String>("firstname", 20)
            return Patient(surname, firstname)
        }
    }

    fun addMesurement(measurement: Measurement) {
        measurements.add(measurement)
    }

    fun print() {
        println(""" - ${this.toString()}
""")
    }

    fun printMeasurements() {
        println("""Measurements:
            |
        """.trimMargin())
        measurements.forEach {
            print(it.toString())
            println()
        }
        if (measurements.isEmpty()) println("no measurements found")
    }

    override fun toString(): String = "$surname, $firstname (patientNumber: $patientNumber) "

}

