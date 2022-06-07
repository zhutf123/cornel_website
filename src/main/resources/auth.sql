

CREATE TABLE user_sign_in_info
(
    id           serial PRIMARY KEY,
    user_id   bigint,
    gold_coin      integer,
    status          integer default 0,
    sign_in_time  timestamptz(6),
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)
