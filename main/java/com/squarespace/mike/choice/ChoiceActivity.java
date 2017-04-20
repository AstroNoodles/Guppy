package com.squarespace.mike.choice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChoiceActivity extends AppCompatActivity {
    public TextView result;
    public EditText situation, respond;
    public Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_choice);
         situation = (EditText) findViewById(R.id.situation);
         result = (TextView) findViewById(R.id.result);
         respond = (EditText) findViewById(R.id.respond);
         save = (Button) findViewById(R.id.set);
    }

    public void start(View v){
        ChoiceBacklog log = new ChoiceBacklog();
        String reply = log.reply(situation.getText().toString());
        if(reply.contains("not sure")){
            respond.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
            result.setText(reply);
        } else result.setText(reply);

    }

    public void clear(View v){
        situation.setText("");
        result.setText("");
        respond.setText("");
        save.setVisibility(View.GONE);
        respond.setVisibility(View.INVISIBLE);
        situation.requestFocus();
    }

    public void setFile(View v){
        if(canWriteExternal()) {
            try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "userDs.txt");
            file.getParentFile().mkdirs();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                bw.write("");
                bw.write(situation.getText().toString() + ":" + respond.getText().toString() + "\n");
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
        Toast.makeText(this, "This app cannot write to external data. Please turn this permission on.",
                Toast.LENGTH_SHORT).show();
    }

    public boolean canWriteExternal(){
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }






}
