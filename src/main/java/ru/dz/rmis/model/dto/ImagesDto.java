package ru.dz.rmis.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vassaeve
 */
public class ImagesDto implements Serializable {

    private static final long serialVersionUID = -9181894037559281912L;

    private int size;
    private List<ImageDto> images;

    public ImagesDto() {
        this.images = new ArrayList<>(0);
    }

    public ImagesDto(List<ImageDto> list) {
        this.images = new ArrayList<>(list);
    }

    public ImagesDto(int size, List<ImageDto> list) {
        this.size = size;
        this.images = new ArrayList<>(list);
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setImages(List<ImageDto> images) {
        this.images.clear();
        this.images.addAll(images);
    }

}
