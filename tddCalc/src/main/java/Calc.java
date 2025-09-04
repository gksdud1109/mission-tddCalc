import java.util.*;

public class Calc {

    private static int idx;
    private static String ParseRest;

    public static int run(String args) {
        ParseRest = args;
        idx = 0;
        return parseExpr();
    }

    private static int parseExpr(){
        int Eval = parseTerm();
        while(true){
            skipSpaces();
            if (match('+')) {
                Eval += parseTerm();
            }
            else if (match('-')) {
                Eval -= parseTerm();
            }
            else break;
        }
        return Eval;
    }

    private static int parseTerm(){
        int Tval = parseNumber();
        while(true){
            skipSpaces();
            if(match('*')){
                Tval *= parseNumber();
            } else if(match('/')){
                Tval /= parseNumber();
            }
            else break;
        }
        return Tval;
    }

    private static int parseNumber(){
        skipSpaces();
        if(idx >= ParseRest.length()){
            throw new IllegalArgumentException("Invalid number");
        }
        else if(!Character.isDigit(ParseRest.charAt(idx))){
            if(match('-')) return -parseNumber();
            else
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
