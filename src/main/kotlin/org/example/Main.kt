package org.example

import kotlin.random.Random


enum class Move(val displayName: String) {
    ROCK("Камень"),
    SCISSORS("Ножницы"),
    PAPER("Бумага");

    companion object {

        fun from(input: String): Move? {
            return when(input.trim().lowercase()) {
                "камень", "1" -> ROCK
                "ножницы", "2" -> SCISSORS
                "бумага", "3" -> PAPER
                else -> null
            }
        }
    }
}


fun main() {
    var wins = 0
    var losses = 0
    var draws = 0

    println("Игра «Камень-Ножницы-Бумага»")
    println("Правила:")
    println(" - Камень побеждает Ножницы")
    println(" - Ножницы побеждают Бумагу")
    println(" - Бумага побеждает Камень")

    gameLoop@ while (true) {
        println("\nСделайте выбор:")
        println("1. Камень")
        println("2. Ножницы")
        println("3. Бумага")
        print("Ваш выбор: ")
        val userInput = readLine()
        val playerMove = userInput?.let { Move.from(it) }

        if (playerMove == null) {
            println("Неверный ввод. Попробуйте ещё раз.")
            continue
        }

        val computerMove = Move.values().random()

        println("Вы выбрали: ${playerMove.displayName}")
        println("Компьютер выбрал: ${computerMove.displayName}")

        val result = determineResult(playerMove, computerMove)
        when(result) {
            0 -> {
                println("Ничья!")
                draws++
            }
            1 -> {
                println("Вы выиграли!")
                wins++
            }
            -1 -> {
                println("Вы проиграли!")
                losses++
            }
        }

        println("\nСтатистика игры: Побед - $wins, Поражений - $losses, Ничьих - $draws")
        println("Хотите сыграть ещё? (Y/N): ")
        val again = readLine()
        if (again == null || again.lowercase() != "y") {
            println("Игра завершена. Итоговая статистика: Побед - $wins, Поражений - $losses, Ничьих - $draws")
            break@gameLoop
        }
    }
}

fun determineResult(player: Move, computer: Move): Int {
    if (player == computer) return 0
    return when(player) {
        Move.ROCK -> if (computer == Move.SCISSORS) 1 else -1
        Move.SCISSORS -> if (computer == Move.PAPER) 1 else -1
        Move.PAPER -> if (computer == Move.ROCK) 1 else -1
    }
}
