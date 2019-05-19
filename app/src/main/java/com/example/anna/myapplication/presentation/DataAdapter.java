package com.example.anna.myapplication.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Person> persons;
    private OnClickListener onClickListener;

    public DataAdapter(OnClickListener onClickListener) {
        this.persons = new ArrayList<>();
        this.onClickListener = onClickListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.person_layout,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        final Person person = persons.get(position);

        holder.nameView.setText(person.getName());
        holder.itemView.setOnClickListener(v -> onClickListener.onClick(person.getId()));
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    interface OnClickListener {
        void onClick(long personId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;

        private ViewHolder(final View itemView){
            super(itemView);
            nameView = itemView.findViewById(R.id.person_name);
        }
    }
}