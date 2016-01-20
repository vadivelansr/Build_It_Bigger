package com.example.vadivelansr.jokeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
/**
 *Activity which provides Jokes 
 */
public class JokeActivity extends AppCompatActivity {
    public final static String INTENT_JOKE = "INTENT_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        String joke = getIntent().getStringExtra(INTENT_JOKE);
        TextView textView = (TextView)findViewById(R.id.textview_joke);
        textView.setText(joke);
    }

}
