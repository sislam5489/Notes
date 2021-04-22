package edu.fordham.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitleView;
    public NoteItemViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitleView = itemView.findViewById(R.id.title_note);
    }

    public void updateView(NotesAdapter notesAdapter, List<Note> data, int position) {
        Note note = data.get(position);
        noteTitleView.setText(note.title);
        noteTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(note.title);
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Note.open(view.getContext(), note.nid, false);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notesAdapter.noteDao.deleteNote(note);
                        notesAdapter.data.remove(note);
                        notesAdapter.notifyItemRemoved(position);
                    }
                });
                builder.create().show();
            }
        });

    }
}
