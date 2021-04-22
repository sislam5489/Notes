package edu.fordham.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteDatabase database;
    NoteDao noteDao;
    RecyclerView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = NoteDatabase.getDatabase(this);
        noteDao = database.NoteDao();

        notesList = findViewById(R.id.noteList);
        notesList.setLayoutManager(new LinearLayoutManager(this));
        notesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> notes = noteDao.getAll();
        NotesAdapter adapter = new NotesAdapter(notes, noteDao);
        notesList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.new_note:
                Note.open(this,-1,true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}