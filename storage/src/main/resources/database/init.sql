create table t_file_info_0(
                              id bigint primary key,
                              name varchar(100),
                              user_id bigint,
                              source_name varchar(200) comment '文件源名称',
                              file_type int comment '文件类型',
                              biz_type int comment '业务类型',
                              check_status int comment '状态',
                              location varchar(200),
                              file_suffix varchar(10),
                              create_dt datetime,
                              modify_uid bigint,
                              modify_dt datetime,
                              index file_uid_biztype_index(user_id, biz_type)
);

create table t_file_info_1(
    id bigint primary key,
    name varchar(100),
    user_id bigint,
    source_name varchar(200) comment '文件源名称',
    file_type int comment '文件类型',
    biz_type int comment '业务类型',
    check_status int comment '状态',
    location varchar(200),
    file_suffix varchar(10),
    create_dt datetime,
    modify_uid bigint,
    modify_dt datetime,
    index file_uid_biztype_index(user_id, biz_type)
);

create table t_file_type_0(
    id int primary key,
    description varchar(100),
    user_id bigint,
    create_dt datetime,
    modify_uid bigint,
    modify_dt datetime
);