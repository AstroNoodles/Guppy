package com.squarespace.mike.choice;

import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChoiceActivity extends AppCompatActivity {
    public TextView result, pl, cl;
    public EditText situation, pros, cons;
    public FloatingActionButton save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        situation = (EditText) findViewById(R.id.situation);
        result = (TextView) findViewById(R.id.result);
        pros = (EditText) findViewById(R.id.pros);
        cons = (EditText) findViewById(R.id.cons);
        save = (FloatingActionButton) findViewById(R.id.save);
        pl = (TextView) findViewById(R.id.proLabel);
        cl = (TextView) findViewById(R.id.conLabel);

    }

    public void start(View v){
        ChoiceBacklog log = new ChoiceBacklog(getResources());
        String reply = log.reply(situation.getText().toString());
        hideKeyboard();
        if(reply.contains(getString(R.string.une))){
            pl.setVisibility(View.VISIBLE);
            cl.setVisibility(View.VISIBLE);
            pros.setVisibility(View.VISIBLE);
            cons.setVisibility(View.VISIBLE);
            save.show();
            result.setText(reply);
        } else result.setText(reply);

    }

    public void clear(View v){
        situation.setText("");
        result.setText("");
        pros.setText("");
        cons.setText("");
        save.setVisibility(View.INVISIBLE);
        pros.setVisibility(View.INVISIBLE);
        cons.setVisibility(View.INVISIBLE);
        pl.setVisibility(View.INVISIBLE);
        cl.setVisibility(View.INVISIBLE);
        situation.requestFocus();
    }

    public void setFile(View v){
        hideKeyboard();
        if(canWriteExternal()) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "userDs.txt");
            file.getParentFile().mkdirs();
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));){
                bw.write(situation.getText().toString() + ":" + pros.getText().toString() + " " + cons.getText().toString() + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
        Toast.makeText(this, R.string.noexternal, Toast.LENGTH_SHORT).show();
    }

    public boolean canWriteExternal(){
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public void hideKeyboard(){
        View v = this.getCurrentFocus();
        if(v != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }




}
