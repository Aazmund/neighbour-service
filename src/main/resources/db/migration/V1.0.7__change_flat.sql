CREATE TABLE flats
(
    uid        UUID NOT NULL,
    number     VARCHAR(255),
    house_uid  UUID,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_flats PRIMARY KEY (uid)
);

ALTER TABLE flats
    ADD CONSTRAINT FK_FLATS_ON_HOUSE_UID FOREIGN KEY (house_uid) REFERENCES houses (uid);