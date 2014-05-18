# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table page (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(4096),
  constraint pk_page primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence page_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists page;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists page_seq;

drop sequence if exists user_seq;

