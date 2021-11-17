package com.example.nameentrysearch;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Create an array to store the users
    String[] users = new String[50];
    // Create a global counter variable
    int counter = 0;
    // Create an occurances counter (for the search property)
    int occurances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddUserInteract(View view) {

        // Grab interface's inputs (EditText, TextView, and Button)
        EditText etAddUser = (EditText) findViewById(R.id.etAddUser);
        TextView tvAddUser = (TextView) findViewById(R.id.tvAddUser);
        Button btnAddUser = (Button) findViewById(R.id.btnAddUser);

        // Check if the input box is empty
        if (checkEmpty(etAddUser)) {
            // Check that the counter is less than 50
            if (counter < 50) {
                // Append the name to the users array
                users[counter] = etAddUser.getText().toString();
                // Increment the total number of users
                counter++;

                // Inform the interface of the total number of users
                tvAddUser.setText("The user " + etAddUser.getText().toString() + " was added to the list.\n There are " + counter + " users!");
                // Clear the input box
                etAddUser.setText(null);
            } else {
                // Disable the ability to enter text
                etAddUser.setEnabled(false);
                // Clear the input box
                etAddUser.setText(null);
                // Disable the ability to add more users
                btnAddUser.setEnabled(false);
                // Inform the user they cannot add more users
                tvAddUser.setText("You cannot add any more users!");
            }
        }
    }

    public void onSearchUserInteract(View view) {

        // Grab the user inputs
        EditText etSearchUser = (EditText) findViewById(R.id.etSearchUser);
        TextView tvSearchUser = (TextView) findViewById(R.id.tvSearchUser);
        TextView tvNames = (TextView) findViewById(R.id.tvNames);

        // Reset the total occurances
        occurances = 0;
        // Ensure the box is not empty
        if(checkEmpty(etSearchUser)) {
            // Create a loop that will iterate through the total number of users
            for(int i = 0; i < counter; i++) {
                // Check if the user's position is equal to the input
                if(users[i].equalsIgnoreCase(etSearchUser.getText().toString())) {
                    // Increase the total number of occurances
                    occurances++;
                    // Append it to the list
                    tvNames.append(etSearchUser.getText().toString() + ", ");
                }
            }
            // Check if the occurances was greater than 0
            if(occurances > 0) {
                // Inform the user that x occurrances were found with the name y.
                tvSearchUser.setText("There were " + occurances + " occurances found with the name " + etSearchUser.getText().toString());
            } else {
                // Inform the user that the inputed user was never found
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