package com.example.awsaf.learnenglish.view.TaskViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.awsaf.learnenglish.R;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

public class MCQ extends AppCompatActivity {
    private int currentStep = 0;
    private StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);
        stepView = findViewById(R.id.step_view);
        setupStepView();
        setupCustomisation();


        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                // 0 is the first step
                Toast.makeText(MCQ.this, "Stepper Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupStepView() {
        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(MCQ.this, "Step " + step, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    stepView.go(currentStep, true);
                } else {
                    stepView.done(true);
                }
            }
        });
        findViewById(R.id.fragment_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);
            }
        });
        List<String> steps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            steps.add("Step " + (i + 1));
        }
        stepView.setSteps(steps);
    }

    private void setupCustomisation() {
        setupSelectCircleColorCustomisation();
        setupSelectTextColorCustomisation();
    }

    private void setupSelectCircleColorCustomisation() {
//        final EditText selectedCircleColorEditText = findViewById(R.id.selected_circle_color_hex);
//        final ImageView selectedCircleColorSampleImageView = findViewById(R.id.selected_circle_color_sample);

        /*selectedCircleColorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // empty
            }

            @Override
            public void afterTextChanged(Editable s) {
                String candidateColorHex = s.toString();
                if (!candidateColorHex.contains("#")) {
                    candidateColorHex = "#" + candidateColorHex;
                }
                try {
                    int color = Color.parseColor(candidateColorHex);
                    selectedCircleColorSampleImageView.setImageDrawable(new ColorDrawable(color));
                    stepView.getState().selectedCircleColor(color).commit();
                } catch (IllegalArgumentException ignore) {
                }
            }
        });*/

//        int color = ContextCompat.getColor(this, R.color.stepview_circle_selected);
//        String hex = Integer.toHexString(color).toUpperCase().substring(2);
//        selectedCircleColorEditText.setText(hex);
//
//        selectedCircleColorSampleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showColorPickerDialog(new ColorPickListener() {
//                    @Override
//                    public void onColorPicked(String hex) {
//                        selectedCircleColorEditText.setText(hex);
//                    }
//                });
//            }
//        });
    }

    private void setupSelectTextColorCustomisation() {
//        final EditText selectedTextColorEditText = findViewById(R.id.selected_text_color_hex);
//        final ImageView selectedTextColorSampleImageView = findViewById(R.id.selected_text_color_sample);
//
//        selectedTextColorEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // empty
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // empty
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String candidateColorHex = s.toString();
//                if (!candidateColorHex.contains("#")) {
//                    candidateColorHex = "#" + candidateColorHex;
//                }
//                try {
//                    int color = Color.parseColor(candidateColorHex);
//                    selectedTextColorSampleImageView.setImageDrawable(new ColorDrawable(color));
//                    stepView.getState().selectedTextColor(color).commit();
//                } catch (IllegalArgumentException ignore) {
//                }
//            }
//        });

//        int color = ContextCompat.getColor(this, R.color.stepview_circle_selected);
//        String hex = Integer.toHexString(color).toUpperCase().substring(2);
//        selectedTextColorEditText.setText(hex);
//
//        selectedTextColorSampleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showColorPickerDialog(new ColorPickListener() {
//                    @Override
//                    public void onColorPicked(String hex) {
//                        selectedTextColorEditText.setText(hex);
//                    }
//                });
//            }
//        });
    }

}
