package com.upwork.utilities;

public class SearchUtils {
    /**
     * @param text
     * @return boolean and if it returns false it means there is no search result.
     */
    public static boolean doesSearchResultExist(String text) {
        boolean result = true;
        char[] chars = text.toCharArray();

        for (char eachChar : chars) {
            if (Character.isDigit(eachChar)) {
                if (eachChar == 48) {
                    result = false;
                }
                break;
            }

        }

        return result;
    }

}
