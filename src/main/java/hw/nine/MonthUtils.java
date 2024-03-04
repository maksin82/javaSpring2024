package hw.nine;

public final class MonthUtils {
    static final String[] monthArr = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};
    static int[] numberOfDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] numberOfWorkingDays = {17, 20, 20, 21, 20, 19, 23, 22, 21, 23, 21, 21};
    static int num = 0;

    public static String getMonth(int number) {
        num = number - 1;
        return monthArr[num];
    }

    public static String getMonth(String abbreviate) {
        if (abbreviate.length() <= 3 && abbreviate.charAt(0) > 96) {
            String[] abb = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug",
                    "sep", "oct", "nov", "dec"};

            abbreviate = abbreviate.toLowerCase();
            for (int i = 0; i < abb.length; i++) {
                if (abb[i].equals(abbreviate)) {
                    num = i;
                }
            }
            return monthArr[num];
        } else for (int i = 0; i < monthArr.length; i++) {
            if (monthArr[i].equals(abbreviate)) {
                num = i;
            }
        }
        return abbreviate;
    }

    public static Month[] firstQuarterArr() {
        return new Month[]{new Month(1), new Month(2), new Month(3)};
    }

    public static Month[] secondQuarterArr() {
        return new Month[]{new Month(4), new Month(5), new Month(6)};
    }

    public static Month[] thirdQuarterArr() {
        return new Month[]{new Month(7), new Month(8), new Month(9)};
    }

    public static Month[] fourthQuarterArr() {
        return new Month[]{new Month(10), new Month(11), new Month(12)};
    }

    public static Month[] halfYearArr() {
        return new Month[]{new Month(1), new Month(2), new Month(3),
                new Month(4), new Month(5), new Month(6)};
    }

    public static Month[] yearArr() {
        return new Month[]{new Month(1), new Month(2), new Month(3),
                new Month(4), new Month(5), new Month(6),
                new Month(7), new Month(8), new Month(9),
                new Month(10), new Month(11), new Month(12)};
    }

    public static int getNumberOfDays() {
        return numberOfDays[num];
    }

    public static int getNumberOfWorkingDays() {
        return numberOfWorkingDays[num];
    }
}