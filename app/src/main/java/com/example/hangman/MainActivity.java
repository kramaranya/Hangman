package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String[] words = {"ANDROID", "ANYA", "UKRAINE"};
    private String selectedWord;
    private char[] guessedWordArray;
    private int attemptsLeft;
    private boolean gameEnded;

    private TextView textViewWord;
    private TextView textViewAttempts;
    private ImageView imageViewHangman;
    private Button[] buttons;
    private Button buttonEndGame;
    private Button buttonNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWord = findViewById(R.id.text_view_word);
        textViewAttempts = findViewById(R.id.text_view_attempts);
        imageViewHangman = findViewById(R.id.image_view_hangman);

        Button buttonA = findViewById(R.id.button_a);
        Button buttonB = findViewById(R.id.button_b);
        Button buttonC = findViewById(R.id.button_c);
        Button buttonD = findViewById(R.id.button_d);
        Button buttonE = findViewById(R.id.button_e);
        Button buttonF = findViewById(R.id.button_f);
        Button buttonG = findViewById(R.id.button_g);
        Button buttonH = findViewById(R.id.button_h);
        Button buttonI = findViewById(R.id.button_i);
        Button buttonJ = findViewById(R.id.button_j);
        Button buttonK = findViewById(R.id.button_k);
        Button buttonL = findViewById(R.id.button_l);
        Button buttonM = findViewById(R.id.button_m);
        Button buttonN = findViewById(R.id.button_n);
        Button buttonO = findViewById(R.id.button_o);
        Button buttonP = findViewById(R.id.button_p);
        Button buttonQ = findViewById(R.id.button_q);
        Button buttonR = findViewById(R.id.button_r);
        Button buttonS = findViewById(R.id.button_s);
        Button buttonT = findViewById(R.id.button_t);
        Button buttonU = findViewById(R.id.button_u);
        Button buttonV = findViewById(R.id.button_v);
        Button buttonW = findViewById(R.id.button_w);
        Button buttonX = findViewById(R.id.button_x);
        Button buttonY = findViewById(R.id.button_y);
        Button buttonZ = findViewById(R.id.button_z);

        buttonEndGame = findViewById(R.id.button_end_game);
        buttonNewGame = findViewById(R.id.button_new_game);


        buttons = new Button[]{buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI,
                buttonJ, buttonK, buttonL, buttonM, buttonN, buttonO, buttonP, buttonQ, buttonR, buttonS,
                buttonT, buttonU, buttonV, buttonW, buttonX, buttonY, buttonZ, buttonEndGame, buttonNewGame};

        resetGame();
        updateUI();

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    private void closeGame() {
        Intent intent = new Intent(MainActivity.this, OpenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button clickedButton = (Button) v;
            if (clickedButton == buttonEndGame) {
                closeGame();
            } else if (clickedButton == buttonNewGame) {
                resetGame();
                updateUI();
                imageViewHangman.setImageResource(R.drawable.hangman_0);
            } else {
                clickedButton.setEnabled(false);
                char clickedLetter = clickedButton.getText().charAt(0);
                checkLetter(clickedLetter);
            }
        }
    }

    private void checkLetter(char letter) {
        boolean letterFound = false;

        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                guessedWordArray[i] = letter;
                letterFound = true;
            }
        }

        if (!letterFound) {
            attemptsLeft--;
            updateHangmanImage();
        }

        if (attemptsLeft == 0 || String.valueOf(guessedWordArray).equals(selectedWord)) {
            endGame();
        }

        updateUI();
    }

    private void updateHangmanImage() {
        int drawableId = getResources().getIdentifier("hangman_" + (6 - attemptsLeft), "drawable", getPackageName());
        imageViewHangman.setImageResource(drawableId);
    }

    private void endGame() {
        gameEnded = true;
        for (Button button : buttons) {
            button.setEnabled(false);
        }
        buttonEndGame.setEnabled(true);
        buttonNewGame.setEnabled(true);

        if (attemptsLeft == 0) {
            Toast.makeText(this, "Game Over! The word was: " + selectedWord, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Congratulations! You won!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {
        StringBuilder wordWithSpaces = new StringBuilder();

        for (char letter : guessedWordArray) {
            wordWithSpaces.append(letter).append(" ");
        }

        textViewWord.setText(wordWithSpaces.toString().trim());
        textViewAttempts.setText("Attempts Left: " + attemptsLeft);
    }

    private void resetGame() {
        selectedWord = words[(int) (Math.random() * words.length)];
        guessedWordArray = new char[selectedWord.length()];
        attemptsLeft = 6;
        gameEnded = false;

        for (int i = 0; i < selectedWord.length(); i++) {
            guessedWordArray[i] = '_';
        }

        for (Button button : buttons) {
            button.setEnabled(true);
        }
    }
}
