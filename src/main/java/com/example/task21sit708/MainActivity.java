package com.example.task21sit708;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText inputValue;
    private Button convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        inputValue = findViewById(R.id.inputValue);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        // Set adapter for spinners

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);


        // Set button click listener
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input value
                String inputStr = inputValue.getText().toString();
                if (inputStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double inputValue = Double.parseDouble(inputStr);
                String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
                String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();

                // Perform the conversion and show result
                String result = convertUnits(inputValue, sourceUnit, destinationUnit);
                resultText.setText("Result: " + result);
            }
        });
    }

    // Conversion logic
    private String convertUnits(double value, String sourceUnit, String destinationUnit) {
        // Convert units to lowercase to avoid case sensitivity issues
        sourceUnit = sourceUnit.toLowerCase();
        destinationUnit = destinationUnit.toLowerCase();

        if (sourceUnit.equals(destinationUnit)) {
            return "Same unit, no conversion needed.";
        }

        double result;

        // Length conversions
        switch (sourceUnit) {
            case "inches":
                if (destinationUnit.equals("centimeters")) result = value * 2.54;
                else return "Conversion not supported.";
                break;
            case "feet":
                if (destinationUnit.equals("meters")) result = value * 0.3048;
                else return "Conversion not supported.";
                break;
            case "yards":
                if (destinationUnit.equals("meters")) result = value * 0.9144;
                else return "Conversion not supported.";
                break;
            case "miles":
                if (destinationUnit.equals("kilometers")) result = value * 1.60934;
                else return "Conversion not supported.";
                break;
            case "centimeters":
                if (destinationUnit.equals("inches")) result = value / 2.54;
                else return "Conversion not supported.";
                break;
            case "meters":
                if (destinationUnit.equals("feet")) result = value / 0.3048;
                else if (destinationUnit.equals("yards")) result = value / 0.9144;
                else return "Conversion not supported.";
                break;
            case "kilometers":
                if (destinationUnit.equals("miles")) result = value / 1.60934;
                else return "Conversion not supported.";
                break;

            // Weight conversions
            case "pounds":
                if (destinationUnit.equals("kilograms")) result = value * 0.453592;
                else return "Conversion not supported.";
                break;
            case "ounces":
                if (destinationUnit.equals("grams")) result = value * 28.3495;
                else return "Conversion not supported.";
                break;
            case "kilograms":
                if (destinationUnit.equals("pounds")) result = value / 0.453592;
                else return "Conversion not supported.";
                break;
            case "grams":
                if (destinationUnit.equals("ounces")) result = value / 28.3495;
                else return "Conversion not supported.";
                break;

            // Temperature conversions
            case "celsius":
                if (destinationUnit.equals("fahrenheit")) result = (value * 9 / 5) + 32;
                else if (destinationUnit.equals("kelvin")) result = value + 273.15;
                else return "Conversion not supported.";
                break;
            case "fahrenheit":
                if (destinationUnit.equals("celsius")) result = (value - 32) * 5 / 9;
                else if (destinationUnit.equals("kelvin")) result = (value - 32) * 5 / 9 + 273.15;
                else return "Conversion not supported.";
                break;
            case "kelvin":
                if (destinationUnit.equals("celsius")) result = value - 273.15;
                else if (destinationUnit.equals("fahrenheit")) result = (value - 273.15) * 9 / 5 + 32;
                else return "Conversion not supported.";
                break;

            default:
                return "Conversion not supported.";
        }

        return String.format("%.2f", result);
    }
}
