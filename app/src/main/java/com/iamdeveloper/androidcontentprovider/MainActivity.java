package com.iamdeveloper.androidcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);


        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Log.i("uri",uri.toString());



        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);





        if(cursor != null){
            Log.i("cursor count",cursor.getColumnCount()+"");
            int indexName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int indexNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            cursor.moveToFirst();
            do {
                String name = cursor.getString(indexName);
                String number = cursor.getString(indexNumber);
                textView.append("name : " + name);
                textView.append("\n");
                textView.append("number : " + number);
                textView.append("\n");
            } while (cursor.moveToNext());

            cursor.close();
        }

    }
}
