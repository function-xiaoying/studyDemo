package com.test;

public class Test02 {

    public static String nullToString(Object s){
        if(s == null){
            return "";
        }else{
            return s.toString().trim();
        }

    }

}
