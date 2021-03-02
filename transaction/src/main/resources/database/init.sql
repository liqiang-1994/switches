-- t_order 订单表--
create table t_order (
    id bigint primary key,
    buyer_id bigint comment '买方ID',
    seller_id bigint comment '卖方ID',
    txn_amt double(6,2) comment '金额',
    txn_num int comment '商品笔数',
    deliver_status int comment '订单状态',
    payment_status int comment '支付状态',
    create_dt datetime,
    modify_dt datetime

)

--t_order_detail 订单明细表 --
create table t_order_detail (
    id bitgint primary key,
    order_id bigint,
    goods_id bigint,
    goods_name varchar(50) comment '商品名称',
    goods_description varchar(200) comment '商品介绍',
    price double(6,2) comment '金额',
    create_dt datetime,
    modify_dt datetime
)