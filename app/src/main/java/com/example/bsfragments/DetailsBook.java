package com.example.bsfragments;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsBook extends AppCompatActivity {
    private TextView name;
    private TextView author;
    private TextView bookGanre;
    private TextView price;
    private ImageView imageBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        name = (TextView)findViewById(R.id.name_d);
        author = (TextView)findViewById(R.id.author_d);
        bookGanre = (TextView)findViewById(R.id.bookGanre_d);
        price = (TextView)findViewById(R.id.price_d);
        imageBook = (ImageView)findViewById(R.id.imageBook_d);

        name.setText(arguments.get("item_book_name").toString());
        author.setText(arguments.get("item_book_author").toString());
        bookGanre.setText(arguments.get("item_book_ganre").toString());
        price.setText(arguments.get("item_book_price").toString());
        String myUrl = arguments.get("item_book_image").toString();
        Uri myUri = Uri.parse(myUrl);
        imageBook.setImageURI(myUri);

        setContentView(R.layout.activity_details_book);
    }
}
