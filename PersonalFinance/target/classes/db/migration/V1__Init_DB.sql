create sequence hibernate_sequence start 1 increment 1;

create table category(
id int8 not null,
name_category varchar(255) not null,
priority int4 not null,
type varchar(255) not null,
user_id int8,
primary key (id));

create table transactions(
id int8 not null, amount float8,
timestamp timestamp not null,
category_id int8,
user_id int8,
primary key (id));

create table user_role(
user_id int8 not null,
roles varchar(255));

create table usr(
id int8 not null,
activation_code varchar(255),
active boolean not null,
balance float8,
email varchar(255),
password varchar(255) not null,
username varchar(255) not null,
primary key (id));

alter table if exists category
    add constraint category_user_fk
        foreign key (user_id) references usr;

alter table if exists transactions
    add constraint transaction_category_fk
        foreign key (category_id) references category;

alter table if exists transactions
    add constraint transaction_user_fk
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr;