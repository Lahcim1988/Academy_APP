
create database academy_APP character set utf8mb4 collate utf8mb4_unicode_ci;

create table users (
    id int(20) not null auto_increment,
    username varchar(255) not null unique ,
    email varchar(255) not null unique ,
    password varchar(60) not null,
    group_id int(11) default null,
    primary key (id),
    foreign key (group_id) references user_group(group_id)
);

create table user_group (
    group_id int(11) not null auto_increment,
    name varchar(255) not null unique,
    primary key (group_id)
);

create table exercise (
    id int(11) not null auto_increment,
    title varchar(255) not null unique,
    description text,
    primary key (id)
);

create table solution (
    id int(11) not null auto_increment,
    created datetime not null,
    updated datetime,
    description text,
    exercise_id int(11),
    user_id int(20),
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (exercise_id) references exercise(id)
);