package com.example.nas19_journal;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_entry, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.rowTitle);
        TextView timestamp = view.findViewById(R.id.rowTimeStamp);
        TextView mood = view.findViewById(R.id.rowMood);
        ImageView image = view.findViewById(R.id.rowImage);

        String rowTitle = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TITLE));
        int rowMood = cursor.getInt(cursor.getColumnIndex(EntryDatabase.COLUMN_MOOD));
        String rowTimestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TIMESTAMP));

        title.setText(rowTitle);
        timestamp.setText(rowTimestamp);
        switch (rowMood) {
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
