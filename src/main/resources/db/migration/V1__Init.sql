CREATE TABLE owner
(
    id bigserial not null constraint owner_pkey primary key,
    created_at timestamp not null,
    modified_at timestamp not null,
    version bigint not null,
    first_name varchar(255),
    last_name varchar(255)
);

CREATE TABLE company (
  id bigserial constraint company_pkey primary key,
  created_at timestamp not null,
  modified_at timestamp not null,
  version bigint not null,
  chargesgst boolean not null,
  chargespst boolean not null,
  code varchar(255),
  name varchar(255),
  owner_id bigint not null constraint fk_company_owner_id references owner
);

CREATE TABLE employee
(
    id bigserial not null constraint employee_pkey primary key,
    created_at timestamp not null,
    modified_at timestamp not null,
    version bigint    not null,
    first_name varchar(255),
    last_name varchar(255),
    position varchar(255),
    salary bigint,
    title varchar(255),
    company_id bigint not null constraint fk_employee_company_id references company
);

CREATE TABLE product
(
    id bigserial not null constraint product_pkey primary key,
    created_at timestamp not null,
    modified_at timestamp not null,
    version bigint    not null,
    comments varchar(255),
    name varchar(255),
    price numeric(19, 2),
    company_id bigint not null constraint fk_product_company_id references company
);

-- Owners

INSERT INTO owner (first_name, last_name, version, created_at, modified_at) VALUES ('Sterling', 'Archer', 0, now(), now());
INSERT INTO owner (first_name, last_name, version, created_at, modified_at) VALUES ('Malory', 'Archer', 0, now(), now());

-- Companies

INSERT INTO company (code, name, chargesgst, chargespst, version, owner_id, created_at, modified_at)
SELECT 'ABCD', 'The Alphabet', true, false, 0, id, now(), now() FROM owner WHERE first_name = 'Sterling';
INSERT INTO company (code, name, chargesgst, chargespst, version, owner_id, created_at, modified_at)
SELECT 'ABBA', 'The Abba Company', true, false, 0, id, now(), now() FROM owner WHERE first_name = 'Malory';
INSERT INTO company (code, name, chargesgst, chargespst, version, owner_id, created_at, modified_at)
SELECT 'FAKE', 'Fake Company', true, true, 0, id, now(), now() FROM owner WHERE first_name = 'Malory';
INSERT INTO company (code, name, chargesgst, chargespst, version, owner_id, created_at, modified_at)
SELECT 'EXMP', 'Example', false, false, 0, id, now(), now() FROM owner WHERE first_name = 'Malory';

-- Employees

INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Sterling', 'Archer', 100000, 'CEO', 'S-10', 0, id, now(), now() FROM company WHERE code = 'ABCD';
INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Cyril', 'Figgis', 200000, 'Manager', 'S-8', 0, id, now(), now() FROM company WHERE code = 'ABCD';
INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Pamela', 'Poovey', 200000, 'Analyst', 'S-10', 0, id, now(), now() FROM company WHERE code = 'ABCD';

INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Malory', 'Archer', 200000, 'CEO', 'S-10', 0, id, now(), now() FROM company WHERE code = 'ABBA';
INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Cherly', 'Tunt', 200000, 'Director', 'S-10', 0, id, now(), now() FROM company WHERE code = 'ABBA';
INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Arthur', 'Woodhouse', 200000, 'Maintenance', 'S-10', 0, id, now(), now() FROM company WHERE code = 'ABBA';

INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Algernop', 'Kreiger', 200000, 'Doctor', 'S-10', 0, id, now(), now() FROM company WHERE code = 'EXMP';
INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Lana', 'Kane', 200000, 'CEO', 'S-10', 0, id, now(), now() FROM company WHERE code = 'EXMP';

INSERT INTO employee (first_name, last_name, salary, title, position, version, company_id, created_at, modified_at)
SELECT 'Barry', 'Dylan', 200000, 'CEO', 'S-10', 0, id, now(), now() FROM company WHERE code = 'FAKE';

-- Products

INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Crayons', 3.99, 'Keep the kid busy.', 0, id, now(), now() FROM company WHERE code = 'ABCD';
INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Diapers', 24.99, 'For those unexplainable messes.', 0, id, now(), now() FROM company WHERE code = 'ABCD';
INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Powder', 399.99, 'When travelling to Miami.', 0, id, now(), now() FROM company WHERE code = 'ABCD';

INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Bourbon', 99.99, 'Dealing with issues.', 0, id, now(), now() FROM company WHERE code = 'ABBA';

INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'TEC-9', 999.99, 'Two are better than one.', 0, id, now(), now() FROM company WHERE code = 'EXMP';
INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Grenade', 49.99, 'When bullets won''t do.', 0, id, now(), now() FROM company WHERE code = 'EXMP';

INSERT INTO product (name, price, comments, version, company_id, created_at, modified_at)
SELECT 'Spare Parts', 9.95, 'Mr fixit.', 0, id, now(), now() FROM company WHERE code = 'FAKE';

