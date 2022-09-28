CREATE TABLE flats
(
    uid    UUID NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_flats PRIMARY KEY (uid)
);