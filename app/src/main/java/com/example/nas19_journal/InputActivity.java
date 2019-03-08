package com.example.nas19_journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private int mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mood = 0;
    }

    public void moodClicked(View v) {
        ViewGroup vg = ((ViewGroup)v.getParent());
        mood = vg.indexOfChild(v) - 2;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View vm = vg.getChildAt(i);
            vm.setBackground(null);
        }
        v.setBackgroundResource(R.drawable.border);
    }

    public void addEntry(View view) {
        String title = ((EditText)findViewById(R.id.inputTitle)).getText().toString();
        String content = ((EditText)findViewById(R.id.inputContent)).getText().toString();
        EntryDatabase.getInstance(this).insert(new JournalEntry(0, mood, title, content, ""));
        finish();
    }
}
