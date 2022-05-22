CREATE TABLE if not exists obiekt (
  id int IDENTITY not null primary key ,
  nazwa varchar(50) not null,
  cena_jednostkowa decimal(6,2)  not null,
  powierzchnia_m2 decimal(10,2) not null,
  opis varchar(100) not null
);

CREATE TABLE if not exists najemca (
  id int IDENTITY not null primary key ,
  nazwa varchar(50) not null

);

CREATE TABLE if not exists wynajmujacy (
  id int IDENTITY not null primary key ,
  nazwa varchar(50) not null

);

CREATE TABLE if not exists rezerwacja (
  id int IDENTITY not null primary key ,
  start_date date not null,
  end_date date not null,
  koszt decimal(14,2) not null,
  wynajmujacy_id int not null,
  najemca_id int not null,
  obiekt_id int not null,

  constraint FK_REZERWACJA_WYNAJM foreign key (wynajmujacy_id) references wynajmujacy(id),
  constraint FK_REZERWACJA_NAJEM foreign key (najemca_id) references najemca(id),
  constraint FK_REZERWACJA_OBIEKT foreign key (obiekt_id) references obiekt(id)
);


