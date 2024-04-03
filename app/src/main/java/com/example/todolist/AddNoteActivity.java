package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHigh;
    private Button buttonSave;
    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        viewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if(shouldClose){
                    finish();
                }
            }
        });
        initUse();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

    }

    private void saveNote() {
        String text = editTextNote.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(this, R.string.note_is_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        int priority = getPriority();
        Note note = new Note(text, priority);
        viewModel.saveNote(note);
    }

    private int getPriority() {
        if (radioButtonLow.isChecked()) {
            return 0;
        } else if (radioButtonMedium.isChecked()) {
            return 1;
        } else if (radioButtonHigh.isChecked()) {
            return 2;
        } else {
            return 3;
        }
    }

    private void initUse() {
        editTextNote = findViewById(R.id.editTextNote);

        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonHigh = findViewById(R.id.radioButtonHigh);

        buttonSave = findViewById(R.id.buttonSave);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}