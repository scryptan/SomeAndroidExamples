package com.example.todo;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<TodoCard> values;
    private Context context;
    private Gson gson;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CheckBox checkBox;
        public Button deleteBtn;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            deleteBtn = (Button) v.findViewById(R.id.deleteBTN);
        }
    }

    public void add(TodoCard item) {
        values.add(0, item);
        notifyItemInserted(0);
        DataItems content = new DataItems();
        content.setTodoCards(values);
        SaveSystem.SaveState(context, gson.toJson(content));
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<TodoCard> myDataset, Context context) {
        values = myDataset;
        this.context = context;
        gson = new Gson();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.todo_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("TAG", "onBindViewHolder: "+position);
        TodoCard todoCard = values.get(position);
        holder.checkBox.setText(todoCard.Text);
        holder.checkBox.setChecked(todoCard.Checked);
        holder.checkBox.setOnClickListener(v -> {
            todoCard.Checked = holder.checkBox.isChecked();
            DataItems content = new DataItems();
            content.setTodoCards(values);
            SaveSystem.SaveState(context, gson.toJson(content));
        });
        holder.deleteBtn.setOnClickListener(v -> {
            remove(values.indexOf(todoCard));
            DataItems content = new DataItems();
            content.setTodoCards(values);
            SaveSystem.SaveState(context, gson.toJson(content));
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
