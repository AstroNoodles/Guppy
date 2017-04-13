package com.squarespace.mike.choice;


import android.content.Context;
import android.os.AsyncTask;
import android.net.ConnectivityManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotType;

import java.util.Locale;

public class ChoiceRecycler extends RecyclerView.Adapter<ChoiceRecycler.ViewHolder> {

    private Context ctx;

    public ChoiceRecycler(Context ctx){
        this.ctx = ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                System.out.println(v.getId() == holder.go.getId());
                if(isOnline()) {
                    ChatterTask task = new ChatterTask(holder);
                    task.execute(holder.problem.getText().toString());
                } else {
                   ChoiceBacklog backlog = new ChoiceBacklog();
                   holder.response.setText(backlog.reply(holder.problem.getText().toString()));
                }
            }
        });
     }

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
        @Override
        public int getItemCount() {
            return 10;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            EditText problem;
            Button go;
            TextView response;

            ViewHolder(View v) {
                super(v);
                problem = (EditText) v.findViewById(R.id.situation);
                go = (Button) v.findViewById(R.id.ok);
                response = (TextView) v.findViewById(R.id.result);
            }
        }

        private class ChatterTask extends AsyncTask<String, Void, String> {
            private ViewHolder holder;

            ChatterTask(ViewHolder holder) {
                this.holder = holder;
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    ChatterBotFactory fact = new ChatterBotFactory();
                    return fact.create(ChatterBotType.CLEVERBOT).createSession(Locale.ENGLISH).think(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "There was something wrong with your internet connection";
            }

            @Override
            protected void onPostExecute(String s) {
                holder.response.setText(s);
            }
        }
    }





