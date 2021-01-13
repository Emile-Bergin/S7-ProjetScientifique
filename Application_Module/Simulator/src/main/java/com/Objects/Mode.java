package com.Objects;

public class Mode {
    private static boolean DEBUG = true;
    public static boolean USEREELAPI = true;

    public static void println(String str){
        if (DEBUG){
            System.out.println(str);
        }
    }

    public static void printlnBold(String str){
        if (DEBUG){
            System.out.println("\u001B[1m"+str);
        }
    }
}
