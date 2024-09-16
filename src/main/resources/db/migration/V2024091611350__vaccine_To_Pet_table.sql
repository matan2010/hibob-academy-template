create table vaccineToPet
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    vaccine_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,
    vaccination_date DATE DEFAULT now()
)