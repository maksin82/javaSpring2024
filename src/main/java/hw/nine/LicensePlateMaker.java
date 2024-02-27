package hw.nine;

public class LicensePlateMaker {
    static String prefix = "CA";
    static int num = 1;

    static String getCarNum() {
        String carNum = prefix + "-" + num;
        num++;
        return carNum;
    }


}
