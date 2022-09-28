CREATE TABLE citizens
(
    uid      UUID NOT NULL,
    name     VARCHAR(255),
    flat_uid UUID NOT NULL,
    CONSTRAINT pk_citizens PRIMARY KEY (uid)
);

ALTER TABLE citizens
    ADD CONSTRAINT FK_CITIZENS_ON_FLAT_UID FOREIGN KEY (flat_uid) REFERENCES flats (uid);