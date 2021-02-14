DROP SCHEMA midterm;
CREATE SCHEMA midterm;
USE midterm;

INSERT INTO user(id, name, password, username) VALUES
('1', 'name1', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username1'),
('2', 'name2','$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username2'),
('3', 'Maria','$2a$10$NcfKcfldbLAEojIUdYgzSujzMgWH6hbCnw7y92FDgSsof/Mg/3MhW', 'admin1');

INSERT INTO roles(id, name, user_id) VALUES
('1','ACCOUNTHOLDER', '1'),
('2','ACCOUNTHOLDER', '2'),
('3','ADMIN', '3');

INSERT INTO account_holder(date_of_birth, mailing_city, mailing_country, mailing_street, mailing_zip, city, country,street, zip_code, id) VALUES
('2010-06-13', 'aa', 'aa', 'aa', '11', 'aa', 'aa', 'aa', '12', '1'),
('2010-06-13', 'aa', 'aa', 'aa', '11', 'aa', 'aa', 'aa', '12', '2'),
('2010-06-13', 'aa', 'aa', 'aa', '11', 'aa', 'aa', 'aa', '12', '3');

INSERT INTO account(id, amount_balance, currency_balance, amount_penalty_fee, currency_penalty_fee, primary_owner, secondary_owner) VALUES
(1, '900', 'EUR', '90', 'EUR', '1', '2'),
(2, '800', 'EUR', '80', 'EUR', '2', '3');

INSERT INTO third_party(id, hashed_key, name) VALUES
(1, 246810, 'Tienda');

INSERT INTO savings(interest_rate, amount_minimum_balance, currency_minimum_balance, secret_key, status, id) VALUES
('0.01', '203', 'EUR', '124', 'ACTIVE', 3);

