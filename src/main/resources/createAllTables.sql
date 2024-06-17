create table if not exists vats.file (
    id int not null auto_increment primary key,
    name varchar(50),
    filePath varchar(50)
);

create table if not exists vats.user (
    id int not null auto_increment primary key,
    name varchar(50)
);

create table if not exists vats.event (
    id int not null auto_increment primary key,
    user_id int references user(id),
    file_id int references file(id)
);



insert into vats.file(name, filePath) VALUES ('test1', 'opa');
insert into vats.file(name, filePath) VALUES ('test2', 'oppo');
insert into vats.file(name, filePath) VALUES ('test3', 'okko');
insert into vats.file(name, filePath) VALUES ('test4', 'oreo');

insert into vats.user(name) VALUES ('Vadim');
insert into vats.user(name) VALUES ('Dima');
insert into vats.user(name) VALUES ('Masha');
insert into vats.user(name) VALUES ('Sasha');

insert into vats.event(user_id, file_id) VALUES (1, 1);
insert into vats.event(user_id, file_id) VALUES (2, 2);
insert into vats.event(user_id, file_id) VALUES (3, 3);
insert into vats.event(user_id, file_id) VALUES (4, 4);


