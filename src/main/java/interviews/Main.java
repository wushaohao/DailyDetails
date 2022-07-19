package interviews;

import java.util.*;

public class Main {

    public static ArrayList<String> infixToPostfix(ArrayList<String> inExp) {

        int length = inExp.size();
        ArrayList<String> postExp = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < length; i++) {
            String temp = inExp.get(i);

            switch (temp) {
                case "(":
                    stack.push(temp);
                    break;
                case "+":
                case "-":
                    while (stack.size() != 0) {
                        String tempPop = stack.pop();
                        if (tempPop.equals("(")) {
                            stack.push(tempPop);
                            break;
                        }
                        postExp.add(tempPop);
                    }
                    stack.push(temp);
                    break;
                case "*":
                case "/":
                    while (stack.size() != 0) {
                        String tempPop = stack.pop();
                        if (tempPop.equals("(") || tempPop.equals("+") || tempPop.equals("-")) {
                            stack.push(tempPop);
                            break;
                        } else {
                            postExp.add(tempPop);
                        }
                    }
                    stack.push(temp);
                    break;
                case ")"://)不入栈
                    while (stack.size() != 0) {
                        String tempPop = stack.pop();
                        if (tempPop.equals("(")) {
                            //                        stack.push(tempPop);
                            break;
                        } else {
                            postExp.add(tempPop);
                        }
                    }
                    break;
                default:
                    postExp.add(temp);
                    break;
            }
        }

        while (stack.size() != 0) {
            postExp.add(stack.pop());
        }

        return postExp;
    }

    public static int postfixToResult(ArrayList<String> postfixExp) {

        Stack<Integer> stack = new Stack<Integer>();
        int expLength = postfixExp.size();
        ArrayList<String> expTemp = new ArrayList<String>();

        //去掉空格的东西
        for (int i = 0; i < expLength; i++) {
            if (postfixExp.get(i).trim().length() != 0) {
                expTemp.add(postfixExp.get(i));
            }
        }

        int length = expTemp.size();

        for (int i = 0; i < length; i++) {
            if (!expTemp.get(i).matches("[\\(\\)\\+\\-\\*\\/]")) {
                stack.push(Integer.parseInt(expTemp.get(i)));
            } else {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(getResult(first, second, expTemp.get(i)));
            }
        }
        return stack.pop();
    }

    public static int getResult(int first, int second, String symbol) {
        if (symbol.equals("+")) {
            return first + second;
        } else if (symbol.equals("-")) {
            return second - first;
        } else if (symbol.equals("*")) {
            return first * second;
        } else if (symbol.equals("/")) {
            return second / first;
        }
        return 0;
    }

    public static ArrayList<String> getExpression(String exp) {

        exp = exp.trim();
        exp = exp.replaceAll("[\\[\\{]", "(");
        exp = exp.replaceAll("[\\]\\}]", ")");
        int expLength = exp.length();

        StringBuilder inSb = new StringBuilder();
        for (int i = 0; i < expLength; i++) {
            String tempStr = String.valueOf(exp.charAt(i));
            if (tempStr.matches("[\\(\\)\\+\\-\\*\\/]")) {
                inSb.append(",");
                inSb.append(tempStr);
                inSb.append(",");
            } else {
                inSb.append(tempStr);
            }
        }

        String[] inArr = inSb.toString().split("\\,+");
        ArrayList<String> inStr = new ArrayList<String>(Arrays.asList(inArr));

        ArrayList<Integer> numList = new ArrayList<Integer>();

        if (inStr.get(0).equals("-")) {
            inStr.set(1, "-" + inStr.get(1));
            numList.add(0);
        } else {
            for (int i = 1; i < inStr.size(); i++) {
                if (inStr.get(i).equals("-")) {
                    if (inStr.get(i - 1).matches("[\\+\\/\\-\\*\\(]")) {//前一个是数字）后面可以有
                        inStr.set(i + 1, "-" + inStr.get(i + 1));
                        numList.add(i);
                    }
                }
            }
        }

        ArrayList<String> endList = new ArrayList<String>();

        for (int i = 0; i < inStr.size(); i++) {
            if (!numList.contains(i)) {
                endList.add(inStr.get(i));
            }
        }

        return endList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String arthLine = scanner.nextLine();
            if (arthLine.trim().length() != 0) {
                System.out.println(postfixToResult(infixToPostfix(getExpression(arthLine))));
            }
        }
    }
}