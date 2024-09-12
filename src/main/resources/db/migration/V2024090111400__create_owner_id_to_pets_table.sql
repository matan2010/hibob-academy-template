ALTER TABLE pets
    ADD owner_id BIGINT;

ALTER TABLE pets
    ADD CONSTRAINT unique_company_owner UNIQUE (company_id, owner_id);
