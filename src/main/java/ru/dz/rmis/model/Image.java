package ru.dz.rmis.model;

import ru.dz.rmis.dto.ImageDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Alex on 22.08.16.
 */
@Entity
@Table(name = "Images")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "study_id")
    private Long studyId;

    @Column(nullable = false, name = "patient_id")
    private Long patientId;

    @Column
    private Date date;

    @Column
    private byte[] image;

    @OneToOne
    private ProcessedImage processedImage;

    public Image() {
    }

    public Image(String name, Long studyId, Long patientId, Date date, byte[] image) {
        this.name = name;
        this.studyId = studyId;
        this.patientId = patientId;
        this.date = date;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ProcessedImage getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(ProcessedImage processedImage) {
        this.processedImage = processedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (!id.equals(image1.id)) return false;
        if (!name.equals(image1.name)) return false;
        if (!studyId.equals(image1.studyId)) return false;
        if (!patientId.equals(image1.patientId)) return false;
        if (!date.equals(image1.date)) return false;
        return Arrays.equals(image, image1.image);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + studyId.hashCode();
        result = 31 * result + patientId.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
