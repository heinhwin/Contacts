package com.hein.contacts;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    private List<Contact> dataList;
    private Activity context;
    private ContactDatabase contactDatabase;

    //Create constructor
    public MainAdapter(Activity context, List<Contact> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize main data
        Contact data = dataList.get(position);
        //Initialize database
        contactDatabase = ContactDatabase.getInstance(context);
        //Set contact fields on text view
        holder.tvFirstName.setText(data.getFirstName());
        holder.tvLastName.setText(data.getLastName());
        holder.tvAddress.setText(data.getAddress());
        holder.tvCity.setText(data.getCity());
        holder.tvAge.setText(data.getAge());

        holder.edit_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Initialize contact data
                Contact d = dataList.get(holder.getAdapterPosition());
                //Get contact field
                String f = d.getFirstName();
                String l = d.getLastName();
                String a = d.getAddress();
                String c = d.getCity();
                String ag = d.getAge();

                //Create dialog
                Dialog dialog = new Dialog(context);
                //Set content view
                dialog.setContentView(R.layout.dialog_update);
                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //Initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //Set layout
                dialog.getWindow().setLayout(width,height);
                //Show dialog
                dialog.show();

                //Initialize and assign variable
                EditText editFirstName = dialog.findViewById(R.id.edit_FirstName);
                EditText editLastName = dialog.findViewById(R.id.edit_LastName);
                EditText editAddress = dialog.findViewById(R.id.edit_Address);
                EditText editCity = dialog.findViewById(R.id.edit_City);
                EditText editAge = dialog.findViewById(R.id.edit_Age);
                Button updateBtn = dialog.findViewById(R.id.update_btn);

                //Set contact field on edit text
                editFirstName.setText(f);
                editLastName.setText(l);
                editAddress.setText(a);
                editCity.setText(c);
                editAge.setText(ag);

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Dismiss dialog
                        dialog.dismiss();
                        //Get update text from edit text
                        String uFirstName = editFirstName.getText().toString().trim();
                        String uLastName = editLastName.getText().toString().trim();
                        String uAddress = editAddress.getText().toString().trim();
                        String uCity = editCity.getText().toString().trim();
                        String uAge = editAge.getText().toString().trim();
                        d.setFirstName(uFirstName);
                        d.setLastName(uLastName);
                        d.setAddress(uAddress);
                        d.setCity(uCity);
                        d.setAge(uAge);
                        //Update text in database
                        contactDatabase.getContactDAO().update(d);
                        //Notify when data is updated
                        dataList.clear();
                        dataList.addAll(contactDatabase.getContactDAO().getAllContact());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize contact data
                Contact d = dataList.get(holder.getAdapterPosition());
                //Delete contact from database
                contactDatabase.getContactDAO().delete(d);
                //Notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView tvFirstName, tvLastName, tvAddress, tvCity, tvAge;
        ImageView edit_btn, delete_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign contact variable
            tvFirstName = itemView.findViewById(R.id.tv_FirstName);
            tvLastName = itemView.findViewById(R.id.tv_LastName);
            tvAddress = itemView.findViewById(R.id.tv_Address);
            tvCity = itemView.findViewById(R.id.tv_City);
            tvAge = itemView.findViewById(R.id.tv_Age);
            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
