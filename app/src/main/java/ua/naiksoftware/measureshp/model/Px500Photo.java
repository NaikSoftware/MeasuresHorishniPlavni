package ua.naiksoftware.measureshp.model;

import java.util.List;

/**
 * Created by naik on 13.08.17.
 */

public class Px500Photo {

    public static final int SIZE_1080 = 1080;
    public static final int SIZE_1600 = 1600;
    public static final int SIZE_2048 = 2048;

    private String name;
    private String description;
    private List<Px500Image> images;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Px500Image> getImages() {
        return images;
    }
}
