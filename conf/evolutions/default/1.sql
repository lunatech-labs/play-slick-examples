# --- !Ups

create table "COCKTAIL" ("ID" BIGINT NOT NULL,"NAME" VARCHAR NOT NULL);

insert into COCKTAIL values (1, 'Margarita');
insert into COCKTAIL values (2, 'Caipirinha');
insert into COCKTAIL values (3, 'Piña colada');

# --- !Downs

drop table "COCKTAIL";

