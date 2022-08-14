package com.example.mvvmchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText meter;
    private EditText kilometer;
    private EditText centimeter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void observeLength() {
        viewModel.getMeter().observe( this,  meter -> {
            String parsedKilometer = getString(
                    R.string.float_to_string,
                    meter.toKilometer()
            );
            String parsedCentimeter = getString(
                    R.string.float_to_string,
                    meter.toCentimeter()
            );
            kilometer.setText(parsedKilometer);
            centimeter.setText(parsedCentimeter);
        });
    }

    private void initView() {
        meter = findViewById(R.id.meter);
        kilometer = findViewById(R.id.kilometer);
        centimeter = findViewById(R.id.centimeter);

        meter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String meter = editable.toString();
                viewModel.setMeter(meter);
            }
        });
    }
}