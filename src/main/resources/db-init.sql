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
insert into pet (personId, name) values (1, 'Samson'); -- id: 1
insert into pet (personId, name) values (1, 'Steady'); -- id: 2
insert into pet (personId, name) values (1, 'Leonidas'); -- id: 3
insert into pet (personId, name) values (1, 'Traveller'); -- id: 4
insert into pet (personId, name) values (1, 'Magnolia '); -- id: 5
insert into pet (personId, name) values (1, 'Drunkard '); -- id: 6
insert into pet (personId, name) values (1, 'Mopsey'); -- id: 7
insert into pet (personId, name) values (1, 'Taster'); -- id: 8
insert into pet (personId, name) values (1, 'Cloe'); -- id: 9
insert into pet (personId, name) values (1, 'Tipsy'); -- id: 10
insert into pet (personId, name) values (1, 'Tipler'); -- id: 11
insert into pet (personId, name) values (1, 'Forester'); -- id: 12
insert into pet (personId, name) values (1, 'Captain'); -- id: 13
insert into pet (personId, name) values (1, 'Lady Rover'); -- id: 14
insert into pet (personId, name) values (1, 'Vulcan'); -- id: 15
insert into pet (personId, name) values (1, 'Sweet Lips'); -- id: 16
insert into pet (personId, name) values (1, 'Searcher'); -- id: 17
insert into pet (personId, name) values (1, 'Rozinante'); -- id: 18
insert into pet (personId, name) values (2, 'Juno'); -- id: 19
insert into pet (personId, name) values (2, 'Satan'); -- id: 20
insert into pet (personId, name) values (2, 'Cleopatra'); -- id: 21
insert into pet (personId, name) values (2, 'Caesar'); -- id: 22
insert into pet (personId, name) values (3, 'Dick'); -- id: 23
insert into pet (personId, name) values (7, 'Sam Patch'); -- id: 24
insert into pet (personId, name) values (7, 'Emily'); -- id: 25
insert into pet (personId, name) values (7, 'Lady Nashville'); -- id: 26
insert into pet (personId, name) values (7, 'Bolivia'); -- id: 27
insert into pet (personId, name) values (7, 'Thruxton'); -- id: 28
insert into pet (personId, name) values (7, 'Poll'); -- id: 29
insert into pet (personId, name) values (9, 'Sukey'); -- id: 30
insert into pet (personId, name) values (10, 'The General'); -- id: 31
insert into pet (personId, name) values (10, 'Le Beau'); -- id: 32
insert into pet (personId, name) values (10, 'Johnny Ty'); -- id: 33
