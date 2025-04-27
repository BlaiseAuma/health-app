package com.example.trial3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterPatient extends AppCompatActivity {
    private EditText nameEditText;
    private Spinner ageSpinner, genderSpinner, specificationSpinner;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);

        nameEditText = findViewById(R.id.nameEditText);
        ageSpinner = findViewById(R.id.ageSpinner);
        genderSpinner = findViewById(R.id.genderSpinner);
        specificationSpinner = findViewById(R.id.specificationSpinner);
        Button registerButton = findViewById(R.id.secureButton);

        databaseHelper = new DatabaseHelper(this);

        // Populate Spinners using string arrays from strings.xml
        setupSpinner(ageSpinner, R.array.age_array);
        setupSpinner(genderSpinner, R.array.gender_array);
        setupSpinner(specificationSpinner, R.array.specification_array);

        registerButton.setOnClickListener(v -> registerPatient());
    }

    private void setupSpinner(Spinner spinner, int arrayResource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void registerPatient() {
        String name = nameEditText.getText().toString().trim();
        String age = ageSpinner.getSelectedItem().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        String specification = specificationSpinner.getSelectedItem().toString();

        if (!name.isEmpty()) {
            databaseHelper.registerPatient(name, age, gender, specification);

        }
    }
}
