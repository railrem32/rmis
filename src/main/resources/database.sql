CREATE TABLE filter (
    id BIGINT IDENTITY PRIMARY KEY,
    description varchar(30),
    class_name varchar(60)
);


CREATE TABLE images (
    id BIGINT IDENTITY PRIMARY KEY,
    description VARCHAR(250),
    study_id VARCHAR(20),
    patient_id VARCHAR(20),
    image blob,
    created date,
    comment VARCHAR(800),
    type_of varchar(3),
    processed_image_id bigint
);

CREATE TABLE processedImage (
    id BIGINT IDENTITY PRIMARY KEY,
    coordinates_json LONGVARCHAR
);

alter table images add foreign key (processed_image_id) references processedImage (id);

alter table images add column created date;