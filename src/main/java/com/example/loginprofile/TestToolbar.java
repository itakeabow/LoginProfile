package com.example.loginprofile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

public class TestToolbar extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayouts;
    private Toolbar toolbar;
    private RelativeLayout root;
    View view;
    Toast toaster;
    MenuItem icon1,icon2,icon3;
    String toastText = "Initial message";
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aa:
                Toast.makeText(TestToolbar.this, "Selected home",Toast.LENGTH_SHORT).show();
            case R.id.menuItem1:
                Toast.makeText(TestToolbar.this, toastText, Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItem2:
              dialogBox();
              return true;
            case R.id.menuItem3:
                snackBar();
                return true;
            default:
                return true;
        }
    }

    public void dialogBox(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
        View view = LayoutInflater.from(TestToolbar.this).inflate(R.layout.dialog_box, null);
        builder.setView(view);
        final EditText newToastText = view.findViewById(R.id.editText1);;

        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(TestToolbar.this, "Toast message changed", Toast.LENGTH_SHORT).show();
                String entry = newToastText.getText().toString();
                toastText = entry;
                newToastText.setText("");
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void snackBar(){
        setContentView(R.layout.coordinator_layout);

        coordinatorLayouts = findViewById(R.id.coordinator);
        Snackbar snackbar = Snackbar.make(coordinatorLayouts,"", Snackbar.LENGTH_LONG).setAction("go back", new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(TestToolbar.this,"finish",Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        snackbar.show();

    }
}
