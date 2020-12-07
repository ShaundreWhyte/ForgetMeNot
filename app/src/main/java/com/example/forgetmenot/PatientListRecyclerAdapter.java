package com.example.forgetmenot;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forgetmenot_softwaredev.R;

import java.util.ArrayList;

public class PatientListRecyclerAdapter extends RecyclerView.Adapter<PatientListRecyclerAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(String text) ;
    }
    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<ItemPatient> items = null;
    private ArrayList<ItemPatient> checkedItems = null;

    public PatientListRecyclerAdapter(ArrayList<ItemPatient> list) {
        items = list;
        checkedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ItemPatient item = items.get(position);
        holder.name.setText(item.patientName);
        holder.gender.setText(item.gender);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox box = (CheckBox)v;
                if(box.isChecked())
                    checkedItems.add(item);
                else
                    checkedItems.remove(item);
                if(mListener != null)
                    mListener.onItemClick(holder.checkBox.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView name;
        TextView gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.chkbox_patient);
            name = itemView.findViewById(R.id.tv_patient_name);
            gender = itemView.findViewById(R.id.tv_patient_gender);
        }
    }

    public void setItemList(ArrayList<ItemPatient> list) {
        items = list;
        notifyDataSetChanged();
    }

    public ArrayList<ItemPatient> getCheckedItems() {
        return checkedItems;
    }
}
