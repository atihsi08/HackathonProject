package com.example.hackathonproject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateAsync extends AsyncTask<String,Void,String> {
    protected String doInBackground(String... params) {

        String text = params[0]; //text to translate
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyDZgnX-GNSI7gfYFUt_4OY_LP6MicgCixg").build().getService();
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.targetLanguage("en"));



        return translation.getTranslatedText();
    }

    //this method will run after doInBackground execution
    protected void onPostExecute(String translatedText) {
        System.out.printf("Translation: %s%n", translatedText);
        Log.d("HARSHUUUUUUUUUUUUUUU",translatedText);
    }
}
