

INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (37,'Vulpix','Fire');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (6,'Charizard','Fire');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (126,'Magmar','Fire');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (154,'Cyndaquil','Fire');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (653,'Fennekin','Fire');


INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (134,'Vaporeon','Water');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (55,'Golduck','Water');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (99,'Kingler','Water');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (129,'Magikarp','Water');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (131,'Lapras','Water');


INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (1,'Bulbasaur','Grass');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (071,'Victreebel','Grass');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (152,'Charizard','Fire');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (192,'Sunflora','Grass');
INSERT INTO POKEMON (ID,NAME,TYPE) VALUES (319,'Sharpedo','Grass');

INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (0,'Ryan',2);
INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (1,'ASH',0);
INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (2,'MISTY',1);
INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (3,'BROOKS',1);
INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (4,'OMER',0);
INSERT INTO TRAINER (ID,NAME,LEVEL) VALUES (5,'SHLOMO',0);

INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (4,6);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (4,1);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (4,653);

INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (0,126);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (0,55);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (0,319);

INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (3,131);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (3,134);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (3,99);

INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (5,154);
INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES (5,152);
