package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        TextView welcomeText = findViewById(R.id.text_view_welcome);
        welcomeText.setText("Welcome to this game!");

        Button newGameButton = findViewById(R.id.button_new_game);
        newGameButton.setOnClickListener(v -> startNewGame());
    }

    private void startNewGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
