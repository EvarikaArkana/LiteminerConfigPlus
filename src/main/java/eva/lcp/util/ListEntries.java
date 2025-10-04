package eva.lcp.util;

public enum ListEntries {
    Shapeless(0),
    Square(4),
    Downstairs(3),
    Upstairs(2),
    Small_tunnel(1);

    public final int index;
    ListEntries(int index) {
        this.index = index;
    }
}
