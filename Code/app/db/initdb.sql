drop table if exists bikes;
drop table if exists stations;
drop table if exists users;
drop table if exists creditCards;
drop table if exists rentalTxs;
drop table if exists paymentTxs;

create table bikes (
    id integer, 
    type integer, 
    price integer, 
    currentBattery integer, 
    currentDockId integer, 
    currentRentalTxId integer,
    licensePlates text,
    primary key("id"),
    unique("id")
);

create table stations (
    id integer,
    name text,
    area integer,
    address text,
    dockCapacity integer,
    numAvailableBike integer,
    primary key("id"),
    unique("id")
);

create table creditCards (
    id integer,
    cardHolderName text,
    cardNumber text,
    bank text,
    expirationDate text,
    code text,
    primary key("id"),
    unique("id")
);

create table users (
    id integer,
    fullname text,
    creditCardId integer,
    primary key("id"),
    unique("id")
);

create table paymentTxs (
    id integer,
    balance integer,
    description text,
    creditCardId integer,
    primary key("id"),
    unique("id")
);

create table rentalTxs (
    id integer,
    startAt integer,
    endAt integer,
    fromDockId integer,
    toDockId integer,
    rentPolicy integer,
    bikeId integer,
    userId integer,
    paymentTxRentId integer,
    paymentTxReturnId integer,
    primary key("id"),
    unique("id")
);

insert into bikes (id, type, price, currentBattery, currentDockId, currentRentalTxId, licensePlates) 
values
(1, 0, 1000, 50, 1, null, "0123"),
(2, 1, 2000, 60, 1, null, "0124"),
(3, 2, 3000, 70, 2, null, "0125"),
(4, 2, 3000, 70, null, 2, "0126");

insert into stations (id, name, area, address, dockCapacity, numAvailableBike)
values
(1, "station 1", 100, "s1 abc", 100, 2),
(2, "station 2", 100, "s2 xyz", 100, 1),
(3, "station 1", 100, "s1 abc", 100, 2);

insert into creditCards (id, cardHolderName, cardNumber, bank, expirationDate, code)
values
(1, "NGUYEN TRUNG THU", "0123456789", "THU", "2205", "666");

insert into users (id, fullname, creditCardId)
values
(1, "Abc", 1);

insert into rentalTxs(id, startAt, endAt, fromDockId, toDockId, rentPolicy, bikeId, userId, paymentTxRentId, paymentTxReturnId)
values
(1, 1000, 2000, 1, 2, 0, 1, 1, 1, 2);