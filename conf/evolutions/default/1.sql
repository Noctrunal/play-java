# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table roles (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_roles primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  login                         varchar(255),
  password                      varchar(255),
  constraint pk_users primary key (id)
);

create table user_roles (
  user_id                       bigint not null,
  role_id                       bigint not null,
  constraint pk_user_roles primary key (user_id,role_id)
);

alter table user_roles add constraint fk_user_roles_users foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_user_roles_users on user_roles (user_id);

alter table user_roles add constraint fk_user_roles_roles foreign key (role_id) references roles (id) on delete restrict on update restrict;
create index ix_user_roles_roles on user_roles (role_id);


# --- !Downs

alter table user_roles drop constraint if exists fk_user_roles_users;
drop index if exists ix_user_roles_users;

alter table user_roles drop constraint if exists fk_user_roles_roles;
drop index if exists ix_user_roles_roles;

drop table if exists roles;

drop table if exists users;

drop table if exists user_roles;

