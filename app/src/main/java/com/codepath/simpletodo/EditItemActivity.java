package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private int position;
    EditText etSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Item");
        setSupportActionBar(toolbar);

        position = getIntent().getExtras().getInt("position");
        String item = getIntent().getExtras().getString("item");

        etSave = (EditText)findViewById(R.id.etSave);
        etSave.append(item);
    }

    public void onSaveItem(View v) {
        Intent data = new Intent();
        data.putExtra("position", position);
        data.putExtra("item", etSave.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
