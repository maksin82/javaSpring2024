package hw;

public class hw6 {
    public static void main(String[] args) {
        String s = "Перестановочный алгоритм быстрого действия";
        String str = "Посмотрите как Рите нравится ритм";
        String[][] array = {{"Привет", "всем", "кто"}, {"изучает", "язык", "программирования"}, {"java"}};


        System.out.println("Задача №1: " + oooooo(s));

        System.out.println("Задача №2: " + eeee(s));

        System.out.println("Задача №3: " + numInArr(array));

        rit(str);

    }

    //6.1
    public static String oooooo(String str) {
//        int amountOfO = str.length() - str.replace("о", "").length();
//        String o = "о";
//        return o.repeat(amountOfO);
        String o = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'о') {
                o += str.charAt(i);
            }
        }
        return o;
    }

    //6.2
    public static int eeee(String str) {
        return str.length() - str.replace("е", "").length();
    }

    //6.3
    public static int numInArr(String[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!arr[i][j].contains("е")) {
                    count++;
                }
            }
        }
        return count;
    }

    //*
    public static void rit(String str) {
        str = str.toLowerCase();
        String subStr = "рит";
//        String strRit = "";
//        int count = 0;
//        do {
//            count = str.indexOf(subStr, count);
//            strRit += count + " ";
//            count ++;
//        } while (str.indexOf(subStr, count) > 0);

        for (int i = 0; i <= str.length() - subStr.length(); i++) {
            String temp = str.substring(i,i + subStr.length());
            if (temp.equals(subStr)) {
                System.out.println(i);
            }
        }

//        System.out.println(strRit);
    }

    //6.2.1

}
