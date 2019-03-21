package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shneddy.dietdiary.R;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class EditorEntries extends AppCompatActivity {

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_entries);
    }
}
