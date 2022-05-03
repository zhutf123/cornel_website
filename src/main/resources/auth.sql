CREATE TABLE user_pay_info
(
    id           serial PRIMARY KEY,
    user_id   bigint,
    product_id  bigint,
    product_name varchar(256),
    money      numeric(10, 2),
    pay_time  varchar(256),
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)