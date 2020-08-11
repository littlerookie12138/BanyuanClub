create table Users{
	id int PRIMARY KEY,
	username varchar(20),
	e-mail varchar(20),
	pwd varchar(11),
	phone long
}

create table Manger{
	id int PRIMARY KEY,
	managername varchar(20),
	e-mail varchar(20),
	pwd varchar(11)
}

create table autionthing{
	id int primary key,
	lotname varchar(20),
	startprice long ,
	enddate date,
	description varchar(50)
}

create table pricehistory{
	id int primary key,
	lotid int,
	userid int,
	pricedate date
}

create table price{
	id int primary key,
	lotid int,
	startprice long,
	endprice long
}

