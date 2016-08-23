package ru.dz.rmis.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex on 22.08.16.
 */

@Entity
@Table
public class ProcessedImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Image originalImage;

    // List of value pairs such as [{x: 1, y: 1}, {x: 2, y: 2}]
    @Column
    private String coordinaatesJson;

    public ProcessedImage() {
    }

    public ProcessedImage(Image originalImage, String coordinaatesJson) {
        this.originalImage = originalImage;
        this.coordinaatesJson = coordinaatesJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(Image originalImage) {
        this.originalImage = originalImage;
    }

    public String getCoordinaatesJson() {
        return coordinaatesJson;
    }

    public void setCoordinaatesJson(String coordinaatesJson) {
        this.coordinaatesJson = coordinaatesJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessedImage that = (ProcessedImage) o;

        if (!id.equals(that.id)) return false;
        if (!originalImage.equals(that.originalImage)) return false;
        return coordinaatesJson.equals(that.coordinaatesJson);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + originalImage.hashCode();
        result = 31 * result + coordinaatesJson.hashCode();
        return result;
    }
}
