package com.example.library;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    EditText author_et, book_et;
    Button addNewBookButton, deleteBookButton;
    SharedPreferences bookAnnotationPreferences;
    SharedPreferences.Editor editor;
    public static final String prefName = "bookAnnotations";



    ListView bookList;
    LinkedList<Book> bookLinkedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookAnnotationPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        editor = bookAnnotationPreferences.edit();

        bookList = findViewById(R.id.book_list);
        bookLinkedList = new LinkedList<>();

        //todo подготовка данных
        bookLinkedList.add(new Book("А.Азимов", "Основание",
                2015, R.drawable.osnovanie));
        editor.putString("Основание", getString(R.string.osnovanie_discription));
        bookLinkedList.add(new Book("Ф.Достоевский", "Преступление и наказание",
                1972, R.drawable.prestuplenie));
        editor.putString("Преступление и наказание",getString(R.string.prestuplenie_discription));
        bookLinkedList.add(new Book("Н.Гоголь", "Шинель",
                1998, R.drawable.shinel));
        bookLinkedList.add(new Book("М.Булгаков", "Роковые яйца",
                1998, R.drawable.book));
        bookLinkedList.add(new Book("народ", "Колобок",
                1000, R.drawable.book));
        editor.putString("Колобок", getString(R.string.kolobok_discription));
        editor.apply();

        //TODO создать массивы с ключами-идентификаторами
        String[] keyArray = {"author", "title", "year","cover"};
        int [] idArray = {R.id.author, R.id.book_title,R.id.year, R.id.cover};
        //TODO создание списка map для адаптера
        LinkedList<HashMap<String, Object>> listForAdapter = new LinkedList<>();
        for (int i = 0; i < bookLinkedList.size(); i++) {
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put(keyArray[0], bookLinkedList.get(i).author);
            bookMap.put(keyArray[1], bookLinkedList.get(i).title);
            bookMap.put(keyArray[2], bookLinkedList.get(i).year);
            bookMap.put(keyArray[3], bookLinkedList.get(i).coverId);
            listForAdapter.add(bookMap);
        }
        //todo создание адаптера
//        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter(this,
//                R.layout.list_item, bookLinkedList);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listForAdapter, R.layout.list_item, keyArray, idArray);


        //todo привязать адаптер к listview
        bookList.setAdapter(simpleAdapter);


        //todo реакция на нажатие
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                Intent showDescriptionIntent = new Intent(MainActivity.this,
                        ShowDescription.class);
                Book book = bookLinkedList.get(i);
                showDescriptionIntent.putExtra(ShowDescription.MY_KEY_STRING, bookLinkedList.get(i).title);
                startActivity(showDescriptionIntent);


            }
        });
        //TODO создать интерфейс добавления-удаления
//        addNewBookButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bookLinkedList.add(new Book(author_et.getText().toString(),
//                        book_et.getText().toString()));
//
//                arrayAdapter.notifyDataSetChanged();//обновление экрана
//            }
//        });
//        deleteBookButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i < bookLinkedList.size(); i++) {
//                    if ((bookLinkedList.get(i).author).equals(author_et.getText().toString()) &&
//                            (bookLinkedList.get(i).title).equals(book_et.getText().toString())){
//                        bookLinkedList.remove(i);
//                    }
//                }
//                arrayAdapter.notifyDataSetChanged();//обновление экрана
//            }
//        });
    }
}
