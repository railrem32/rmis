package ru.dz.rmis.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alex on 23.08.16.
 */
public class ImageDto implements Serializable {

    private static final long serialVersionUID = -1971645606381715556L;

    private Long id;

    @NotNull
    @Size(min = 1)
    private String description;

    @NotNull
    private String studyId;

    @NotNull
    private String patientId;

    @NotNull
    private Date created;

    @NotNull
    private byte[] imageBytes;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
