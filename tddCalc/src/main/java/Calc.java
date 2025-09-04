import java.util.*;

public class Calc {

    private static int idx;
    private static String ParseRest;

    public static int run(String args) {
        ParseRest = args;
        idx = 0;
        int val = parseExpr();
        skipSpaces();
        return val;
    }

    private static int parseExpr(){
        int val = parseNumber();
        while(true){
            skipSpaces();
            if (match('+')) {
                val += parseNumber();
            }
            else if (match('-')) {
                val -= parseNumber();
            }
            else break;
        }
        return val;
    }

    private static int parseNumber(){
        skipSpaces();
        if(idx >= ParseRest.length() || !Character.isDigit(ParseRest.charAt(idx))){
            throw new IllegalArgumentException("Invalid number");
        }
        int num = 0;
        while(idx < ParseRest.length() && Character.isDigit(ParseRest.charAt(idx))){
            num = num*10 + (ParseRest.charAt(idx) - '0');
            idx++;
        }
        return num;
    }

    private static boolean match(char c){
        skipSpaces();
        if(idx < ParseRest.length() && ParseRest.charAt(idx) == c){
            idx++;
            return true;
        }
        return false;
    }
    private static void skipSpaces(){
        while(idx < ParseRest.length() && Character.isWhitespace(ParseRest.charAt(idx))) idx++;
    }

}
