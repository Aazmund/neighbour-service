ALTER TABLE flats ADD COLUMN house_uid UUID;
ALTER TABLE flats
    ADD CONSTRAINT FK_FLATS_ON_HOUSE_UID FOREIGN KEY (house_uid) REFERENCES houses (uid);