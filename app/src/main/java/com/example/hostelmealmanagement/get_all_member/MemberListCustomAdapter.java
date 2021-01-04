package com.example.hostelmealmanagement.get_all_member;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmealmanagement.expense.GetAllExpenseData;

import java.util.List;

public class MemberListCustomAdapter extends RecyclerView.Adapter<MemberListCustomAdapter.MyViewHolder> {

    Context context;
    String token;
    List<GetAllMemberData> getAllMemberDataList;

    public MemberListCustomAdapter(Context context, String token, List<GetAllMemberData> getAllMemberDataList) {
        this.context = context;
        this.token = token;
        this.getAllMemberDataList = getAllMemberDataList;
    }

    @NonNull
    @Override
    public MemberListCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListCustomAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
