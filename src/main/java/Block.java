public class Block {
    int width;
    int length;
    int height;

    public Block(int[] brick) {
        width = brick[0];
        length = brick[1];
        height = brick[2];
    }

    @Override
    public String toString() {
        return "Block{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getVolume() {
        int volume = height * length * width;
        return volume;
    }

    public int getSurfaceArea() {
        int area = 2 * (height * width + width * length + height * length);
        return area;
    }
}
