var running = true
var patients = mutableListOf<Patient>()
var selectedPatient: Patient? = null

fun main(args: Array<String>) {

    loadDatabase()

    println("___ Welcome by PATIENT FILES ___")
    println()
    println("""(i) Available Commands: 
        | - stop
        | - add patient
        | - delete patient 
        | - select patient
        | - list patients
        | - list measurements
        | - list all measurements
        | - add measurement
        | - delete measurement""".trimMargin())

    while (running) {
        when (readln()) {
            "stop" -> stop()
            "add patient" -> addPatient()
            "delete patient" -> deletePatient()
            "select patient" -> selectPatient()
            "list patients" -> listPatients()
            "list measurements" -> listMeasurements()
            "list all measurements" -> listAllMeasurements()
            "add measurement" -> addMeasurement()
            "delete measurement" -> deleteMeasurement()
            else -> {println("!!! unknown command !!!")}
        }
    }

}

fun stop() {
    running = false
}

fun addPatient() {
    val patient = Patient.byUserInput()
    patients.add(patient)
    saveDatabase()
    println("+++ PATIENT ADDED +++")
}

fun deletePatient() {

    listPatients()
    println("--- DELETE PATIENT ---")

    val patient = getPatient()

    println("Do you realy want to delete ${patient.firstname} ${patient.surname}? ")
    when(getInput<String>("Enter Surname to confirm", 20)) {
        patient.surname -> {
            patients.remove(patient)
            saveDatabase()
            println("--- Patient DELETED ---")
        }
        else -> {
            println("--- DELETE PATIENT CANCELED ---")
        }
    }
}

fun selectPatient() {

    listPatients()
    println(">>> SELECT PATIENT <<<<")

    selectedPatient = getPatient()
    println(">>> ${selectedPatient!!.firstname} ${selectedPatient!!.surname} SELECTED <<<")
}
private fun getPatient(): Patient {

    while (true) {
        val patientNumber = getInput<Int>("patientNumber", 11)
        val patient = patients.find { it.patientNumber == patientNumber}

        if(patient != null) {
            return patient
        } else {
            println("!!! CANT FIND PATIENT !!!")
        }
    }

}

fun listPatients() {
    println("Patients:")
    patients.forEach {
        it.print()
    }
    if (patients.isEmpty()) println("no patients found")
}

fun listMeasurements() {
    if (selectedPatient == null) {
        println("!!! PLEASE SELECT PATIENT FIRST !!!")
        selectPatient()
    }
    selectedPatient!!.printMeasurements()
}

fun listAllMeasurements() {
    val allMeasurements = mutableListOf<Measurement>()
    patients.forEach { allMeasurements.addAll(it.measurements) }
    val allMeasurementsSorted = allMeasurements.sortedByDescending { it.measurementNumber }
    allMeasurementsSorted.forEach { println(it.toString()) }
}

fun addMeasurement() {
    if (selectedPatient == null) {
        println("!!! PLEASE SELECT PATIENT FIRST !!!")
        selectPatient()
    }
    selectedPatient!!.addMesurement(Measurement.byUserInput(selectedPatient!!.patientNumber))
    saveDatabase()
    println("+++ Measurement Added +++")
}

fun deleteMeasurement() {
    if (selectedPatient == null) {
        println("!!! PLEASE SELECT PATIENT FIRST !!!")
        selectPatient()
    }
    listMeasurements()
    println("--- DELETE MEASUREMENT ---")
    val measurement = getMeasurement(selectedPatient!!)
    println("Do you really want to delete ${measurement.title} ?")
    when(getInput<String>("Enter Title to confirm", 20)) {
        measurement.title -> {
            selectedPatient!!.measurements.remove(measurement)
            saveDatabase()
            println("--- MEASUREMENT DELETED ---")
        }
        else -> {
            println("--- MEASUREMENT PATIENT CANCELED ---")
        }
    }
}

private fun getMeasurement(patient: Patient): Measurement {

    while (true) {
        val measurementNumber = getInput<Int>("measurementNumber", 11)
        val measurement = selectedPatient!!.measurements.find { it.measurementNumber == measurementNumber }

        if(measurement != null) {
            return measurement
        } else {
            println("!!! CANT FIND MEASUREMENT !!!")
        }
    }

}