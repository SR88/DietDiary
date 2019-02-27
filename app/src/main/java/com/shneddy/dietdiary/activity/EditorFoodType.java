package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.shneddy.dietdiary.R;

public class EditorFoodType extends AppCompatActivity {
    public static final String EXTRA_FOODTYPE =
            "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE";
    public static final String EXTRA_FOODTYPE_DESCRIPTION =
            "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_DESCRIPTION";
    private EditText editTextName;
    private EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food_type);

        editTextName = findViewById(R.id.edittext_name);
        editTextDescription = findViewById(R.id.edittext_description);

        setTitle("Edit a Food Type");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_save:
                saveType();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveType() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this,"Please make sure to enter in a name for your food type",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_FOODTYPE, name);
        data.putExtra(EXTRA_FOODTYPE_DESCRIPTION, description);

        setResult(RESULT_OK, data);
        finish();
    }
}
