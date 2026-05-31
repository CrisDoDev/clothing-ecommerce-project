package ui;

import java.awt.Color;

// Dai dien 1 muc trong sidebar: ten nhom, ten hien thi, mau cham tron, ma card layout
public class SidebarItem {
    private final String section;
    private final String displayName;
    private final Color bulletColor;
    private final String cardKey;

    public SidebarItem(String section, String displayName, Color bulletColor, String cardKey) {
        this.section = section;
        this.displayName = displayName;
        this.bulletColor = bulletColor;
        this.cardKey = cardKey;
    }

    public String section() { return section; }
    public String displayName() { return displayName; }
    public Color bulletColor() { return bulletColor; }
    public String cardKey() { return cardKey; }
}