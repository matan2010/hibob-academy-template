create table pets
(
    id UUID primary key default gen_random_uuid() ,
    name varchar(100) not null ,
    type varchar(100) not null ,
    company_id int,
    date_of_arrival date not null
);
CREATE INDEX idx_name_company_id ON pets(company_id);
