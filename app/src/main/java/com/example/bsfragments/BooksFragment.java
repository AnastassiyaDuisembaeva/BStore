package com.example.bsfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Iterator;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class BooksFragment extends Fragment  implements IBookClickedInterface {
    View view;

    List<Item> items = new ArrayList<>();
    ArrayList<Item> books = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    RecyclerView recyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_books, container, false);

        return view;
    }


    public void initializeAdapter(){
        recyclerView = (RecyclerView) view.findViewById(R.id.booksList);
        DataAdapter adapter = new DataAdapter(getActivity(), items, this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemsBook = dataSnapshot.getChildren();
                myRef.getDatabase();
                for(DataSnapshot book : itemsBook ){
                    if(book.hasChildren()){
                        Iterator<DataSnapshot> iter = book.getChildren().iterator();
                        while (iter.hasNext()){
                            Item item = null;
                            DataSnapshot snap = iter.next();
                            String nodId = snap.getKey();
                            String bookName = (String) snap.child("name").getValue();
                            String bookGenre = (String) snap.child("bookGanre").getValue();
                            String bookPrice = (String) snap.child("price").getValue();
                            String bookAuthor = (String) snap.child("author").getValue();
                            String bookImage = (String) snap.child("imageBook").getValue();

                            item = new Item (bookName,bookAuthor,bookGenre,bookPrice,bookImage);
                            items.add(item);
                        }

                    }
                }
             initializeAdapter();
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
        Intent intent = new Intent(getActivity(), DetailsBook.class);
        intent.putExtra("item_book_image", items.get(position).getImageBook());
        intent.putExtra("item_book_name", items.get(position).getName());
        intent.putExtra("item_book_author", items.get(position).getAuthor());
        intent.putExtra("item_book_price", items.get(position).getPrice());
        intent.putExtra("item_book_ganre", items.get(position).getBookGanre());

        getActivity().startActivity(intent);
    }
}
