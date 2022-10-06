CREATE TABLE houses
(
    uid        UUID NOT NULL,
    number     VARCHAR(255),
    street_uid UUID,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_houses PRIMARY KEY (uid)
);

ALTER TABLE houses
    ADD CONSTRAINT FK_HOUSES_ON_STREET_UID FOREIGN KEY (street_uid) REFERENCES streets (uid);