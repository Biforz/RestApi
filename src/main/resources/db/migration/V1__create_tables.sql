create table if not exists vats.file
(
    id       int not null auto_increment primary key,
    name     varchar(50),
    filePath varchar(50)
);

create table if not exists vats.user
(
    id   int not null auto_increment primary key,
    name varchar(50)
);

create table if not exists vats.event
(
    id      int not null auto_increment primary key,
    user_id int,
    file_id int,
    foreign key (user_id) references user (id),
    foreign key (file_id) references user (id)
#     user_id int references user (id),
#     file_id int references file (id)
);