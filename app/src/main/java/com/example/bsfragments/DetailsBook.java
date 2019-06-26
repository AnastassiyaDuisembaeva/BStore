package com.example.bsfragments;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsBook extends AppCompatActivity {
    private TextView name;
    private TextView author;
    private TextView bookGanre;
    private TextView price;
    private ImageView imageBook;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        name = (TextView)findViewById(R.id.name_d);
        author = (TextView)findViewById(R.id.author_d);
        bookGanre = (TextView)findViewById(R.id.bookGanre_d);
        price = (TextView)findViewById(R.id.price_d);
        imageBook = (ImageView)findViewById(R.id.imageBook_d);
        button = (Button)findViewById(R.id.button);


        name.setText(getIntent().getExtras().getString("item_book_name"));
        author.setText(getIntent().getExtras().getString("item_book_author"));
        bookGanre.setText(getIntent().getExtras().getString("item_book_ganre"));
        price.setText(getIntent().getExtras().getString("item_book_price"));
        String myUrl = getIntent().getExtras().getString("item_book_image");
        Uri myUri = Uri.parse(myUrl);
        imageBook.setImageURI(myUri);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(DetailsBook.this, "Книга успешно куплена", Toast.LENGTH_SHORT).show();
                DetailsBook.this.finish();
            }
        });

        setContentView(R.layout.activity_details_book);
    }
}
