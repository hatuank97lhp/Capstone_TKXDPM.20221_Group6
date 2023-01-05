CREATE TABLE `User` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255),
  `password` varchar(255),
  `fullname` varchae(255),
  `phone` varchar(55),
  `dob` datetime,
  `province` varchar(255),
  `address` varchar(255),
  `email` varchar(255),
  `creditInfo` int
);

CREATE TABLE `CreditInfo` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `brand` varchar(255),
  `number` varchar(55),
  `cvv` varchar(55),
  `name` varchar(255),
  `expiry` datetime
);

CREATE TABLE `Bike` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `batteryCapicity` int,
  `batteryCurrent` int,
  `type` varchar(55),
  `currentDock` int
);

CREATE TABLE `DockingStation` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `address` varchar(255),
  `area` varchar(255),
  `numAvailableBike` int,
  `numEmptyDock` int
);

CREATE TABLE `RentalTransaction` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user` int,
  `bike` int,
  `fromDock` int,
  `toDock` int,
  `startAt` datetime,
  `endAt` datetime,
  `status` varchar(255),
  `type` varchar(255),
  `paymentTx` int
);

CREATE TABLE `PaymentTransaction` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `balance` int,
  `user` int,
  `status` varchar(255)
);

ALTER TABLE `User` ADD FOREIGN KEY (`creditInfo`) REFERENCES `CreditInfo` (`id`);

ALTER TABLE `Bike` ADD FOREIGN KEY (`currentDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`user`) REFERENCES `User` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`bike`) REFERENCES `Bike` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`fromDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`toDock`) REFERENCES `DockingStation` (`id`);

ALTER TABLE `RentalTransaction` ADD FOREIGN KEY (`paymentTx`) REFERENCES `PaymentTransaction` (`id`);

ALTER TABLE `PaymentTransaction` ADD FOREIGN KEY (`user`) REFERENCES `User` (`id`);
