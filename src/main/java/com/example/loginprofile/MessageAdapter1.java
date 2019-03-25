package com.example.loginprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter1 extends ArrayAdapter<Message> {
    public static final int SEND = 0;
    public static final int RECIEVE = 1;

    private TextView messageText;
    private List<Message> messageList = new ArrayList<Message>();
    private Context context;


    public void add(Message object) {
        messageList.add(object);
        super.add(object);
    }

    public MessageAdapter1(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public int getCount() {
        return this.messageList.size();
    }

    public Message getMessage(int index) {
        return this.messageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getMessage(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (message.isSend) {
            row = inflater.inflate(R.layout.m, parent, false);
        }

        messageText = row.findViewById(R.id.messages);
        messageText.setText(message.message);
        return row;

    }


}
//Object getitem(int position)
//long getItemId(int position) not used right now.
