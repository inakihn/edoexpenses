create table expenses (
	id identity,
	user varchar(30) not null,
	description varchar(30) not null,
	value varchar(50) not null,
	date varchar(13)
);

insert into expenses (user, description, value, date) values ('eneko', 'food', '50', 'yesterday');
insert into expenses (user, description, value, date) values ('eneko', 'shoes', '99', '2 days ago');