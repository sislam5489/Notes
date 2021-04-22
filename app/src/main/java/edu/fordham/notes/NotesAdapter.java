package edu.fordham.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteItemViewHolder> {
    List<Note> data;
    NoteDao noteDao;

    NotesAdapter(List<Note> l, NoteDao dao){
        data = l;
        noteDao = dao;
    }

    @NonNull
    @Override
    public NoteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new NoteItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteItemViewHolder holder, int position) {
        holder.updateView(this, data,position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
