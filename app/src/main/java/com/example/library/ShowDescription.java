package com.example.library;

import static com.example.library.MainActivity.prefName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowDescription extends AppCompatActivity {
    private String bookForShow;
    Button saveButton;
    public static final String MY_KEY_STRING = "showDescriptionKey";
    SharedPreferences bookAnnotationPreferences;
    SharedPreferences.Editor editor;
    EditText bookDicriptionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_description);
        bookForShow = getIntent().getStringExtra(MY_KEY_STRING);
        bookDicriptionET = findViewById(R.id.book_discription);
        saveButton = findViewById(R.id.save_button);

        bookAnnotationPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        editor = bookAnnotationPreferences.edit();
        if (bookAnnotationPreferences.getString(bookForShow,"").length() > 0){
            bookDicriptionET.setText(bookAnnotationPreferences.getString(bookForShow,""));
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(bookForShow, bookDicriptionET.getText().toString());
                editor.apply();
            }
        });

    }
}