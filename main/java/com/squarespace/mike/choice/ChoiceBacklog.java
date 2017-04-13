package com.squarespace.mike.choice;

import android.util.ArrayMap;

import java.util.Random;

public class ChoiceBacklog {
    private ArrayMap<String, String> vals = new ArrayMap<>();

    public ChoiceBacklog(){
        vals.put("!", "Calm down... Everything will be all right!");
        vals.put("sad", "Calm down... Everything will be all right!");
        vals.put("angry", "Calm down... Everything will be all right!");
        vals.put("argument", "Calm down... Everything will be all right!");
        vals.put("love", "Just do it!");
        vals.put("banana", "Bananas are a good source of potassium!");
        vals.put("life", "Life is a mystery in of itself");
        vals.put("Issac Newton", "Remember the three gravitational laws!");
        vals.put("George Washington", "The first president of the U.S.A");
        vals.put("bacon", "Delicious!");
        vals.put("Joseph Stalin", "The Soviet Premier that was prominent in WWII");
        vals.put("friend", "The friends are the people that truly care for you.");
        vals.put("foe", "The foes are the ones that exploit you. Like Gandhi, peacefully resist them.");
        vals.put("?", "Ask the question again in your own terms");
        vals.put("winter", "Don't get sick!");
        vals.put("plane", "Remember your limitations, don't be sick.");
        vals.put("password", "I'd be ashamed if your passcode was your birthday, 1234 or your name");
        vals.put("email", "Is it secure?");
        vals.put("challenge", "One word: perseverance");
        vals.put("died", "I'm so sorry. Can you recover from the experience?");
        vals.put("burn", "Treat it with some burn ointment or put some ice on it.");
    }

    private String genericReply(){
        String[] replies = {"Pretty interesting, ain't it?", "That's pretty good",
                "Nice.", "Think about it more.", "You sure?", "Great."};
        return replies[new Random().nextInt(replies.length - 1)];
    }

    public String reply(String problem){
        for(String key : vals.keySet()){
            if(problem.contains(key)){
                return vals.get(key);
            }
        }
        return genericReply();
    }
}
