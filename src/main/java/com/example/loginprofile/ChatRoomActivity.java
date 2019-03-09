package com.example.loginprofile;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatRoomActivity extends Activity {

    private ListView listView;
    private Button send,receive;
    private EditText editText;
    private boolean isSend = false;
    private MessageAdapter adapter;
    Database database = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        listView = findViewById(R.id.listView1);
        send = findViewById(R.id.button1);
        receive = findViewById(R.id.button2);
        editText = findViewById(R.id.editText1);
        adapter = new MessageAdapter(getApplication(),R.layout.m);
        listView.setAdapter(adapter);

        final ArrayList<Message> messages= new ArrayList<>();

        ArrayList<String> messageList = new ArrayList<>();

        Cursor data = database.getListContent();
        database.printCursor();

        if(data.getCount() == 0){
            Toast.makeText(this,"There are no messages",Toast.LENGTH_LONG);
        }else{
        while(data.moveToNext()){

            messageList.add(data.getString(1));

            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageList);
            //ListAdapter listx = listx.setAda

            listView.setAdapter(listAdapter);
        }}

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                listView.setAdapter(adapter);
                messageSend();
            }
        });
        receive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                listView.setAdapter(adapter);
                messageReceive();
            }
        });

    }

    private boolean messageSend(){
        adapter.add(new Message(editText.getText().toString(), isSend));
        String entry =editText.getText().toString();
        AddData("Sent:" + " " + entry);
        editText.setText(entry);
        editText.setText("");
        return true;
    }

    private boolean messageReceive(){
        adapter.add(new Message(editText.getText().toString(), !isSend));
        String entry1 =editText.getText().toString();
        AddData("Received:" + " " + entry1);
        editText.setText("");
        return true;
    }


    public void AddData(String newEntry){
        boolean insertData =  database.addData(newEntry);

        if (insertData){
            toastMessage("Data inserted");
        } else{
            toastMessage("Data not inserted");
        }

    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
