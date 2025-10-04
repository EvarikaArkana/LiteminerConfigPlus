package eva.lcp.util;

public enum ListEntries {
    Shapeless(0),
    Square(1),
    Downstairs(2),
    Upstairs(3),
    Small_tunnel(4);

    public final int index;
    ListEntries(int index) {
        this.index = index;
    }
}
