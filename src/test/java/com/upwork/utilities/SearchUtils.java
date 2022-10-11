package com.upwork.utilities;

/*
In this class only general utility methods that are NOT related to some specific page.
 */


public class SearchUtils {
    /**
     *
     * @param text
     * @return boolean and it returns false it means there is no search result.
     */
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
