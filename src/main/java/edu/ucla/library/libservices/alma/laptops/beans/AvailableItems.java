
package edu.ucla.library.libservices.alma.laptops.beans;

public class AvailableItems {

    private String location;

    private int chromeBooks;

    private int macs;

    private int windows;

    private int iPads;

    public AvailableItems() {
        super();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setChromeBooks(int chromeBooks) {
        this.chromeBooks = chromeBooks;
    }

    public int getChromeBooks() {
        return chromeBooks;
    }

    public void setMacs(int macs) {
        this.macs = macs;
    }

    public int getMacs() {
        return macs;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public int getWindows() {
        return windows;
    }

    public void setIPads(int iPads) {
        this.iPads = iPads;
    }

    public int getIPads() {
        return iPads;
    }
}
