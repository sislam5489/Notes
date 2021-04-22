package edu.fordham.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {

    EditText note_title, note_content;
    boolean isNewNote;
    int nid;
    Note note;
    NoteDatabase db;
    NoteDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        note_title = findViewById(R.id.note_title);
        note_content = findViewById(R.id.note);

        db = NoteDatabase.getDatabase(this);
        dao = db.NoteDao();

        isNewNote = getIntent().getBooleanExtra("newNote", true);
        nid = getIntent().getIntExtra("id",-1);

        if(!isNewNote){
            note = dao.findById(nid);
            note_title.setText(note.title);
            note_content.setText(note.content);
        }else{
            note = new Note();
        }
    }

    public void saveNote(View view) {
        String title = note_title.getText().toString();
        String content = note_content.getText().toString();

        if(title.isEmpty()){
            Toast.makeText(view.getContext(),"Title must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        note.title = title;
        note.content = content;

        if(isNewNote){
            dao.insertNote(note);
        }else{
            dao.updateNote(note);
        }
        finish();
    }
}