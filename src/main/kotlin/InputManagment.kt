inline fun <reified E> getInput(title: String, maxLength: Int?): E {
    while (true) {
        print("$title: ")
        val i = readln()
        try {
            return when (E::class) {
                Int::class -> i.toInt() as E
                String::class -> i as E
                Double::class -> i.toDouble() as E
                Boolean::class -> i.toBoolean() as E
                else -> {
                    throw NotImplementedError("This datatype is not supported. Add to when to handle datatype.")
                }
            }
        } catch (e: Exception) {
            println("!!! WRONG DATATYPE !!!")
        }

    }
}