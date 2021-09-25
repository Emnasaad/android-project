package com.example.biblio.adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.biblio.R;
import com.example.biblio.activity.FileActivity;
import com.example.biblio.activity.ReadRespanceActivity;
import com.example.biblio.utils.Book;
import com.example.biblio.utils.Question;
import com.example.biblio.utils.Repance;
import com.example.biblio.utils.TinyDB;
import com.google.firebase.database.*;

import java.io.FileNotFoundException;
import java.util.List;


public class RecyclViewAdapter extends RecyclerView.Adapter<RecyclViewAdapter.ViewHolder> {

    private  List<Book> mValues;
    private  List<Question> questionList;
    private  List<Repance> reponseList;



    public RecyclViewAdapter(List<Book> items, List<Question> list,List<Repance> list1) {
        mValues = items;
        questionList = list;
        reponseList = list1;
    }

    public RecyclViewAdapter() {
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_book_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.mTitleView.setText(mValues.get(position).getTitle());
        Glide.with(holder.mView.getContext())
                .load(mValues.get(position).getUrl_img()) // image url
                .placeholder(R.drawable.logo) // any placeholder to load at start
                .error(R.drawable.logo)  // any image in case of error
                .into(holder.mCoverView);



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new TinyDB(holder.mView.getContext()).putString("title_file", holder.mTitleView.getText().toString());
                new TinyDB(holder.mView.getContext()).putString("url_image", mValues.get(position).getUrl_img());
                new TinyDB(holder.mView.getContext()).putString("url_pdf", mValues.get(position).getUrl_file());



              // Log.i("Hello", "size of list "+questionList.size());

               new TinyDB(holder.mView.getContext()).putString("question1",questionList.get(position).getQuestion1().toString());
               new TinyDB(holder.mView.getContext()).putString("question2",questionList.get(position).getQuestion2().toString());
                new TinyDB(holder.mView.getContext()).putString("question3",questionList.get(position).getQuestion3().toString());
                new TinyDB(holder.mView.getContext()).putString("question4",questionList.get(position).getQuestion4().toString());
                new TinyDB(holder.mView.getContext()).putString("question5",questionList.get(position).getQuestion5().toString());



          // Log.i("ppppp", "num pos: "+position);

       // Log.i("hiiiii", "cc "+questionList.get(1).getQuestion1().toString());
                new TinyDB(holder.mView.getContext()).putString("rep1",reponseList.get(position).getRep1().toString());
                new TinyDB(holder.mView.getContext()).putString("rep2",reponseList.get(position).getRep2().toString());
                new TinyDB(holder.mView.getContext()).putString("rep3",reponseList.get(position).getRep3().toString());
                new TinyDB(holder.mView.getContext()).putString("rep4",reponseList.get(position).getRep4().toString());
                new TinyDB(holder.mView.getContext()).putString("rep5",reponseList.get(position).getRep5().toString());




                v.getContext().startActivity(new Intent(v.getContext(), ReadRespanceActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mCoverView;
        public final TextView mTitleView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCoverView = view.findViewById(R.id.cover);
            mTitleView = view.findViewById(R.id.tv_title);

        }
    }

}