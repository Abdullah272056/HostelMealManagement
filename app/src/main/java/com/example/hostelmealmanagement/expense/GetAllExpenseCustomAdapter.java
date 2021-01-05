package com.example.hostelmealmanagement.expense;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmealmanagement.ExpensesActivity;
import com.example.hostelmealmanagement.HomePageDataResponse;
import com.example.hostelmealmanagement.R;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllExpenseCustomAdapter extends RecyclerView.Adapter<GetAllExpenseCustomAdapter.MyViewHolder> {

    Context context;
    String token;
    List<GetAllExpenseData> getAllExpenseDataList;

    ApiInterface apiInterface;


    public GetAllExpenseCustomAdapter(Context context, String token, List<GetAllExpenseData> getAllExpenseDataList) {
        this.context = context;
        this.token = token;
        this.getAllExpenseDataList = getAllExpenseDataList;
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

    }

    @NonNull
    @Override
    public GetAllExpenseCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.expense_recycler_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull GetAllExpenseCustomAdapter.MyViewHolder holder, final int position) {
        holder.marketerTextView.setText(String.valueOf(getAllExpenseDataList.get(position).getGetAllExpenseMarketerData().getName()));

        String string=String.valueOf(getAllExpenseDataList.get(position).getDateAdded());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy ", Locale.forLanguageTag(string));
        String time = df.format(new Date());
        holder.dateTextView.setText(time);

        holder.nameTextView.setText(String.valueOf(getAllExpenseDataList.get(position).getName()));
        holder.typeTextView.setText(String.valueOf(getAllExpenseDataList.get(position).getType()));
        holder.costTextView.setText(String.valueOf(getAllExpenseDataList.get(position).getCost()));

        holder.deleteExpenseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExpense(position);
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getAllExpenseDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView marketerTextView,dateTextView,nameTextView,typeTextView,costTextView;
        ImageView editImageView,deleteExpenseImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marketerTextView=itemView.findViewById(R.id.marketerTextViewId);
            dateTextView=itemView.findViewById(R.id.dateTextViewId);
            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            typeTextView=itemView.findViewById(R.id.typeTextViewId);
            costTextView=itemView.findViewById(R.id.costTextViewId);

            deleteExpenseImageView=itemView.findViewById(R.id.deleteExpenseImageViewId);


        }
    }



    private  void  deleteExpense(final int position1){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            apiInterface.deleteExpense("Bearer "+token,getAllExpenseDataList.get(position1).getId())
                    .enqueue(new Callback<HomePageDataResponse>() {
                        @Override
                        public void onResponse(Call<HomePageDataResponse> call, Response<HomePageDataResponse> response) {
                            if (response.code()==200){
                                Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.code()==503){
                                Toast.makeText(context, "Service Unavailable", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "fail deleted", Toast.LENGTH_SHORT).show();
                            }
                            ((ExpensesActivity)context).getAllExpense();
                        }

                        @Override
                        public void onFailure(Call<HomePageDataResponse> call, Throwable t) {
                            Toast.makeText(context, "fail deleted! try again", Toast.LENGTH_SHORT).show();
                            Log.e("asd",t.getMessage());
                        }
                    });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
