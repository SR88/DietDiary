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
/**
 * Created By Seth Sneddon Mar 2019
 */
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

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
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

    /**
     * Setups of the menu icon at the top right of the screen to save the item which is being
     * edited or created
     * @param menu which menu on the screen
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic_save, menu);
        return true;
    }

    /**
     * This directs what the application to do based on which item in the menu at the top right
     * of the screen is selected.  At this moment there is only the save option
     * @param item in menu selected
     * @return
     */
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

    /**
     * Handles validation of inputs by the user.  It passes the data back to the previous activity
     * which called this activity to either insert or update the appropriate record.
     *
     * THROWS EXCEPTION FOR REQUIREMENT OF CAPSTONE
     */
    private void saveType() throws NoValueInputException {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();

        // create toast message if there is an error
        if (name.trim().isEmpty()) {
            throw new NoValueInputException("Please make sure to enter in a name for your food type");
        } else {
            // return data to appropriate activity
            Intent data = new Intent();
            data.putExtra(EXTRA_FOODTYPE, name);
            data.putExtra(EXTRA_FOODTYPE_DESCRIPTION, description);
            data.putExtra(EXTRA_FOODTYPE_ID, previousId);

            setResult(RESULT_OK, data);
            finish();
        }
    }

}
