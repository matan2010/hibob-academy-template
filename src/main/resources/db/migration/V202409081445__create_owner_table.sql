create table owner
(
    owner_id serial primary key,
    name varchar(100) not null,
    company_id int,
    employee_id int
);
CREATE INDEX idx_name_owner ON owner(owner_id);
CREATE INDEX idx_name_employee_id ON owner(employee_id);
/*INSERT INTO pets ( name, type, company_id, date_of_arrival) VALUES
('Buddy', 'Dog', 101, '2023-01-15'),
('Mittens', 'Cat', 102, '2022-06-20'),
('Charlie', 'Dog', 103, '2021-08-05'),
('Whiskers', 'Cat', 104, '2023-03-10'),
('Goldie', 'Fish', 105, '2022-09-25');*/

/*SELECT * FROM pets WHERE type = 'Dog';*/

/*DELETE FROM pets WHERE id = 3;*/

/*SELECT * FROM pets WHERE date_of_arrival < (CURRENT_DATE-INTERVAL '1 YEAR')*/