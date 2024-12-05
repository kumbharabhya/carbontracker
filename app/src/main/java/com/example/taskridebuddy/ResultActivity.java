package com.example.taskridebuddy;

import android.content.Intent;
import android.os.Bundle;
//import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}


        // Get the passed emissions value
        Intent intent = getIntent();
        double emissions = intent.getDoubleExtra("calculatedEmissions", 0.0);

        // Display the result
        TextView resultText = findViewById(R.id.resultText);
        resultText.setText("CO₂ Savings: " + String.format("%.2f", emissions) + " grams");

        // Find the button and set a click listener
        Button goToDashboardButton = findViewById(R.id.dashBoardButtom);
        goToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DashboardActivity

                Intent intent = new Intent(ResultActivity.this, dashboardActivity.class);
                // Pass the cumulative savings to DashboardActivity
                intent.putExtra("CUMULATIVE_SAVINGS", emissions);
                startActivity(intent);

            }
        });
    }
}




