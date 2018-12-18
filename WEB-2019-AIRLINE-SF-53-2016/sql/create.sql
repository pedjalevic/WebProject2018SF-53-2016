DROP SCHEMA IF EXISTS airline;
CREATE SCHEMA airline DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE airline;

create table airports(
id BIGINT AUTO_INCREMENT,
name varchar (30) not null unique,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
primary key (id)
);

insert into airports(name) values ('Nikola Tesla Beograd');
insert into airports(name) values ('Atlanta USA');
insert into airports(name) values ('Heatrow London');
insert into airports(name) values ('Haneda Tokio');
insert into airports(name) values ('Charles de Gaulle Pariz');

create table flights(
id bigint auto_increment,
flightNumber varchar (10) not null unique ,
departureDate DATETIME not null,
arrivalDate DATETIME not null,
departureAirport bigint not null,
arrivalAirport bigint not null,
seatNumber bigint not null,
ticketPrice double not null,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
primary key (id),
FOREIGN KEY (departureAirport) REFERENCES airports(id) ON DELETE restrict,
FOREIGN KEY (arrivalAirport) REFERENCES airports(id) ON DELETE restrict
);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('1NTSAU','2018-12-14 16:54:00','2018-12-14 17:54:00',1,2,180,15.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('1NTSHL','2018-12-14 16:21:00','2018-12-14 17:24:00',1,3,250,11.70);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('1HTAU','2018-12-14 16:26:00','2018-12-14 17:32:00',4,2,500,10.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('1CDGPHT','2018-12-14 16:29:00','2018-12-14 17:58:00',5,4,300,22.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('2CDGPHT','2018-12-15 16:29:00','2018-12-15 17:58:00',5,4,300,22.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('2NTSAU','2019-12-14 16:54:00','2019-12-14 17:54:00',1,2,180,15.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('2NTSHL','2019-12-14 16:21:00','2019-12-14 17:24:00',1,3,250,11.70);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('2HTAU','2019-12-14 16:26:00','2019-12-14 17:32:00',4,2,500,10.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('1AUHL','2019-12-14 16:26:00','2019-12-14 17:32:00',4,2,500,10.40);
insert into flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,ticketPrice) values
('2AUHL','2019-12-15 16:26:00','2019-12-15 17:32:00',4,2,500,10.40);



create table users(
id bigint auto_increment,
userName VARCHAR(10) NOT NULL unique,
userPassword VARCHAR(15) NOT NULL,
registrationDate DATE NOT NULL,
role ENUM ('USER','ADMIN') NOT NULL DEFAULT 'USER',
blocked BOOLEAN NOT NULL DEFAULT FALSE,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
PRIMARY KEY (id)
);

insert into users(userName,userPassword,registrationDate,role) values ('pedja','pedja','2018-11-14','ADMIN');
insert into users(userName,userPassword,registrationDate,role) values ('123','123','2018-11-15','USER');
insert into users(userName,userPassword,registrationDate,role) values ('1234','1234','2018-11-16','USER');

create table tickets(
id bigint auto_increment,
departureFlight bigint not null,
returnFlight bigint null,
departureSeat bigint not null,
returnSeat bigint null,
reservationDate datetime null,
sellDate datetime null,
ticketUser bigint not null,
passengerName varchar(15) not null,
passengerLastName varchar (15) not null,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
primary key (id),
foreign key (departureFlight) references flights(id) on delete restrict,
foreign key (returnFlight) references flights(id) on delete restrict,
foreign key (ticketUser) references users(id) on delete restrict
);

insert into tickets(departureFlight,returnFlight,departureSeat,returnSeat,reservationDate,sellDate,ticketUser,passengerName,passengerLastName) values
('1','2','1','1','2018-12-10 16:29:00','2018-12-10 18:29:00','2','Milutin','Milutinovic');
insert into tickets(departureFlight,returnFlight,departureSeat,returnSeat,reservationDate,sellDate,ticketUser,passengerName,passengerLastName) values
('1','2','2','2','2018-12-10 17:24:00','2018-12-10 19:23:00','3','Jovan','Jovanovic');
insert into tickets(departureFlight,returnFlight,departureSeat,returnSeat,reservationDate,sellDate,ticketUser,passengerName,passengerLastName) values
('3','4','1','1','2018-12-10 12:29:00','2018-12-10 18:26:00','2','Mile','Milic');
insert into tickets(departureFlight,returnFlight,departureSeat,returnSeat,reservationDate,sellDate,ticketUser,passengerName,passengerLastName) values
('5','3','15','16','2018-12-10 14:29:00','2018-12-10 18:22:00','3','Pera','Peric');