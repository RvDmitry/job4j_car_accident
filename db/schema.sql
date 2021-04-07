create table types (
    id serial primary key,
    name text unique not null
);

CREATE TABLE accidents (
  id serial primary key,
  name text,
  text text,
  address text,
  type_id int references types(id) not null
);

create table rules (
    id serial primary key,
    name text unique not null
);

create table accidents_rules (
    id serial primary key,
    accident_id int references accidents(id) not null,
    rule_id int references rules(id) not null
);