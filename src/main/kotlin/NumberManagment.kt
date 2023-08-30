import kotlin.random.Random

fun getRandomNumber(): Int {
    val random = Random(System.currentTimeMillis())
    return random.nextInt(100000, 1000000)
}