package com.example.hackathonproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathonproject.Constants.LanguagaMap;

import java.util.ArrayList;

public class Form extends AppCompatActivity {

    ImageView mic;
    Spinner languageSpinner;
    EditText content;
    Button submitForm;
    String cont = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        languageSpinner = findViewById(R.id.languageDropDown);
        mic = findViewById(R.id.ppfFormMic);
        content = findViewById(R.id.edit_text_ppfForm);
        submitForm = findViewById(R.id.submit_form_button);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(LanguagaMap.map.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        mic.setOnClickListener(v -> {
            String selectedLanguage = languageSpinner.getSelectedItem().toString();
            String languageCode = LanguagaMap.getFromValue(selectedLanguage);
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, languageCode);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode);

            if (intent.resolveActivity(Form.this.getPackageManager()) != null) {
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(Form.this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
            }
        });

        submitForm.setOnClickListener(v -> {
            TranslateAsync translateAsync = new TranslateAsync();
            String formText = String.valueOf(content.getText());
            translateAsync.execute(formText);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            cont = content.getText().toString() + " " + (result != null ? result.get(0) : null);
            content.setText("");
            content.setText(cont);
        }
    }

}
