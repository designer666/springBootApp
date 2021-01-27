insert into company (id, name) values (1, 'Sony'), (2, 'Panasonic');

select setval('company_id_seq', (select max(id) from company));