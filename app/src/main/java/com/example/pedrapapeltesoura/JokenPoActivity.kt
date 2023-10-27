package com.example.pedrapapeltesoura

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class JokenPoActivity : AppCompatActivity() {

    private lateinit var playerChoice: String
    private lateinit var cpuChoice: String
    private var playerScore = 0
    private var cpuScore = 0
    private var empate = 0
    private lateinit var playerScoreTextView: TextView
    private lateinit var cpuScoreTextView: TextView
    private lateinit var empateTextView: TextView
    private lateinit var playerChoiceTextView: TextView
    private lateinit var cpuChoiceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("JokenPoActivity", "onPlayerChoice called")
        try {
            setContentView(R.layout.activity_joken_po) // Define o layout da atividade

            // Inicializa as views a partir dos IDs
            playerScoreTextView = findViewById(R.id.textView7)
            cpuScoreTextView = findViewById(R.id.textView8)
            empateTextView = findViewById(R.id.textViewEmpate)
            playerChoiceTextView = findViewById(R.id.textViewJOGADOR)
            cpuChoiceTextView = findViewById(R.id.textView9)

            // Inicializa os botões e atribui tratadores de eventos
            val rockButton = findViewById<ImageButton>(R.id.imagePEDRA)
            val paperButton = findViewById<ImageButton>(R.id.imagePAPEL)
            val scissorsButton = findViewById<ImageButton>(R.id.imageTESOURA)
            val newGameButton = findViewById<Button>(R.id.button)

            rockButton.setOnClickListener { onPlayerChoice("rock") }
            paperButton.setOnClickListener { onPlayerChoice("paper") }
            scissorsButton.setOnClickListener { onPlayerChoice("scissors") }
            newGameButton.setOnClickListener { startNewGame() }
        } catch (e: Exception) {
            e.printStackTrace() // Se ocorrer alguma exceção, ela será registrada no LogCat para depuração
        }
    }

    private fun onPlayerChoice(choice: String) {
        playerChoice = choice
        cpuChoice = generateCPUChoice()
        determineWinner()
        updateUI()
    }

    // Gera a escolha da CPU
    private fun generateCPUChoice(): String {
        val choices = listOf("rock", "paper", "scissors")
        val random = Random()
        return choices[random.nextInt(choices.size)]
    }

    // Determina o ganhador
    private fun determineWinner() {
        if (playerChoice == cpuChoice) {
            // É um empate
            empate++
        } else if ((playerChoice == "rock" && cpuChoice == "scissors") ||
            (playerChoice == "paper" && cpuChoice == "rock") ||
            (playerChoice == "scissors" && cpuChoice == "paper")
        ) {
            playerScore++
        } else {
            cpuScore++
        }
    }

    private fun updateUI() {
        // Atualiza as pontuações e as escolhas exibidas nas views
        playerScoreTextView.text = playerScore.toString()
        cpuScoreTextView.text = cpuScore.toString()
        empateTextView.text = empate.toString()
        playerChoiceTextView.text = playerChoice
        cpuChoiceTextView.text = cpuChoice

        // Define a imagem da escolha da CPU com base na escolha gerada
        when (cpuChoice) {
            "rock" -> cpuChoiceTextView.text = "Pedra"
            "paper" -> cpuChoiceTextView.text = "Papel"
            "scissors" -> cpuChoiceTextView.text = "Tesoura"
        }

        // Define o texto da escolha do jogador com base na escolha
        when (playerChoice) {
            "rock" -> playerChoiceTextView.text = "Pedra"
            "paper" -> playerChoiceTextView.text = "Papel"
            "scissors" -> playerChoiceTextView.text = "Tesoura"
        }
    }

    private fun startNewGame() {
        // Inicializa uma nova partida, redefinindo as escolhas e atualizando a UI
        cpuChoice = ""
        playerChoice = ""
        playerScore = 0
        cpuScore = 0
        empate = 0
        updateUI()
    }
}
