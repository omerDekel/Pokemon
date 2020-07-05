CREATE TABLE POKEMON (ID INT PRIMARY KEY,
                    NAME VARCHAR(25),
                    TYPE VARCHAR(25)
                    --TRAINER_ID INT,
                     );
CREATE TABLE TRAINER(ID INT PRIMARY KEY ,
                      NAME VARCHAR(25),
                      LEVEL INT );
CREATE TABLE POKEMONS_TRAINER(TRAINER_ID INT ,
                              POKEMON_ID INT ,
                              TIME_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
                              CONSTRAINT ID PRIMARY KEY (POKEMON_ID, TRAINER_ID)
 );