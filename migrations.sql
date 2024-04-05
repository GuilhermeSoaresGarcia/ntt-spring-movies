USE movies;

INSERT INTO movies (title) VALUES ('The Matrix');
INSERT INTO movies (title) VALUES ('The Silence of the Lambs');
INSERT INTO movies (title) VALUES ('Back to the Future');
INSERT INTO movies (title) VALUES ('Cidade de Deus');
INSERT INTO movies (title) VALUES ('OldBoy');
INSERT INTO movies (title) VALUES ('Inception');

INSERT INTO genres (genre_name) VALUES ('Action');
INSERT INTO genres (genre_name) VALUES ('Drama');
INSERT INTO genres (genre_name) VALUES ('Comedy');
INSERT INTO genres (genre_name) VALUES ('Science Fiction');
INSERT INTO genres (genre_name) VALUES ('Adventure');
INSERT INTO genres (genre_name) VALUES ('Horror');

INSERT INTO actors (name) VALUES ('Anthony Hopkins');
INSERT INTO actors (name) VALUES ('Jodie Foster');
INSERT INTO actors (name) VALUES ('Ted Levine');
INSERT INTO actors (name) VALUES ('Michael J. Fox');
INSERT INTO actors (name) VALUES ('Christopher Lloyd');
INSERT INTO actors (name) VALUES ('Carrie-Anne Moss');
INSERT INTO actors (name) VALUES ('Keanu Reeves');
INSERT INTO actors (name) VALUES ('Laurence Fishburne');
INSERT INTO actors (name) VALUES ('Hugo Weaving');
INSERT INTO actors (name) VALUES ('Leonardo DiCaprio');
INSERT INTO actors (name) VALUES ('Cillian Murphy');
INSERT INTO actors (name) VALUES ('Marion Cotillard');
INSERT INTO actors (name) VALUES ('Michael Caine');
INSERT INTO actors (name) VALUES ('Tom Berenger');
INSERT INTO actors (name) VALUES ('Ken Watanabe');
INSERT INTO actors (name) VALUES ('Elliot Page');
INSERT INTO actors (name) VALUES ('Tom Hardy');
INSERT INTO actors (name) VALUES ('Joseph Gordon-Levitt');

INSERT INTO studios (name, country) VALUES ('Universal', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Warner Bros.', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Paramount', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Studio Ghibli', 'Japao');
INSERT INTO studios (name, country) VALUES ('Estudios Globo', 'Brasil');

INSERT INTO directors (name) VALUES ('Cristopher Nolan');
INSERT INTO directors (name) VALUES ('Steven Spielberg');
INSERT INTO directors (name) VALUES ('Fernando Meirelles');
INSERT INTO directors (name) VALUES ('Park Chan-wook');
INSERT INTO directors (name) VALUES ('Lilly Wachowski');
INSERT INTO directors (name) VALUES ('Lana Wachowski');
INSERT INTO directors (name) VALUES ('Jonathan Demme');

INSERT INTO users (name, username, email, password) VALUES ('Guilherme', 'gui', 'guilherme.garcia@email.com', '$2a$12$eibiCrRZqH90xGJC9uBZd.fhuzt.r.36M0Ntdi8CHKoyeilZyJ/Pe');
INSERT INTO users (name, username, email, password) VALUES ('Marina', 'mar', 'marina@placeholder.com', '123abc');
INSERT INTO users (name, username, email, password) VALUES ('Valdor', 'valdor', 'valdor@placeholder.com', 'password');

INSERT INTO franchises (name) VALUES ('Fast & Furious');
INSERT INTO franchises (name) VALUES ('Lord of the Rings');
INSERT INTO franchises (name) VALUES ('Back to the Future');
INSERT INTO franchises (name) VALUES ('Saw');

INSERT INTO streamings (name, url) VALUES ('Netflix', 'www.netflix.com');
INSERT INTO streamings (name, url) VALUES ('GloboPlay', 'www.globoplay.com');
INSERT INTO streamings (name, url) VALUES ('Amazon Prime', 'www.prime.com');
