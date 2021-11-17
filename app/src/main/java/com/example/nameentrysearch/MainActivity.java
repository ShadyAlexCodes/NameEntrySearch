package com.example.nameentrysearch;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] users = new String[50];
    int counter = 0;
    int occurances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddUserInteract(View view) {

        EditText etAddUser = (EditText) findViewById(R.id.etAddUser);
        TextView tvAddUser = (TextView) findViewById(R.id.tvAddUser);
        Button btnAddUser = (Button) findViewById(R.id.btnAddUser);

        if (checkEmpty(etAddUser)) {
            if (counter < 50) {
                users[counter] = etAddUser.getText().toString();
                counter++;

                tvAddUser.setText("The user " + etAddUser.getText().toString() + " was added to the list.\n There are " + counter + " users!");
                etAddUser.setText(null);
            } else {
                etAddUser.setEnabled(false);
                etAddUser.setText(null);
                btnAddUser.setEnabled(false);
                tvAddUser.setText("You cannot add any more users!");
            }
        }
    }

    public void onSearchUserInteract(View view) {
        EditText etSearchUser = (EditText) findViewById(R.id.etSearchUser);
        TextView tvSearchUser = (TextView) findViewById(R.id.tvSearchUser);
        TextView tvNames = (TextView) findViewById(R.id.tvNames);
        Button btnSearchUser = (Button) findViewById(R.id.btnSearchUser);

        occurances = 0;
        if(checkEmpty(etSearchUser)) {
            for(int i = 0; i < counter; i++) {
                if(users[i].equalsIgnoreCase(etSearchUser.getText().toString())) {
                    occurances++;
                    tvNames.append(etSearchUser.getText().toString() + ", ");
                }
            }
            if(occurances > 0) {
                tvSearchUser.setText("There were " + occurances + " occurances found with the name " + etSearchUser.getText().toString());
            } else {
                tvSearchUser.setText("The name " + etSearchUser.getText().toString() + " was never found!");
            }

        }

    }

    private boolean checkEmpty(EditText input) {
        // Check if the inputted action is null.
        if (TextUtils.isEmpty(input.getText().toString())) {
            // Define the error
            input.setError("Please enter a user");
            return false;
        }
        return true;
    }
}