package com.example.betitarev.betitarev.helper;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.objects.Bet;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Bet> mDataset;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(List<Bet> myDataset) {
        mDataset=myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override



    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if(position==mDataset.size()) {
            holder.mTextView.setText("\n");
            return;
        }

        Bet b=mDataset.get(position);

        holder.mTextView.setText("bettor 1: "+b.getPlayer1().getUser().getName()+", guess: "+b.getPlayer1().getGuessing()+
                "\nbettor 2: "+b.getPlayer2().getUser().getName()+", guess: "+b.getPlayer2().getGuessing()+
                "\npharse: "+b.getPhrase()+
                "\nmoney: "+Integer.toString(b.getFictiveMoney().getAmount()));
        holder.mTextView.setTextColor(Color.BLACK);
        if(position%2==0)
            holder.mTextView.setBackgroundColor(Color.rgb(242, 254, 47));
        else
            holder.mTextView.setBackgroundColor(Color.rgb(0, 61, 7));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size() + 1;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

}

