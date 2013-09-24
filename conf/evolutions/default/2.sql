# --- !Ups

lter table COCKTAILS add column CREATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
lter table COCKTAILS add column UPDATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;


# --- !Downs

alter table "COCKTAIL" drop column "UPDATED";
alter table "COCKTAIL" drop column "CREATED";

