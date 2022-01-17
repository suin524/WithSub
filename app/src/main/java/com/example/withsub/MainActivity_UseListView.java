package com.example.withsub;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    ListView listView;
    EditText editText;
    ArrayAdapter<String> arrayAdapter;

    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;
    public static final String EXTRA_MESSAGE = "com.example.WithSub.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        // add button에 대한 이벤트 처리.
        Button addButton = (Button)findViewById(R.id.Aging) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayButton1Activity.class);
                startActivity(intent);
            }
        }) ;



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;

                // TODO : use strText
            }
        }) ;


        /*listView = (ListView)findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.editText);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        arrayAdapter.add("A_list");
        arrayAdapter.add("B_list");
        arrayAdapter.add("C_list");
        listView.setAdapter(arrayAdapter);
        listView.setTextFilterEnabled(true);
        editText.addTextChangedListener(this);*/
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DisplayButton1Activity.class);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        listView.setFilterText(editText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editText.getText().length() == 0) {
            listView.clearTextFilter();
        }
    }
}
