ALTER TABLE pets
    ADD owner_id BIGINT;

CREATE UNIQUE INDEX idx_pets_company_id_and_owner_id ON pets(company_id, owner_id);