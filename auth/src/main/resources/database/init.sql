-- t_account 账号表 --
create table t_account (
    id bigint primary key,
    name varchar(50) not null comment '昵称',
    real_name varchar(50) comment '名称',
    id_no varchar(20),
    phone varchar(15) not null,
    password varchar(50) not null,
    follower bigint not null default 0 comment '关注我的',
    followed bigint not null default 0 comment '我关注的',
    collect bigint not null default 0 comment '收藏',
    gender bit not null default 0 comment '性别',
    description varchar(50),
    location int,
    birthday varchar(8),
    avatar_url varchar(50),
    create_dt datetime not null,
    modify_dt datetime,
    index account_phone_unique_index(phone)
);