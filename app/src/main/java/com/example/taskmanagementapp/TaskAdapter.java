package com.example.taskmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> mTaskList=new ArrayList<>();
    private Context mContext;
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_task,parent,false);
        return new TaskViewHolder(view);
    }

    public TaskAdapter(Context context, List<Task> TaskList) {
        this.mContext = context;
        this.mTaskList = TaskList;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task=mTaskList.get(position);
        holder.mTitle.setText(task.getTitle());
        holder.mDesc.setText(task.getDescription());
        holder.mPriority.setText(task.getPriority());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle,mDesc,mPriority;
        public TaskViewHolder(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.listTaskTitle);
            mDesc=itemView.findViewById(R.id.listTaskDesc);
            mPriority=itemView.findViewById(R.id.listTaskDate);

        }
    }
}

