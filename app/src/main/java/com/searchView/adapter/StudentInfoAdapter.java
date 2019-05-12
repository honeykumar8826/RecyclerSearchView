package com.searchView.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.searchView.R;
import com.searchView.modal.StudentInfoModal;

import java.util.ArrayList;
import java.util.List;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.ViewHolder> implements Filterable {
    private final String TAG = "abc";
    private List<StudentInfoModal> modalList;
    private List<StudentInfoModal> fullStudentInfoList;
    private Filter filterItems = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StudentInfoModal> studentInfoModalList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
                studentInfoModalList.addAll(fullStudentInfoList);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (StudentInfoModal item : fullStudentInfoList) {
                    if (item.getStudentAge().toLowerCase().contains(filterPattern)) {
                        studentInfoModalList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = studentInfoModalList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modalList.clear();
            modalList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public StudentInfoAdapter(List<StudentInfoModal> modalList) {
        this.modalList = modalList;
        fullStudentInfoList = new ArrayList<>();
        fullStudentInfoList.addAll(modalList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i(TAG, "onBindViewHolder: ");
        StudentInfoModal studentInfoModalList = modalList.get(i);
        viewHolder.tvUserName.setText(studentInfoModalList.getStudentName());
        viewHolder.tvUserAge.setText(studentInfoModalList.getStudentAge());
    }

    @Override
    public int getItemCount() {
        return modalList.size();
    }

    @Override
    public Filter getFilter() {
        return filterItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName, tvUserAge;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_name_value);
            tvUserAge = itemView.findViewById(R.id.tv_age_value);

        }
    }
}
