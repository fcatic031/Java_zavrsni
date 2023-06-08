#c:\xampp\mysql\bin\mysql -uroot --default_character_set=utf8 <C:\Users\xyz\Documents\GitHub\ERA_sql\kucni_budget.sql

drop database if exists kucni_budget;
create database kucni_budget;
use kucni_budget;

create table obitelj(
    id int not null primary key auto_increment,
    prezime_obitelji varchar(50)
);

create table korisnik(
    id int not null primary key auto_increment,
    ime varchar(50) not null,
    prezime varchar(50) not null,
    email varchar(50) not null,
    spol boolean,
    obitelj int
);

create table kategorija(
    id int not null primary key auto_increment,
    naziv varchar(50)
);

create table dnevna_potrosnja(
    id int not null primary key auto_increment,
    korisnik int not null,
    kategorija int not null,
    datum datetime not null,
    potrosnja decimal(18,2) not null
);

alter table korisnik add foreign key (obitelj) references obitelj(id);
alter table dnevna_potrosnja add foreign key (korisnik) references korisnik(id);
alter table dnevna_potrosnja add foreign key (kategorija) references kategorija(id);

#INSERT

insert into obitelj(prezime_obitelji) values 
('Arpadovići'),('Trpimirović'),('Anžuvinski');

insert into korisnik(ime,prezime,email,spol,obitelj) values
('Koloman','Arpadović', 'koloman@vlada.hu',1,1),
('Bela Druga', 'Arpadović', 'bela123@shemail.com',0,1), 
('Stjepan','Peti','stipek@vlada.hu',1,1),
('Tomislav','Trpimirović','tomo925@kralj.hr',1,2), 
('Petar Krešimir IV', 'Trpimirović', 'pero_kreso@kralj.hr',1,2), 
('Dmitar Zvonimir', 'Trpimirović', 'zvone@kralj.hr',1,2),
('Ludovik','Anžuvinski','ludi@kralj.hu',1,3),
('Karlo','Anžuvinski','karlo@kralj.hu',1,3),
('Marija','Anžuvinski','marija@shemail.com',0,3);

insert into kategorija(naziv) values
('Vojska'),('Svirači'),('Putni troškovi'),('Ostalo');

insert into dnevna_potrosnja(korisnik,kategorija,datum,potrosnja) values
(4,1,'928-03-06',3000000),(2,3,'1134-04-12',12000),(8,2,'1342-06-07',20);