drop table if exists users;
drop table if exists comments;
drop table if exists news;

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

create table users_favorites
(
    elected_id   bigserial not null
        constraint fkqewshplngegc1hyl8qma95sxr
            references users,
    favorites_id bigserial not null
        constraint fksomuc7om8448ao3ltm66hsyb9
            references news
);