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
        addList(res.getString(R.string.injuryresponse), res.getStringArray(R.array.medical));
        addList(res.getString(R.string.vorcresponse), res.getStringArray(R.array.vorc));
        addList(res.getString(R.string.fruorvegeresponse), res.getStringArray(R.array.fruorvege));
        addList(res.getString(R.string.sleepresponse), res.getStringArray(R.array.sleep));
        addList(res.getString(R.string.expectresponse), res.getStringArray(R.array.expectations));
        addList(res.getString(R.string.siriresponse), res.getStringArray(R.array.siri));
        addList(res.getString(R.string.hiresponse), res.getStringArray(R.array.hi));
        addList(res.getString(R.string.jresponse), res.getStringArray(R.array.jacket));
        addList(res.getString(R.string.bsresponse), res.getStringArray(R.array.bootsorshoes));
        addList(res.getString(R.string.mormresponse), res.getStringArray(R.array.museumormovie));
        addList(res.getString(R.string.aororesponse), res.getStringArray(R.array.applesororanges));
        addList(res.getString(R.string.torbresponse), res.getStringArray(R.array.tacoorburrito));
        addList(res.getString(R.string.ytvresponse), res.getStringArray(R.array.youtubeortv));
        addList(res.getString(R.string.presentresponse), res.getString(R.string.startpresent));
        addExternalLists();

    }

    public void addExternalLists(){
        System.out.println(canReadExternal());
        if(canReadExternal()){
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "userDs.txt");
            if(!file.exists()) return;
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while((line = br.readLine()) != null){
                    String[] kv = line.split(":");
                    addList(kv[1], kv[0]);
                }
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
