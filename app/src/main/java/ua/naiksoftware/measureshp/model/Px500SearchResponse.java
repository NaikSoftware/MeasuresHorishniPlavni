package ua.naiksoftware.measureshp.model;

import java.util.List;

/**
 * Created by naik on 13.08.17.
 */

public class Px500SearchResponse {

    private int currentPage;
    private int totalPages;
    private int totalItems;
    private List<Px500Photo> photos;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<Px500Photo> getPhotos() {
        return photos;
    }
}
