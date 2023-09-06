create  database acgb;
CREATE TABLE auteur (
                        id int AUTO_INCREMENT PRIMARY KEY ,
                        name varchar (255)
);

CREATE TABLE book (
                      id int AUTO_INCREMENT PRIMARY KEY ,
                      title varchar (255),
                      isbn varchar (255),
                      quantity int,
                      auteurId int,
                      FOREIGN KEY (auteurId) REFERENCES auteur(id)

);

CREATE TABLE membre (
                        id int AUTO_INCREMENT PRIMARY KEY ,
                        firstName varchar (255),
                        secondName varchar (255),
                        memberNum varchar (255)
);


CREATE TABLE reservation (
                             id int AUTO_INCREMENT PRIMARY KEY ,
                             memberId int ,
                             bookId int,
                             status varchar (30),
                             borrowDate date ,
                             returnDate date,
                             FOREIGN KEY (memberId) REFERENCES membre(id),
                             FOREIGN KEY (bookId) REFERENCES book(id)
);

INSERT INTO `auteur`(`firstName`, `secondName`) VALUES ('ahmed','sefrioui'),('Jean' ,'Anouilh'),('victor' ,'hugo');
INSERT INTO `book`( `title`, `isbn`, `quantity`, `auteurId`) VALUES ('Le chapelet d ambre','AF54865','3','1'),('Le festival de marrakech','AF54435','5','1'),('Lumieres du Moroc','GV54435','5','1');
insert  into  `membre` values (null , 'redone' ,'ao' , 'GT543' );