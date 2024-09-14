ALTER TABLE pets
    ADD owner_id BIGINT;

CREATE UNIQUE INDEX unique_company_owner ON pets(company_id, owner_id);