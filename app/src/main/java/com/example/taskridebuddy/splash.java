package com.example.taskridebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            ImageView logo = findViewById(R.id.RBlogo);
            TextView travelText = findViewById(R.id.TravelText);

        ScaleAnimation scaleUpAnimation = new ScaleAnimation(
                0.5f, 1.5f, // Scale from 70% to 100% in X
                0.5f, 1.5f, // Scale from 70% to 100% in Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot at center X
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot at center Y
            );
        scaleUpAnimation.setDuration(2500); // 1 second
        scaleUpAnimation.setFillAfter(true); // Maintain final state

        // Step 2: Alpha Animation (Fade-in effect)
        AlphaAnimation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f); // From invisible to visible
        fadeInAnimation.setDuration(1500); // 1 second
        fadeInAnimation.setFillAfter(true);

        //text animation step 1
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f); // From invisible to fully visible
        fadeIn.setDuration(1500); // 1.5 seconds
        fadeIn.setFillAfter(true);

        //text aniamtion step 2
        ScaleAnimation zoomIn = new ScaleAnimation(
                0.8f, 1.2f, // Scale from 80% to 120% in X
                0.8f, 1.2f, // Scale from 80% to 120% in Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot at center X
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot at center Y
        );

        zoomIn.setDuration(1500); // 1.5 seconds
        zoomIn.setFillAfter(true);

        // Step 3: Translate Animation (Slide-in effect)
        TranslateAnimation slideIn = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, // Start at current position
                Animation.RELATIVE_TO_SELF, 0f, // End at same horizontal position
                Animation.RELATIVE_TO_SELF, 1f, // Start from below the screen
                Animation.RELATIVE_TO_SELF, 0f  // End at original vertical position
        );

        slideIn.setDuration(1500); // 1.5 seconds
        slideIn.setFillAfter(true);

        // Combine all animations into an AnimationSet
        AnimationSet textAnimationSet = new AnimationSet(true);
        textAnimationSet.addAnimation(fadeIn);
        textAnimationSet.addAnimation(zoomIn);
        textAnimationSet.addAnimation(slideIn);

        // Start the animation on the TextView
        travelText.startAnimation(textAnimationSet);

        // Step 3: Subtle Pulse Effect

        ScaleAnimation pulseAnimation = new ScaleAnimation(
                0.5f, 1.0f, // Slightly increase size
                0.5f, 1.0f, // Slightly increase size
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot at center X
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot at center Y
        );

        pulseAnimation.setDuration(300); // Quick pulse
        pulseAnimation.setRepeatMode(Animation.REVERSE); // Reverse the effect
        pulseAnimation.setRepeatCount(1); // Pulse once

        // Combine animations into an AnimationSet
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleUpAnimation);
        animationSet.addAnimation(fadeInAnimation);

        // Start animations sequentially
        logo.startAnimation(animationSet);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash.this, MainActivity.class);
            startActivity(intent);
            finish(); // End splash activity
        }, 3000); // Total duration (3 seconds)

        // Transition to the main activity after all animations
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish splash screen activity
        }, 4000); // Total duration (1.5 seconds)

            }
        }

