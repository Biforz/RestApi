create table if not exists vats.file
(
    id       int auto_increment primary key,
    name     varchar(50),
    filePath varchar(50)
);

create table if not exists vats.user
(
    id   int auto_increment primary key,
    name varchar(50)
);

create table if not exists vats.event
(
    id      int auto_increment primary key,
    user_id int,
    file_id int,
    foreign key (user_id) references user (id),
    foreign key (file_id) references file (id)
);