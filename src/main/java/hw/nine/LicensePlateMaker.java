package hw.nine;

public class LicensePlateMaker {
    private String prefix;
    private static int num = 0;
//    private int num = 0;

    public LicensePlateMaker(String prefix) {
        this.prefix = prefix;
    }

    public LicensePlate getCarNum() {
        num++;
        return new LicensePlate(prefix + "-" + num);
    }


}
