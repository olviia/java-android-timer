package com.stopwatcholv.timer;

import java.util.ArrayList;

public class SavedTimeAnnotation {
    private static ArrayList<String> annotation = new ArrayList<>();

    public static void removeAnnotation(int index){
        annotation.remove(index);
    }
    public static void setAnnotation(int index, String text){
        annotation.set(index, text);
    }
    public static void addAnnotation(String text){
        annotation.add(0,text);
    }
    public static  String getAnnotation(int index){
        return annotation.get(index);
    }
    public static void clearAnnotation(){
        annotation.clear();
    }
}
