drop table if exists users;
drop table if exists comments;
drop table if exists news;
create sequence if not exists user_seq;
create sequence if not exists news_seq;
create sequence if not exists comment_seq;

create table users
(
    id       bigserial not null
        primary key,
    name     varchar(255),
    nickname varchar(255),
    password varchar(255),
    photo    varchar(255),
    role     varchar(255),
    surname  varchar(255)
);

create table news
(
    id                bigserial not null
        primary key,
    category          varchar(255),
    header            varchar(255),
    news_cover        varchar(255),
    publication_date  date,
    short_description varchar(255),
    text_news         varchar(255),
    publisher_id      bigserial
        constraint fkgg42ngxra44lpmqhi9xv6o5r5
            references users
);

create table comments
(
    id              bigserial not null
        primary key,
    comment         varchar(255),
    date_of_comment date,
    news_id         bigserial
        constraint fkqx89vg0vuof2ninmn5x5eqau2
            references news,
    user_id         bigserial
        constraint fk8omq0tc18jd43bu5tjh6jvraq
            references users
);