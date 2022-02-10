package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    EditText author_et, book_et;
    Button addNewBookButton, deleteBookButton;

    ListView bookList;
    LinkedList<Book> bookLinkedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = findViewById(R.id.book_list);
        author_et = findViewById(R.id.author);
        book_et = findViewById(R.id.book);
        addNewBookButton = findViewById(R.id.addButton);
        deleteBookButton = findViewById(R.id.deleteButton);
        bookLinkedList = new LinkedList<>();

        //todo подготовка данных
        bookLinkedList.add(new Book("Л.Толстой", "Война и мир"));
        bookLinkedList.add(new Book("А и Б Стругацкие", "Пикник на обочине"));
        bookLinkedList.add(new Book("Т.Драйзер", "Американская трагедия"));

        //todo создание адаптера
        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter(this,
                R.layout.list_item, bookLinkedList);
        //todo привязать адаптер к listview
        bookList.setAdapter(arrayAdapter);

        //todo реакция на нажатие
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        bookLinkedList.get(i).toString(), Toast.LENGTH_SHORT).show();

            }
        });
        //TODO создать интерфейс добавления-удаления
        addNewBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookLinkedList.add(new Book(author_et.getText().toString(),
                        book_et.getText().toString()));

                arrayAdapter.notifyDataSetChanged();//обновление экрана
            }
        });
        deleteBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < bookLinkedList.size(); i++) {
                    if ((bookLinkedList.get(i).author).equals(author_et.getText().toString()) &&
                            (bookLinkedList.get(i).title).equals(book_et.getText().toString())){
                        bookLinkedList.remove(i);
                    }
                }
                arrayAdapter.notifyDataSetChanged();//обновление экрана
            }
        });
    }
}