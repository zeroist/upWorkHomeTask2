package com.upwork.utilities;

/*
In this class only general utility methods that are NOT related to some specific page.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleUtils {

    public static boolean doesSearchResultExist(String text){
        boolean result=true;
        char[] chars = text.toCharArray();

        for (char eachChar : chars) {
            if(Character.isDigit(eachChar)){
                if (eachChar==48){
                    result=false;
                }
            }
        }

        return result;
    }

}
