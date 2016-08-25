package ru.dz.rmis.model.dto;

import java.io.Serializable;

/**
 *
 * @author vassaeve
 */
public class ImageDto implements Serializable {

    private static final long serialVersionUID = -1971645606381715556L;

    private Long id;
    private String description;
    private String typeOf;

    public ImageDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public ImageDto(Long id, String description, String typeOf) {
        this.id = id;
        this.description = description;
        this.typeOf = typeOf;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
