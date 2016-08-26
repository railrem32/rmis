package ru.dz.rmis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Alex on 22.08.16.
 */
@Entity
@Table(name = "processedImage")
public class ProcessedImage implements Serializable {

    private static final long serialVersionUID = 3140639417151360885L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "processedImage")
    private ImageEntity originalImage;

        
    // List of value pairs such as [{x: 1, y: 1}, {x: 2, y: 2}]
    @Column(name = "COORDINATES_JSON")
    private String coordinaatesJson;

    public ProcessedImage() {
    }

    public ProcessedImage(ImageEntity originalImage, String coordinaatesJson) {
        this.originalImage = originalImage;
        this.coordinaatesJson = coordinaatesJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageEntity getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(ImageEntity originalImage) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProcessedImage that = (ProcessedImage) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (!originalImage.equals(that.originalImage)) {
            return false;
        }
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
