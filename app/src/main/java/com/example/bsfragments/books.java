package com.example.bsfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.bsfragments.interfaces.IBookClickedInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class books extends Fragment  implements IBookClickedInterface {
    List<Item> items = new ArrayList<>();
    ArrayList<Item> books = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialData();
        return inflater.inflate(R.layout.fragment_books, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.booksList);
        DataAdapter adapter = new DataAdapter(getActivity(), items, this);
        //добавление notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemsBook = dataSnapshot.getChildren();

                for(DataSnapshot book : itemsBook ){
                    Item parsedBook = new Item();
                    String bookId =  myRef.child("books").getKey();
                    parsedBook.setName((String) book.child(bookId).child("name").getValue());
                    parsedBook.setAuthor((String) book.child(bookId).child("author").getValue());
                    parsedBook.setbookGanre((String) book.child(bookId).child("bookGanre").getValue());
                    parsedBook.setPrice((String) book.child(bookId).child("price").getValue());
                    parsedBook.setImageBook((String) book.child(bookId).child("imageBook").getValue());
                   if (parsedBook != null) {
                        books.add(parsedBook);
                    }
                }
                Log.d("Data", books.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    @Override
    public void onViewClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        Bundle data = new Bundle();
        data.putString("item_book_image", items.get(position).getImageBook());
        getActivity().startActivity(intent);
    }

    public void setInitialData(){
        for(Item itemBook : books ){
            items.add(itemBook);
        }
    }
}
