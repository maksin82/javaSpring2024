package javaAutomatioCourse.parametrased;

public class SpeedLights {
    /*
     * green
     * yellow <=20
     * red >20
     * */
    public String showLights(int currentSpeed) {
        if (currentSpeed < 60) {
            return "green";
        } else if (currentSpeed <= 80) {
            return "yellow";
        } else if (currentSpeed<=120) {
            return "red";
        } else throw new IllegalArgumentException("devilSpeed");
    }
}
