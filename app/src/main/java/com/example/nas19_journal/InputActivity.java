package com.example.nas19_journal;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private int mood;

    /** On creation, check if the mood is already set so as to correctly support rotation. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mood = 0;
        if (savedInstanceState != null) {
            JournalEntry entry = (JournalEntry) savedInstanceState.getSerializable("entry");
            if (entry != null) {
                mood = entry.getMood();
            }
        }
        setMoodImage();
        setTitle("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("entry", bundleInput());
    }

    /** Set the correct mood picture based on which mood has been clicked.
     * -2 = v unhappy, -1 = unhappy, 0 = neutral, 1 = happy, 2 = v. happy */
    public void moodClicked(View v) {
        ViewGroup vg = ((ViewGroup)v.getParent());
        mood = vg.indexOfChild(v) - 2;
        setMoodImage();
    }

    public void addEntry(View view) {
        EntryDatabase.getInstance(this).insert(bundleInput());
        finish();
    }

    /** Creates a new JournalEntry based on the current values of textinputs in the activity and
     * the mood. */
    public JournalEntry bundleInput() {
        String title = ((EditText)findViewById(R.id.inputTitle)).getText().toString();
        String content = ((EditText)findViewById(R.id.inputContent)).getText().toString();
        return new JournalEntry(0, mood, title, content, "");
    }

    /** Sets a border around the correct mood image. */
    public void setMoodImage() {
        ViewGroup vg = findViewById(R.id.moodContainer);
        for (int i = 0; i < vg.getChildCount(); i++) {
            View vm = vg.getChildAt(i);
            vm.setBackground(null);
        }
        vg.getChildAt(mood + 2).setBackgroundResource(R.drawable.border);
    }
}
