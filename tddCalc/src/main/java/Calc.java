import java.util.*;

public class Calc {
    public static int run(String args){

        String[] tokens = args.split("\\s+", 2); // 첫공백으로 왼쪽 오른쪽으로 나눔

        if (tokens.length == 0 || tokens[0].isEmpty()) {
            return 0;
        }

        String first = tokens[0];
        String rest = (tokens.length>1) ? tokens[1] : "";   // tokens[1]에 아무것도 없을경우 빈문자열 넣어줌

        try{
            int left = Integer.parseInt(first); // 좌항 int로 파싱 시도
            if (rest.isEmpty()) {
                return left;
            }
            String[] operator = rest.split("\\s+", 2);
            if(operator.length == 0){
                return left;
            }
            String op = operator[0];
            String right = (operator.length>1) ? operator[1] : "";

            switch (op) {
                case "+": return left + run(right);
                case "-": return left - run(right);
                case "*": return left * run(right);
                case "/": return left / run(right);
                default:
                    throw new NumberFormatException("지원하지 않는 연산자: " + first);
            }
        } catch(NumberFormatException e){
            if(first.equals("+"))   // tokens[0]이 숫자가 아니라면 연산자임 나머지 우항 리턴
                return run(rest);
            else if(first.equals("-")) {
                return -run(rest);
            }

            throw new NumberFormatException("지원하지 않는 연산자: " + first);
        }
    }

}
