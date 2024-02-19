import java.util.Arrays;

public class Codewars {
    public static void main(String[] args) {

        String time = "The greatest 2 victory is that 3 which requires no battle";
        String num = "1234";
        int a = 3;
        int b = 4;
        int[] d = {34, 15, 88, 2};
        double c = 0.5;
        Block brik = new Block(new int[]{2, 6, 7});

        System.out.println(declareWinner(new Fighter("Harald", 208, 5), new Fighter("Harry", 5, 4), "Harry"));

    }

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        String winner = "";
        if (firstAttacker.equals(fighter1.name)) {
            do {
                fighter2.health -= fighter1.damagePerAttack;
                if (fighter2.health <= 0) {
                    winner = fighter1.name;
                    break;
                }
                fighter1.health -= fighter2.damagePerAttack;
                if (fighter1.health <= 0) {
                    winner = fighter2.name;
                    break;
                }
            } while (fighter1.health >= 0 || fighter2.health >= 0);
        } else {
            do {
                fighter1.health -= fighter2.damagePerAttack;
                if (fighter1.health <= 0) {
                    winner = fighter2.name;
                    break;
                }
                fighter2.health -= fighter1.damagePerAttack;
                if (fighter2.health <= 0) {
                    winner = fighter1.name;
                    break;
                }
            } while (fighter1.health >= 0 || fighter2.health >= 0);
        }
        return winner;
    }

    public static int findSmallestInt(int[] args) {
//        int[] arr = Arrays.stream(args).sorted().toArray();
//        return arr[0];
        Arrays.sort(args);
        return args[0];
    }

    public static boolean isLove(final int flower1, final int flower2) {
        return (flower1 + flower2) % 2 != 0;
//        return flower1 % 2 != flower2 % 2;
    }

    public static int makeNegative(final int x) {
        return Math.abs(x) * (-1);
    }

    public static boolean isEven(double n) {
        return n % 2 == 0;

//        return n % 2 ==0 ? true : false;
    }

    public static int simpleMultiplication(int n) {
        return n % 2 == 0 ? n * 8 : n * 9;
//        return n * (n % 2 + 8);
    }

    public static int paperWork(int n, int m) {
        if (n < 0 || m < 0) {
            return 0;
        } else return Math.multiplyExact(n, m);

//        return (n < 0) || (m < 0) ? 0 : n * m;

//        return max(m, 0) * max(n, 0);
    }

    public static String numberToString(int num) {
//         return Integer.toString(num);
//        return num+"";
        return String.valueOf(num);

    }

    public static int stringToNumber(String str) {
//        return Integer.parseInt(str);
        return Integer.valueOf(str);
    }

    public static String remove(String str) {
        return str.substring(1, str.length() - 1);
    }

    public static String[] stringToArray(String s) {
        String[] split = s.split("\\d"); // d- цифра s - пробел
        return split;
    }

    public static String reverseWords(String str) {

        String[] arr = str.split(" ");
        String[] newArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[arr.length - 1 - i];
        }
        String sr = "";
        for (int i = 0; i < newArr.length; i++) {
            sr += newArr[i];
            sr += " ";
        }
        return sr.trim();
//        String[] str2 = str.split(" ");
//        String fin = "";
//        for(int i=str2.length-1;i>=0;i--){
//            fin += str2[i];
//            if(i>0)fin+=" ";
//        }
//        return fin;

//        List Words = Arrays.asList(str.split(" "));
//        Collections.reverse(Words);
//        return String.join(" ", Words);

//        return Arrays.stream(str.split(" ")).reduce((x, y) -> y+" "+x).get();
    }

    public static int howOld(final String herOld) {
        String age = String.valueOf(herOld.charAt(0));
        return Integer.parseInt(age);
//        String[] s = herOld.split(" ");
//        return Integer.valueOf(s[0]);

//        String s = herOld.substring(0,1);
//        return Integer.parseInt(s);

//        return Character.getNumericValue(herOld.charAt(0));
    }

    public static String noSpace(final String x) {
//        List simbol = Arrays.asList(x.split(" "));
        return String.join("", x.split(" "));

//        return x.replace(" ", "");
//        return x.replaceAll("\\s+","");
    }

    public static String solution(String str) {
        String newstr = "";
        for (int i = 0; i < str.length(); i++) {
            newstr += str.charAt(str.length() - i - 1);
        }
        return newstr;

//        for(int i = str.length()-1; i >= 0; i--){
//            newstr += str.charAt(i);}

//        return Stream.of(str)
//                .map(s -> new StringBuilder(s).reverse())
//                .collect(Collectors.joining());


    }

    static String encode(String word) {
        word = word.toLowerCase();
        String encodeWord = "";

        for (int i = 0; i < word.length(); i++) {
            String newS = "";
            newS = word.substring(0, i);
            newS += word.substring(i + 1);

            char w = word.charAt(i);
            String count = String.valueOf(w);
            if (newS.contains(count)) {
                encodeWord += ")";
            } else encodeWord += "(";
        }
        return encodeWord;

//        word = word.toLowerCase();
//        String result = "";
//        for (int i = 0; i < word.length(); ++i) {
//            char c = word.charAt(i);
//            result += word.lastIndexOf(c) == word.indexOf(c) ? "(" : ")";
//        }
//        return result;

    }

    public static double twoDecimalPlaces(double number) {
        number = number * 100;
        number = (double) ((int) number);
        number = number / 100;
        return number;
    }

    public static int Liters(double time) {
//(int) (time * 0.5);
        return (int) time / 2;
    }

    public static boolean getXO(String str) {
//         str = str.toLowerCase();
//        char charX = 'x';
//        char charO = 'o';
//        int countX = 0;
//        int countO = 0;
//
//        for (int i = 0; i < str.length(); i++) {
//            if (str.charAt(i) == charX) {
//                countX++;
//            }
//            if (str.charAt(i) == charO) {
//                countO++;
//            }
//        }
//
//        if (!str.contains("x") && !str.contains("o")) {
//            return true;
//        } else if (countX == countO) {
//            return true;
//        } else {
//            return false;
//        }
        str = str.toLowerCase();
        return str.replace("o", "").length() == str.replace("x", "").length();
    }

    public static String highAndLow(String numbers) {
        String[] str = numbers.split(" ", 0);

        int[] ints = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            ints[i] = Integer.parseInt(str[i]);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] < min) {
                min = ints[i];
            }
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] > max) {
                max = ints[i];
            }
        }

        return min + " " + max;
    }

}


