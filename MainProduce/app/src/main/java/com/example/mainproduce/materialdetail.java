package com.example.mainproduce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class materialdetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialdetail);

        TextView material = (TextView)findViewById(R.id.textView1);
        TextView effect = (TextView)findViewById(R.id.textView2);
        ImageView iv = (ImageView)findViewById(R.id.imageView1);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        material.setText(intent.getStringExtra("material"));
        effect.setText(intent.getStringExtra("effect"));
        iv.setImageResource(intent.getIntExtra("img", 0));
    }
}