INSERT INTO obiekt
VALUES
  (1, 'Dom 5-pokojowy, ul.Smocza', 220.00, 60.00, 'Kort tenisowy opis'),
  (2, 'Apartament, ul.Socza', 220.00, 62.00, 'Boisko pilkarskie opis'),
  (3, 'Mieszkanie 2-pokojowe, ul.Zlota', 130.00, 35.00, 'Boisko pilkarskie opis'),
  (4, 'Dom Jednorodzinny, ul.Malpia', 210.00, 57.00, 'Boisko pilkarskie opis');


INSERT INTO najemca
VALUES
  (1, 'Robert Maklowicz'),
  (2, 'Mariusz Pudzianowski'),
  (3, 'Adam Malysz');


INSERT INTO wynajmujacy
VALUES
  (1, 'James Wade'),
  (2, 'Jonny Clayton'),
  (3, 'Michael van Gerwen');



INSERT INTO rezerwacja
VALUES
  (1, '2022-05-12', '2022-06-14', 7260.00, 1, 1, 2),
  (2, '2022-04-02', '2022-05-02', 6600.00, 3, 3, 1),
  (3, '2022-05-22', '2022-10-10', 18330.00, 2, 1, 3),
  (4, '2022-03-12', '2022-08-06', 30870.00, 1, 3, 4),
  (5, '2022-06-15', '2022-08-24', 15400.00, 3, 2, 2),
  (6, '2022-06-12', '2022-07-14', 7040.00, 2, 2, 1),
  (7, '2022-10-12', '2023-06-03', 30420.00, 1, 1, 3);