ALTER TABLE houses
    ADD COLUMN deleted_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL;
ALTER TABLE citizens
    ADD COLUMN deleted_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL;
ALTER TABLE flats
    ADD COLUMN deleted_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL;
ALTER TABLE streets
    ADD COLUMN deleted_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL;