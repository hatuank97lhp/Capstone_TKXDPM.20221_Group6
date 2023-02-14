CREATE TABLE `User` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255),
  `password` varchar(255),
  `fullname` varchae(255),
  `creditCard` int
);

CREATE TABLE `CreditCard` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `cardHolderName` varchar(255),
  `cardNumber` varchar(55),
  `bank` varchar(255),
  `expireDate` datetime
);

CREATE TABLE `Bike` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `currentBattery` int,
  `type` varchar(255),
  `price` varchar(255),
  `currentDock` int,
  `currentRentalTx` int,
  `licenseLates` varchar(255)
);

CREATE TABLE `DockingStation` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `address` varchar(255),
  `area` varchar(255),
  `numAvailableBike` int,
  `dockCapicity` int
);

CREATE TABLE `RentalTransaction` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user` int,
  `bike` int,
  `fromDock` int,
  `toDock` int,
  `startAt` datetime,
  `endAt` datetime,
  `rentalBikePolicy` text
);

CREATE TABLE `PaymentTransaction` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `balance` int,
  `creditCardId` int,
  `description` text
);

ALTER TABLE `User` ADD FOREIGN KEY (`creditCard`) REFERENCES `CreditCard` (`id`);

ALTER TABLE `Bike` ADD FOREIGN KEY (`currentDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `Bike` ADD FOREIGN KEY (`currentRentalTx`) REFERENCES `RentalTransaction` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`user`) REFERENCES `User` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`bike`) REFERENCES `Bike` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`fromDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`toDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `PaymentTransaction` ADD FOREIGN KEY (`creditCardId`) REFERENCES `CreditCard` (`id`);
