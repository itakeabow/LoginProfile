package com.example.loginprofile;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class DialogBox extends Dialog implements
        android.view.View.OnClickListener{



    public Activity activity;
    public Dialog d;
    public Button send, dontSend;
    EditText message;
    String dialogMessage;

    public DialogBox(Activity a){
    super(a);
    this.activity=a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_box);
    message = findViewById(R.id.editText1);
    send.setOnClickListener(this);
    dontSend.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
    switch (v.getId()){
        case R.id.button1:

        dialogMessage = message.getText().toString();
    }

    }

}
