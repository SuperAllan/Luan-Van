/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     1/20/2015 2:47:38 PM                         */
/*==============================================================*/


drop table if exists PROJECT;

drop table if exists USER;

drop table if exists USERROLE;

/*==============================================================*/
/* Table: PROJECT                                               */
/*==============================================================*/
create table PROJECT
(
   PROJECTID            int not null,
   USERNAME             varchar(45) not null,
   NAME                 varchar(255) not null,
   TFW                  int,
   PROGRAMSKILL         varchar(999),
   KNOWLEDGEREQUIRED    varchar(999),
   UNDERSTANDPROCESS    varchar(999),
   STATUS               int not null,
   DESCRIPTION          varchar(255),
   primary key (PROJECTID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USERNAME             varchar(45) not null,
   PASSWORD             varchar(45) not null,
   ENABLED              numeric(1,0) not null,
   EMAIL                varchar(255) not null,
   primary key (USERNAME)
);

/*==============================================================*/
/* Table: USERROLE                                              */
/*==============================================================*/
create table USERROLE
(
   USERROLEID           int not null,
   USERNAME             varchar(45) not null,
   ROLE                 varchar(45) not null,
   primary key (USERROLEID)
);

alter table PROJECT add constraint FK_CREATE foreign key (USERNAME)
      references USER (USERNAME) on delete restrict on update restrict;

alter table USERROLE add constraint FK_USER_USERROLES foreign key (USERNAME)
      references USER (USERNAME) on delete restrict on update restrict;

