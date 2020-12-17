-- Database: Professional Maintenance
DROP DATABASE "Professional Maintenance";

CREATE DATABASE "Professional Maintenance"

Grant all privileges on all tables in schema public to postgres;

drop table if exists SYSTEM_ADMIN cascade;
drop table if exists PLANNER cascade;
drop table if exists MAINTAINER cascade;
drop table if exists ACTIVITY cascade;
drop table if exists COMPETENCE cascade;
drop table if exists MATERIAL cascade;
drop table if exists TYPOLOGY cascade;
drop table if exists AVAILABILITY cascade;

drop view if exists PLANNED_ACTIVITIES cascade;

drop table if exists MAINTAINER_ACTIVITIES cascade;
drop table if exists MAINTAINER_COMPETENCES cascade;
drop table if exists ACTIVITY_COMPETENCES cascade;
drop table if exists ACTIVITY_MATERIALS cascade;
drop table if exists ACTIVITY_TYPOLOGIES cascade;

create table SYSTEM_ADMIN(
	USERNAME VARCHAR(30) Primary Key,
	PASSWORD_ VARCHAR(10) not null
);

create table PLANNER(
	USERNAME VARCHAR(30) Primary Key,
	PASSWORD_ VARCHAR(10) not null	
);

create table MAINTAINER(
	USERNAME VARCHAR(30) Primary Key,
	PASSWORD_ VARCHAR(10) not null	
);

create table ACTIVITY(
	ID_CODE 		INTEGER 	   Primary Key,
	WEEK 			INTEGER			not null,
	FACTORY_SITE	VARCHAR(20)		not null,
	DEPARTMENT		VARCHAR(20)		not null,
	TYPOLOGY		VARCHAR(20)		not null,
	STATUS			BOOL 			not null,
	ESTIMATED_TIME 	INTEGER 		not null,
	TYPE_			VARCHAR(20)		not null,
	DESCRIPTION 	VARCHAR(40) 	not null,
	INTERRUPTIBLE 	BOOL,
	
	CHECK (WEEK<=52)
);

create table COMPETENCE(
	NOME VARCHAR(30) Primary Key
);


create table AVAILABILITY(
	Maintainer VARCHAR(30),
	Week 	   INT,
	WeekDay    VARCHAR(3),
	TimeSlot   INT,
	Avail	   INT,
	Primary Key (Maintainer, Week, WeekDay, TimeSlot),
	Foreign Key (Maintainer) references Maintainer(Username)
		on delete cascade on update cascade	
);


create view PLANNER_ACTIVITIES as
select * from ACTIVITY where STATUS = FALSE;


create table MAINTAINER_ACTIVITIES(
	Maintainer VARCHAR(30),
	Activity INTEGER ,
	Primary Key (Maintainer, Activity),
	Foreign Key (Maintainer) references Maintainer(Username)
		on delete restrict on update cascade,
	Foreign Key (Activity) references Activity(ID_Code)
		on delete restrict on update cascade
);

create table MAINTAINER_COMPETENCES(
	Maintainer VARCHAR(30),
	Competence VARCHAR(30),
	Primary Key (Maintainer, Competence),
	Foreign Key (Maintainer) references Maintainer(Username)
		on delete restrict on update cascade,
	Foreign Key (Competence) references Competence(Nome)
		on delete restrict on update cascade
);

create table ACTIVITY_COMPETENCES(
	Activity INTEGER,
	Competence VARCHAR(30),
	Primary Key (Activity, Competence),
	Foreign Key (Activity) references Activity(ID_Code)
		on delete restrict on update cascade,
	Foreign Key (Competence) references Competence(Nome)
		on delete restrict on update cascade
);

create table ACTIVITY_MATERIALS(
	Activity INTEGER,
	Material VARCHAR(30),
	Primary Key (Activity, Material),
	Foreign Key (Activity) references Activity(ID_Code)
		on delete restrict on update cascade
);