package com.example.bsfragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddNewBookActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    private EditText idAdd;
    private EditText name;
    private EditText author;
    private EditText bookGanre;
    private EditText price;
    private EditText imageBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        idAdd = (EditText)findViewById(R.id.idAdd);
        name = (EditText)findViewById(R.id.nameAdd);
        author = (EditText)findViewById(R.id.authorAdd);
        bookGanre = (EditText)findViewById(R.id.bookGanreAdd);
        price = (EditText)findViewById(R.id.priceAdd);
        imageBook = (EditText)findViewById(R.id.imageBookAdd);

        findViewById(R.id.add_new_book).setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_new_book)
        {
            writeNewBook(idAdd.getText().toString(), name.getText().toString(), author.getText().toString(), bookGanre.getText().toString(), price.getText().toString(), imageBook.getText().toString());
        }
    }
    public void writeNewBook(String idAdd, String name, String author, String bookGanre, String price, String imageBook){
        Item book = new Item(name, author, bookGanre, price, imageBook);
        if(book != null){
            myRef.child("books").child(idAdd).setValue(book);

            Toast.makeText(AddNewBookActivity.this, "Книга добавлена успешна", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else{
            Toast.makeText(AddNewBookActivity.this, "Поля не заполненны, книга не может быть добавлена", Toast.LENGTH_SHORT).show();
        }

    }
}
