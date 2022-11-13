import java.util.LinkedList;
import java.util.Queue;

public class EnglishWords {
    public static String numberToWords(int num) {
        // Largest number: 2147483647
        Queue<String> queue = new LinkedList<>();
        String numStr = num + "";
        if (numStr.length() == 10) {
            simple(numStr.charAt(0), queue);
            queue.add("Billion");
            numStr = numStr.substring(1);
        }
        if (numStr.length() == 9) {
            three(numStr.charAt(0), numStr.charAt(1), numStr.charAt(2), queue);
            if (numStr.charAt(0) != '0' || numStr.charAt(1) != '0' || numStr.charAt(2) != '0')
                queue.add("Million");
            numStr = numStr.substring(3);
        }
        if (numStr.length() == 8) {
            two(numStr.charAt(0), numStr.charAt(1), queue);
            if (numStr.charAt(0) != '0' || numStr.charAt(1) != '0')
                queue.add("Million");
            numStr = numStr.substring(2);
        }
        if (numStr.length() == 7) {
            simple(numStr.charAt(0), queue);
            if (numStr.charAt(0) != '0')
                queue.add("Million");
            numStr = numStr.substring(1);
        }
        if (numStr.length() == 6) {
            three(numStr.charAt(0), numStr.charAt(1), numStr.charAt(2), queue);
            if (numStr.charAt(0) != '0' || numStr.charAt(1) != '0' || numStr.charAt(2) != '0')
                queue.add("Thousand");
            numStr = numStr.substring(3);
        }
        if (numStr.length() == 5) {
            two(numStr.charAt(0), numStr.charAt(1), queue);
            if (numStr.charAt(0) != '0' || numStr.charAt(1) != '0')
                queue.add("Thousand");
            numStr = numStr.substring(2);
        }
        if (numStr.length() == 4) {
            simple(numStr.charAt(0), queue);
            if (numStr.charAt(0) != '0')
                queue.add("Thousand");
            numStr = numStr.substring(1);
        }
        if (numStr.length() == 3) {
            three(numStr.charAt(0), numStr.charAt(1), numStr.charAt(2), queue);
            numStr = "";
        }
        if (numStr.length() == 2) {
            two(numStr.charAt(0), numStr.charAt(1), queue);
            numStr = "";
        }
        if (numStr.length() == 1) {
            simple(numStr.charAt(0), queue);
            numStr = "";
        }

        return String.join(" ", queue);
    }

    private static void three(char a, char b, char c, Queue<String> queue) {
        if (a == '0') {
            two(b, c, queue);
            return;
        }

        simple(a, queue);
        queue.add("Hundred");
        two(b, c, queue);
    }

    private static void two(char a, char b, Queue<String> queue) {
        if (a == '0') {
            simple(b, queue);
        } else {
            switch (a) {
                case '1' -> ten(b, queue);
                case '2' -> {
                    queue.add("Twenty");
                    simple(b, queue);
                }
                case '3' -> {
                    queue.add("Thirty");
                    simple(b, queue);
                }
                case '4' -> {
                    queue.add("Forty");
                    simple(b, queue);
                }
                case '5' -> {
                    queue.add("Fifty");
                    simple(b, queue);
                }
                case '6' -> {
                    queue.add("Sixty");
                    simple(b, queue);
                }
                case '7' -> {
                    queue.add("Seventy");
                    simple(b, queue);
                }
                case '8' -> {
                    queue.add("Eighty");
                    simple(b, queue);
                }
                case '9' -> {
                    queue.add("Ninety");
                    simple(b, queue);
                }
                default -> throw new RuntimeException();
            }
        }
    }

    private static void ten(char a, Queue<String> queue) {
        switch (a) {
            case '0' -> queue.add("Ten");
            case '1' -> queue.add("Eleven");
            case '2' -> queue.add("Twelve");
            case '3' -> queue.add("Thirteen");
            case '4' -> queue.add("Fourteen");
            case '5' -> queue.add("Fifteen");
            case '6' -> queue.add("Sixteen");
            case '7' -> queue.add("Seventeen");
            case '8' -> queue.add("Eighteen");
            case '9' -> queue.add("Nineteen");
            default -> throw new RuntimeException();
        };
    }

    private static void simple(char c, Queue<String> queue) {
        if (c == '0') {
            return;
        }

        queue.add(switch (c) {
            case '1' -> "One";
            case '2' -> "Two";
            case '3' -> "Three";
            case '4' -> "Four";
            case '5' -> "Five";
            case '6' -> "Six";
            case '7' -> "Seven";
            case '8' -> "Eight";
            case '9' -> "Nine";
            default -> throw new RuntimeException();
        });
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(1000000));
    }
}
