public class Coordinates {
    int x;
    int y;
    Coordinates parent;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Coordinates(int x, int y, Coordinates parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Coordinates getParent() {
        return parent;
    }
}
