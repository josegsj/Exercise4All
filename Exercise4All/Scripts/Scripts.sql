CREATE TABLE movies (
movie_id  SERIAL NOT NULL PRIMARY KEY,
title VARCHAR(250) NOT NULL,
qtd_copy integer
);

INSERT INTO movies (title,qtd_copy)
VALUES ('Green book',2);
INSERT INTO movies (title,qtd_copy)
VALUES ('os Vingadores',3);
INSERT INTO movies (title,qtd_copy)
VALUES ('Batman',2);
INSERT INTO movies (title,qtd_copy)
VALUES ('IT-a coisa',2);
INSERT INTO movies (title,qtd_copy)
VALUES ('Matrix',1);

CREATE TABLE user_client (
user_id SERIAL NOT NULL PRIMARY KEY,
name INTEGER NOT NULL,
login VARCHAR(250) NOT NULL,
password VARCHAR(250) NOT NULL,
token VARCHAR(250)
);

CREATE TABLE rent_movie (
rent_id SERIAL NOT NULL PRIMARY KEY,
user_id INTEGER NOT NULL,
movie_id INTEGER NOT NULL,
date date, 
CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES user_client (user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT fk_movie_id FOREIGN KEY (movie_id)
REFERENCES movies (movie_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE give_back_movie (
give_id SERIAL NOT NULL PRIMARY KEY,
user_id INTEGER NOT NULL,
movie_id INTEGER NOT NULL,
date date, 
CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES user_client (user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT fk_movie_id FOREIGN KEY (movie_id)
REFERENCES movies (movie_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION
);
