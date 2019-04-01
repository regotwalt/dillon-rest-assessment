connect 'jdbc:derby:memory:sample;create=true';

-- in case these are left over from previous tests...
drop table Pet;
drop table Person;

create table Person(
	id bigint not null generated always as identity (start with 1, increment by 1) constraint person_pk primary key,
	firstname varchar(50),
	middlename varchar(50),
	lastname varchar(50)
);

create table Pet(
	id bigint not null generated always as identity (start with 1, increment by 1),
	personId bigint not null constraint pet_fk references Person on delete cascade on update restrict,
	name varchar(50)
);

insert into person (firstname, middlename, lastname) values ('George', null, 'Washington');
insert into person (firstname, middlename, lastname) values ('John', null, 'Adams');
insert into person (firstname, middlename, lastname) values ('Thomas',null,'Jefferson');
insert into person (firstname, middlename, lastname) values ('James',null,'Madison');
insert into person (firstname, middlename, lastname) values ('James' ,null, 'Monroe');
insert into person (firstname, middlename, lastname) values ('John','Quincy','Adams');
insert into person (firstname, middlename, lastname) values ('Andrew',null,'Jackson');
insert into person (firstname, middlename, lastname) values ('Martin',null,'Van Buren');
insert into person (firstname, middlename, lastname) values ('William','Henry','Harrison');
insert into person (firstname, middlename, lastname) values ('John',null,'Tyler');

-- Source: http://www.presidentialpetmuseum.com/whitehousepets-4/
insert into pet (personId, name) values (1, 'Samson');
insert into pet (personId, name) values (1, 'Steady');
insert into pet (personId, name) values (1, 'Leonidas');
insert into pet (personId, name) values (1, 'Traveller');
insert into pet (personId, name) values (1, 'Magnolia ');
insert into pet (personId, name) values (1, 'Drunkard ');
insert into pet (personId, name) values (1, 'Mopsey');
insert into pet (personId, name) values (1, 'Taster');
insert into pet (personId, name) values (1, 'Cloe');
insert into pet (personId, name) values (1, 'Tipsy');
insert into pet (personId, name) values (1, 'Tipler');
insert into pet (personId, name) values (1, 'Forester');
insert into pet (personId, name) values (1, 'Captain');
insert into pet (personId, name) values (1, 'Lady Rover');
insert into pet (personId, name) values (1, 'Vulcan');
insert into pet (personId, name) values (1, 'Sweet Lips');
insert into pet (personId, name) values (1, 'Searcher');
insert into pet (personId, name) values (1, 'Rozinante');
insert into pet (personId, name) values (2, 'Juno');
insert into pet (personId, name) values (2, 'Satan');
insert into pet (personId, name) values (2, 'Cleopatra');
insert into pet (personId, name) values (2, 'Caesar');
insert into pet (personId, name) values (3, 'Dick');
insert into pet (personId, name) values (7, 'Sam Patch');
insert into pet (personId, name) values (7, 'Emily');
insert into pet (personId, name) values (7, 'Lady Nashville');
insert into pet (personId, name) values (7, 'Bolivia');
insert into pet (personId, name) values (7, 'Thruxton');
insert into pet (personId, name) values (7, 'Poll');
insert into pet (personId, name) values (9, 'Sukey');
insert into pet (personId, name) values (10, 'The General');
insert into pet (personId, name) values (10, 'Le Beau');
insert into pet (personId, name) values (10, 'Johnny Ty');
