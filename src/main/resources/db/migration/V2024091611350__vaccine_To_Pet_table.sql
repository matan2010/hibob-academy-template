create table vaccineToPet
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    vaccineId BIGINT NOT NULL,
    petId BIGINT NOT NULL,
    vaccinationDate DATE DEFAULT now()
)