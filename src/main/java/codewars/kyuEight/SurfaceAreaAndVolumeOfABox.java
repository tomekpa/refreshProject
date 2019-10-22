package codewars.kyuEight;

public class SurfaceAreaAndVolumeOfABox {

    private static final int AREA_INDEX = 0;
    private static final int VOLUME_INDEX = 1;

    public int[] getSize(int w, int h, int d) {
        int[] result = new int[2];
        result[AREA_INDEX] = calculateArea(w, h, d);
        result[VOLUME_INDEX] = calculateVolume(w, h, d);
        return result;
    }

    private int calculateArea(int w, int h, int d) {
        return 2 * (w * h + w * d + h * d);
    }

    private int calculateVolume(int w, int h, int d) {
        return w * h * d;
    }
}
