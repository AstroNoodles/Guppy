package com.squarespace.mike.choice;

import android.content.res.Resources;
import android.os.Environment;
import android.util.ArrayMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ChoiceBacklog {

    private ArrayMap<List<String>, String> vals = new ArrayMap<>();
    private Resources res;

    public ChoiceBacklog(Resources res){
        this.res = res;
        addList(res.getString(R.string.feelresponse), res.getStringArray(R.array.feelings));
        addList(res.getString(R.string.injuryresponse), res.getStringArray(R.array.injury));
        addList(res.getString(R.string.loveresponse), res.getStringArray(R.array.love));
        addList(res.getString(R.string.suicideresponse), res.getStringArray(R.array.suicide));
        addList(res.getString(R.string.drugresponse), res.getStringArray(R.array.drugs));
        addList(res.getString(R.string.obeseresponse), res.getStringArray(R.array.obesity));
        addList(res.getString(R.string.vorcresponse), res.getStringArray(R.array.vorc));
        addList(res.getString(R.string.lorrresponse), res.getStringArray(R.array.lorr));
        addList(res.getString(R.string.fruorvegeresponse), res.getStringArray(R.array.fruorvege));
        addList(res.getString(R.string.sleepresponse), res.getStringArray(R.array.sleep));
        addList(res.getString(R.string.expectresponse), res.getStringArray(R.array.expectations));
        addList(res.getString(R.string.presentresponse), res.getString(R.string.startpresent));
        addExternalLists();

    }

    public void addExternalLists(){

        if(canReadExternal()){
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "userDs.txt");
            if(!file.exists()) return;
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder build = new StringBuilder();
                while((line = br.readLine()) != null){
                    build.append(line);
                }
                String[] items = build.toString().split(":");
                String key = "", value = "";
                for(int i = 0; i < items.length; i++){
                    if(i % 2 == 0) key = items[i]; else value = items[i];
                }
                addList(value, key);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else System.out.println("can't read external data");

    }

    public String reply(String problem) {
        for (Map.Entry<List<String>, String> entry : vals.entrySet()) {
            for (String key : entry.getKey()) {
                if (problem.toLowerCase().contains(key.toLowerCase())) {
                    return vals.get(entry.getKey());
                }
            }
        }
        return res.getString(R.string.unsure);
    }

    public boolean canReadExternal(){
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }



    public void addList(String value, String... keys){
        List<String> list = Arrays.asList(keys);
        System.out.println(list);
        vals.put(list, value);

    }



}
