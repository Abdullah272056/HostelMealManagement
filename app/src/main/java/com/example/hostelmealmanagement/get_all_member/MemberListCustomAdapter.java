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

    public MemberListCustomAdapter(Context context, String token, List<GetAllMemberData> getAllMemberDataList) {
        this.context = context;
        this.token = token;
        this.getAllMemberDataList = getAllMemberDataList;
    }

    @NonNull
    @Override
    public MemberListCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_list_item,parent,false);
        return new MemberListCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListCustomAdapter.MyViewHolder holder, int position) {
        holder.marketerTextView.setText(getAllMemberDataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return getAllMemberDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView marketerTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marketerTextView=itemView.findViewById(R.id.marketerNameTextViewId);
        }
    }
}
