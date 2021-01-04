package com.example.hostelmealmanagement.get_all_member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmealmanagement.R;
import com.example.hostelmealmanagement.expense.GetAllExpenseCustomAdapter;
import com.example.hostelmealmanagement.expense.GetAllExpenseData;

import java.util.List;

public class MemberListCustomAdapter extends RecyclerView.Adapter<MemberListCustomAdapter.MyViewHolder> {

    Context context;
    String token;
    List<GetAllMemberData> getAllMemberDataList;
    OnContactClickListener onContactClickListener;

    public MemberListCustomAdapter(Context context, String token, List<GetAllMemberData> getAllMemberDataList, OnContactClickListener onContactClickListener) {
        this.context = context;
        this.token = token;
        this.getAllMemberDataList = getAllMemberDataList;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public MemberListCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_list_item,parent,false);
        return new MemberListCustomAdapter.MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListCustomAdapter.MyViewHolder holder, int position) {
        holder.marketerTextView.setText(getAllMemberDataList.get(position).getName());
    }

    @Override
    public int getItemCount(){
        return getAllMemberDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView marketerTextView;
        OnContactClickListener onContactClickListener;
        public MyViewHolder(@NonNull View itemView,OnContactClickListener onContactClickListener) {
            super(itemView);
            marketerTextView=itemView.findViewById(R.id.marketerNameTextViewId);
            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());

        }
    }

    public  interface  OnContactClickListener{
        void onContactClick(int position);
    }
}
