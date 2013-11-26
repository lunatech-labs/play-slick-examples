# --- !Ups

alter table COCKTAIL alter column "ID" bigint not null auto_increment;
alter table COCKTAIL add column "PRICE_CURRENCY" varchar(3) not null default 'EUR';
alter table COCKTAIL add column "PRICE_AMOUNT" decimal(13,3) not null default 0.00;
update COCKTAIL set PRICE_CURRENCY = 'EUR';
update COCKTAIL set PRICE_AMOUNT = 6.50 where NAME = 'Caipirinha';
update COCKTAIL set PRICE_AMOUNT = 7.00 where NAME = 'Margarita';
update COCKTAIL set PRICE_AMOUNT = 8.50 where NAME = 'Piña colada';

# --- !Downs

alter table COCKTAIL drop column "PRICE_CURRENCY";
alter table COCKTAIL drop column "PRICE_AMOUNT";
