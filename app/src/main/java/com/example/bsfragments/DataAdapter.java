package com.example.bsfragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bsfragments.interfaces.IBookClickedInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Item> items;
    Context context;
    private IBookClickedInterface iBookClickedInterface;

    DataAdapter(Context context, List<Item> items, IBookClickedInterface iBookClickedInterface) {
        this.items = items;
        this.context = context;
        this.iBookClickedInterface = iBookClickedInterface;
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.book, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.nameView.setText(item.getName());
        holder.authorView.setText(item.getAuthor());
        holder.bookGanreView.setText(item.getBookGanre());
        holder.priceView.setText(item.getPrice());
        holder.descriptionView.setText(item.getDescription());
        Picasso.get().load(item.getImageBook()).into(holder.imageBookView);

        System.out.println(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView imageBookView;
        final TextView nameView, authorView, bookGanreView, priceView, descriptionView;
        ViewHolder(View view){
            super(view);
            imageBookView = (ImageView)view.findViewById(R.id.imageBook);
            nameView = (TextView) view.findViewById(R.id.name);
            authorView = (TextView) view.findViewById(R.id.author);
            bookGanreView = (TextView) view.findViewById(R.id.bookGanre);
            priceView = (TextView) view.findViewById(R.id.price);
            descriptionView = (TextView) view.findViewById(R.id.description);

            imageBookView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iBookClickedInterface.onViewClicked(v, getAdapterPosition());
        }
    }
}
