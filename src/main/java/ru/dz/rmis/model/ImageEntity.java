package ru.dz.rmis.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author vassaeve
 */
@Entity
@Table(name = "IMAGES")
public class ImageEntity implements Serializable {

    private static final long serialVersionUID = -9040139787828676099L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

     @Enumerated(EnumType.STRING)
    @Column(name = "type_of ")
    private ImageTypeOf typeOf;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "IMAGE")
    private byte[] image;

    public ImageEntity() {
    }

    public ImageEntity(Long id) {
        this.id = id;
    }

    public ImageTypeOf getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(ImageTypeOf typeOf) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImageEntity)) {
            return false;
        }
        ImageEntity other = (ImageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.dz.rmis.model.Images[ id=" + id + " ]";
    }

}
