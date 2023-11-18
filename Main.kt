import kotlin.random.Random

fun main() {
    println("Добро пожаловать в игру 'Быки и коровы'!")
    println("Правила просты! Вам нужно отгадать 4-значное число, с неповторяющимися цифрами! \nВам будут даваться подсказки:")
    println("1. Быки - это верно угаданные цифры, которые стоят на своих местах! Соответственно, вам нужно набрать 4 быка!")
    println("2. Коровы - это верно угаданные цифры, но они стоят не на своих местах, попробуйте их переставить!\n")
    println("Компьютер загадал тайное 4-значное число. Попробуйте отгадать!")

    val secretNumber = generateSecretNumber()
    var attempts = 0

    while (true) {
        print("Введите вашу попытку (4 цифры): ")
        val guess = readLine()

        if (guess != null && guess.length == 4 && guess.all { it.isDigit() }) {
            attempts++
            val result = evaluateGuess(guess, secretNumber)

            println("Результат попытки $attempts: $result")

            if (result == "4 бык(а), 0 корова(ы)") {
                println("Поздравляем! Вы отгадали число за $attempts попыток! ")
                break
            }
        } else {
            println("Некорректный ввод. Пожалуйста, введите 4 цифры.")
        }
    }
}

fun generateSecretNumber(): String {
    val digits = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    digits.shuffle()
    return digits.subList(0, 4).joinToString("")
}

fun evaluateGuess(guess: String, secretNumber: String): String {
    var bulls = 0
    var cows = 0

    for (i in guess.indices) {
        if (guess[i] == secretNumber[i]) {
            bulls++
        } else if (secretNumber.contains(guess[i])) {
            cows++
        }
    }

    return "$bulls бык(а), $cows корова(ы)"
}