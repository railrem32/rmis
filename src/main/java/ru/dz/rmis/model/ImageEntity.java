package ru.dz.rmis.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Alex on 22.08.16.
 */
@Entity
@Table(name = "Images")
public class ImageEntity implements Serializable {

    private static final long serialVersionUID = -7675248942682135857L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "study_id")
    private String studyId;

    @Column(nullable = false, name = "patient_id")
    private String patientId;

    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "IMAGE")
    private byte[] image;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of ")
    private ImageTypeOf typeOf;

    @JoinColumn(name = "PROCESSED_IMAGE_ID", referencedColumnName = "ID")
    @OneToOne
    private ProcessedImage processedImage;

    public ImageEntity() {
    }

    public ImageEntity(String description, String studyId, String patientId, Date created, byte[] image) {
        this.description = description;
        this.studyId = studyId;
        this.patientId = patientId;
        this.created = created;
        this.image = image;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public ImageTypeOf getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(ImageTypeOf typeOf) {
        this.typeOf = typeOf;
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

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = Arrays.copyOf(image, image.length);
    }

    public ProcessedImage getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(ProcessedImage processedImage) {
        this.processedImage = processedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageEntity image1 = (ImageEntity) o;

        if (!id.equals(image1.id)) {
            return false;
        }
        if (!description.equals(image1.description)) {
            return false;
        }
        if (!studyId.equals(image1.studyId)) {
            return false;
        }
        if (!patientId.equals(image1.patientId)) {
            return false;
        }
        if (!created.equals(image1.created)) {
            return false;
        }
        return Arrays.equals(image, image1.image);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + studyId.hashCode();
        result = 31 * result + patientId.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
