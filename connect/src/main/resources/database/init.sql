create table t_goods (
    id bigint primary key,
    name varchar(100) comment '物品名称',
    type bigint comment '物品分类',
    description varchar(300) comment '描述',
    price double(6,2),
    seller_id bigint comment '卖方ID',
    create_dt datetime,
    create_uid bigint,
    modify_dt datetime,
    modify_uid bigint,
    index goods_uid_index(seller_id)
);
