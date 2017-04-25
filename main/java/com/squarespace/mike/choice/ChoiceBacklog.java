package com.squarespace.mike.choice;

import android.content.Context;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ChoiceBacklog {

    private ArrayMap<List<String>, String> vals = new ArrayMap<>();
    private Context ctx;

    public ChoiceBacklog(Context ctx){
        this.ctx = ctx;

        addList("Calm down, \nEverything will be all right!", "sad", "mad", "angry", "upset", "bitter", "enraged", "harebrained",
                "insane", "crazy", "argument");
        addList("Let it heal off. \nThen, try your activity again.", "fire", "burn", "cut", "hazard", "frostbite", "freeze");
        addList("Think about if you're happy with him/her", "love", "kiss", "bang", "sex", "erotic");
        addList("Don't kill yourself! \nThink about what you hold dear in life! Friends, Family, Video Games? ;-)", "death",
                "suicide", "life", "annihilation", "felo-de-se", "hara-kiri", "melange");
        addList("Drug use is only taken in moderation. \n Being too drunk might mean death or being badly injured!", "morphine",
                "cocaine", "heroine", "drug abuse", "alcoholism", "drug addiction", "alcohol abuse", "drug use");
        addList("Obesity is a big problem. \n Let yourself fix it. \nRestrict the sweets you eat!", "obesity", "rotundness",
                "plumpness", "fat", "overweight", "chubby", "plump", "rotund", "paunch", "big belly", "obese");
        addList("All sexes should be allowed to marry each other from their ideals. \n It's not weird.", "homophile love",
                "lesbian marriage", "gay marriage", "homoerotic", "queer love", "homophobic");
        addList("Vanilla. \n Always vanilla","vanilla or","chocolate or","strawberry or", "vanilla or chocolate?");
        addList("Left always go left","Left or right", "left or", "right or");
        addList("Eat fruits or vegetables. \nTry a healthy substitute.", "hungry", "I want food", "diet", "quick snack");
        addList("Try to go to sleep at a reasonable hour. \n Preferably, 11 pm or so", "What time should I go to sleep?",
                "to sleep", "too late", "stay up all night", "all nighter", "not want sleep");
        addList("Lower your expectations.", "big test", "high expectations", "expectations", "good score",
                "great score", "great expectations", "scared on test", "overconfidence", "overconfident");
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
                if (problem.contains(key)) {
                    return vals.get(entry.getKey());
                }
            }
        }
        return ctx.getString(R.string.unsure);
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
