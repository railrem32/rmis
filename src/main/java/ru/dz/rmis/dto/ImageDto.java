package ru.dz.rmis.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alex on 23.08.16.
 */
public class ImageDto implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private Long studyId;

    @NotNull
    private Long patientId;

    @NotNull
    private Date date;

    @NotNull
    private MultipartFile multipartFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
