package pl.mazsak.mag.mesh;

public class PackedIndex {
    private int v;
    private int u;
    private int n;

    public PackedIndex(int v, int u, int n) {
        this.v = v;
        this.u = u;
        this.n = n;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
