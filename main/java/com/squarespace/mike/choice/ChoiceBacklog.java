package com.squarespace.mike.choice;

import android.os.Environment;
import android.util.ArrayMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ChoiceBacklog {

    private ArrayMap<List<String>, String> vals = new ArrayMap<>();

    public ChoiceBacklog(){
        addList("Calm down, \neverything will be all right!", "sad", "mad", "angry", "upset", "bitter", "enraged", "harebrained",
                "insane", "crazy", "argument");
        addList("Let it heal off. \nThen, try your activity again.", "fire", "burn", "cut", "hazard", "frostbite", "freeze");
        addList("Think about if you're happy with him/her", "love", "kiss", "bang", "sex", "erotic");
        addList("Don't kill yourself! \nThink about what you hold dear in life! Friends, Family, Video Games? ;-)", "death",
                "suicide", "life", "annihilation", "felo-de-se", "hara-kiri", "melange");
        addList("Drug use is only taken in moderation. \n Being too drunk might mean death or being badly injured!", "morphine",
                "cocaine", "heroine", "drug abuse", "alcoholism", "drug addiction", "alcohol abuse", "drug use");
        addList("Obesity is a big problem. \n Let yourself fix it. Restrict the sweets you eat!", "obesity", "rotundness",
                "plumpness", "fat", "overweight", "chubby", "plump", "rotund", "paunch", "big belly", "obese");
        addList("A baby is the creation of a human being. \n Do you really want to kill it?", "abortion", "not want fetus",
                "miscarraige", "feticide", "misbirth", "aborticide");
        addList("What you think is up to you. \n Just try to keep up with the news and keep secret your feelings",
                "not allowed", "illegal immigrants", "people without visas", "illegal crossing", "not legal citizens");
        addList("All sexes should be allowed to marry each other from their ideals. \n It's not weird.", "homophile love",
                "lesbian marriage", "gay marriage", "homoerotic", "queer love", "homophobic");
        addList("Protect your gun at all costs. \nYour trigger is a weapon to all.", "gun control", "gun kill",
                "gun killings", "gun out of control");
        addExternalLists();

    }

    public void addExternalLists(){
        if(canReadExternal()){
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "userDs.txt");
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder build = new StringBuilder();
                while((line = br.readLine()) != null){
                    build.append(line);
                }
                String[] items = build.toString().split(":");
                for(int i = 0; i < items.length; i++){
                    String key = "";
                    String value = "";
                    if(i % 2 == 0) key = items[i]; else value = items[i];
                    vals.clear();
                    addList(value, key);

                }
            } catch(IOException e){
                e.printStackTrace();
            }
        } else System.out.println("Not able to read external data. :-p");
    }

    public String reply(String problem) {
        for (Map.Entry<List<String>, String> entry : vals.entrySet()) {
            for (String key : entry.getKey()) {
                if (problem.contains(key)) {
                    return vals.get(entry.getKey());
                }
            }
        }
        return "I'm not sure on that.\n Could you remind me on what to say?";
    }

    public boolean canReadExternal(){
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public void addList(String value, String... keys){
        List<String> list = Arrays.asList(keys);
        vals.put(list, value);
        System.out.println(vals);

    }



}
