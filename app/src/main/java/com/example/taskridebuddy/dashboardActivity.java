package com.example.taskridebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class dashboardActivity extends AppCompatActivity {

    private TextView cumulativeSavingsText;
    private TextView milestoneText;
    private TextView motivationalText;
    private ProgressBar progressBar;
    private TextView progressText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}

        // Receive the passed CO2 savings value from ResultActivity
        Intent intent = getIntent();
        double cumulativeSavings = intent.getDoubleExtra("CUMULATIVE_SAVINGS", 0.0);

        // Display the badge
        TextView badgeText = findViewById(R.id.badgeText);
        updateBadge(cumulativeSavings, badgeText);

        // Display milestone
        TextView milestoneText = findViewById(R.id.milestoneText);
        updateMilestone(cumulativeSavings, milestoneText);
    }

    private void updateBadge(double cumulativeSavings, TextView badgeText) {
        if (cumulativeSavings > 500) {
            badgeText.setText("Eco-Warrior! 🌟 Total Savings: " + String.format("%.2f", cumulativeSavings / 1000) + " kg CO₂");
        } else if (cumulativeSavings > 100) {
            badgeText.setText("Great Job! 🚀 Total Savings: " + String.format("%.2f", cumulativeSavings / 1000) + " kg CO₂");
        } else {
            badgeText.setText("Keep Going! 🌱 Total Savings: " + String.format("%.2f", cumulativeSavings) + " grams CO₂");
        }
    }

    private void updateMilestone(double cumulativeSavings, TextView milestoneText) {
        if (cumulativeSavings >= 1000) {
            milestoneText.setText("🎉 Milestone Achieved: 1 Ton of CO₂ Saved!");
        } else if (cumulativeSavings >= 500) {
            milestoneText.setText("🎉 Milestone Achieved: 500 kg of CO₂ Saved!");
        } else if (cumulativeSavings >= 100) {
            milestoneText.setText("🎉 Milestone Achieved: 100 kg of CO₂ Saved!");
        } else {
            milestoneText.setText("🚀 Save more to unlock your first milestone!");
        }
    }
}