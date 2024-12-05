package com.example.taskridebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

//    }
//}
        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                EditText distanceInput = findViewById(R.id.distanceInput);
                EditText ridersInput = findViewById(R.id.ridersInput);
                Spinner fuelTypeSpinner = findViewById(R.id.fuelTypeSpinner);
                Spinner trafficSpinner = findViewById(R.id.trafficSpinner);
                EditText idleTimeInput = findViewById(R.id.idleTimeInput);
                Spinner rideTimeSpinner = findViewById(R.id.rideTimeSpinner);

                double distance = Double.parseDouble(distanceInput.getText().toString());
                int riders = Integer.parseInt(ridersInput.getText().toString());
                String fuelType = fuelTypeSpinner.getSelectedItem().toString();
                String traffic = trafficSpinner.getSelectedItem().toString();
                int idleTime = idleTimeInput.getText().toString().isEmpty() ? 0 : Integer.parseInt(idleTimeInput.getText().toString());
                boolean isNight = rideTimeSpinner.getSelectedItem().toString().equals("Night");

                if (riders <= 0) {
                    Toast.makeText(MainActivity.this, "Number of riders must be at least 1.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate emissions
                double emissions = calculateEmissions(distance, riders, fuelType, traffic, idleTime, isNight);

//                // Display result
//                TextView resultText = findViewById(R.id.resultText);
//                resultText.setText("CO₂ Savings: " + emissions + " grams");

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("calculatedEmissions", emissions);
                startActivity(intent);

            }
        });
    }

    private double calculateEmissions(double distance, int riders, String fuelType, String traffic, int idleTime, boolean isNight) {
        double baseEmission = 251.0;

        // Fuel adjustment
        double fuelAdjustment;
        switch (fuelType) {
            case "Diesel":
                fuelAdjustment = 1.15;
                break;
            case "Petrol":
                fuelAdjustment = 1.0;
                break;
            default: // EV
                fuelAdjustment = 0.0;
                break;
        }

        // Traffic adjustment
        double trafficAdjustment;
        switch (traffic) {
            case "Moderate":
                trafficAdjustment = 1.10;
                break;
            case "Heavy":
                trafficAdjustment = 1.20;
                break;
            default: // Light
                trafficAdjustment = 1.0;
                break;
        }

        // Nighttime adjustment
        double nightAdjustment = isNight ? 0.95 : 1.0;

        // Idle emissions
        int idleEmissions = idleTime * 10;

        // Calculate total emissions
        double totalEmissions;
        if (fuelAdjustment == 0.0) {
            totalEmissions = 0.0; // EVs produce no emissions
        } else {
            totalEmissions = distance * baseEmission * fuelAdjustment * trafficAdjustment * nightAdjustment;
        }

        // Share emissions among riders and add idle emissions
        return totalEmissions * (1 - 1.0 / riders) + idleEmissions;
    }
}