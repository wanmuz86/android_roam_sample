package com.itrainasia.androidroamsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    Button buttonSave;
    EditText nameEditText, descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        buttonSave = findViewById(R.id.buttonSave);
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descEditText);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("name",nameEditText.getText().toString());
                intent.putExtra("desc",descEditText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
