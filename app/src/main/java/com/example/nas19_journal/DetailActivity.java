package com.example.nas19_journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        renderDetails((JournalEntry) getIntent().getSerializableExtra("entry"));
    }

    public void renderDetails(JournalEntry entry) {
        TextView title = findViewById(R.id.detailTitle);
        TextView content = findViewById(R.id.detailContent);
        TextView timestamp = findViewById(R.id.detailTimestamp);
        ImageView image = findViewById(R.id.detailMoodImage);
        TextView mood = findViewById(R.id.detailMood);

        title.setText(entry.getTitle());
        timestamp.setText(entry.getTimestamp());
        content.setText(entry.getContent());
        switch (entry.getMood()) {
            case -2:
                image.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
                mood.setText("Very Unhappy");
                break;
            case -1:
                image.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
                mood.setText("Unhappy");
                break;
            case 0:
                image.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
                mood.setText("Okay");
                break;
            case 1:
                image.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp);
                mood.setText("Happy");
                break;
            case 2:
                image.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp);
                mood.setText("Very Happy");
                break;
        }
    }
}
