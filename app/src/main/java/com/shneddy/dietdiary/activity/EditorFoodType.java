package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.exception.NoValueInputException;

public class EditorFoodType extends AppCompatActivity {
    public static final String EXTRA_FOODTYPE =
            "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE";
    public static final String EXTRA_FOODTYPE_DESCRIPTION =
            "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_DESCRIPTION";
    public static final String EXTRA_FOODTYPE_ID =
            "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_ID";
    private EditText editTextName;
    private EditText editTextDescription;
    private int previousId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food_type);

        Intent intent = getIntent();

        editTextName = findViewById(R.id.edittext_name);
        editTextDescription = findViewById(R.id.edittext_description);

        if (getIntent().hasExtra(AllFoodTypes.FOODTYPE_ID)){
            setTitle("Edit your Food Type");
            editTextName.setText(intent.getStringExtra(AllFoodTypes.FOODTYPE_NAME));
            editTextDescription.setText(intent.getStringExtra(AllFoodTypes.FOODTYPE_DESCRIPTION));
            previousId = intent.getIntExtra(AllFoodTypes.FOODTYPE_ID, -1);
        } else {
            setTitle("Create a Food Type");
        }
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
                try {
                    saveType();
                } catch (NoValueInputException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveType() throws NoValueInputException {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();

        if (name.trim().isEmpty()) {
            throw new NoValueInputException("Please make sure to enter in a name for your food type");
        } else {

            Intent data = new Intent();
            data.putExtra(EXTRA_FOODTYPE, name);
            data.putExtra(EXTRA_FOODTYPE_DESCRIPTION, description);
            data.putExtra(EXTRA_FOODTYPE_ID, previousId);

            setResult(RESULT_OK, data);
            finish();
        }
    }

}
