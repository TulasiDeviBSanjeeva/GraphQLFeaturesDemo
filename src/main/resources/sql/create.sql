drop table if exists order ;
drop table if exists product;
drop table if exists customer;

create table customer (
                         id    bigint auto_increment primary key,
                         email varchar(255) null,
                         name  varchar(255) null
);

create table product(
                        id    bigint auto_increment primary key,
                        name  varchar(255) null,
                        price double       not null
);

create table order
(
    id         bigint auto_increment primary key,
    date       datetime     null,
    quantity int          null,
    status     varchar(255) null,
    customer_id bigint       null,
    product_id bigint       null,
    constraint FK_ORDER_PRODUCT foreign key (product_id) references product (id),
    constraint FK_ORDER_CUSTOMER foreign key (customer_id) references customer (id)
);