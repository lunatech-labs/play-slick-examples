# --- !Ups

alter table COCKTAIL add column CREATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
alter table COCKTAIL add column UPDATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;


# --- !Downs

alter table "COCKTAIL" drop column "UPDATED";
alter table "COCKTAIL" drop column "CREATED";

