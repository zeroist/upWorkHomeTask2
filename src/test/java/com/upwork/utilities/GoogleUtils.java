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

    public static List<String> createGoogleSearchItemList(){
        List<String> result=new ArrayList<>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/test/resources/googleSearcItems.txt"));
            String line = bufReader.readLine();
            while (line != null) { //SON SATIR BOŞ OLUCA DÖNGÜYÜ SONLANDIRIYOR.
                result.add(line); //LİSTEYE EKLEME BURADAAAAAAAAAAAAA
                line = bufReader.readLine(); //MUHTEMELEN BİR SONRAKİ SATIRA GEÇİYOR
            }
            bufReader.close(); //DÖNGÜ BİTTİKTEN SONRA READER I SONLANDIRIYOR
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

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
