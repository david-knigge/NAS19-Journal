package com.example.nas19_journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createJournalEntry();
            }
        });

        setAdapter();
        setOnClickListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setAdapter();
    }

    private class EntryClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor e = (Cursor) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("entry", new JournalEntry(
                    e.getInt(e.getColumnIndex(EntryDatabase.COLUMN_ID)),
                    e.getInt(e.getColumnIndex(EntryDatabase.COLUMN_MOOD)),
                    e.getString(e.getColumnIndex(EntryDatabase.COLUMN_TITLE)),
                    e.getString(e.getColumnIndex(EntryDatabase.COLUMN_CONTENT)),
                    e.getString(e.getColumnIndex(EntryDatabase.COLUMN_TIMESTAMP)))
            );
        }
    }

    private class EntryLongClickListener implements ListView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor entry = (Cursor) parent.getItemAtPosition(position);
            db.delete(entry.getInt(entry.getColumnIndex(EntryDatabase.COLUMN_ID)));
            return false;
        }
    }

    public void createJournalEntry() {
        Intent intent = new Intent(this, InputActivity.class);
        startActivityForResult(intent, 1);
    }

    public void setAdapter () {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        ((ListView)findViewById(R.id.entriesList)).setAdapter(new EntryAdapter(this, db.selectAll()));
    }

    public void setOnClickListeners() {
        ListView view = findViewById(R.id.entriesList);
        view.setOnItemLongClickListener(new EntryLongClickListener());
        view.setOnItemClickListener(new EntryClickListener());
    }

}
