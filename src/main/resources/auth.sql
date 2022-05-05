CREATE TABLE rank_info
(
    id           serial PRIMARY KEY,
    name   varchar(256),
    weight        integer,
    teleplay_id      varchar(40)[],
    status      integer default 1,
    operator     bigint,
    operator_name varchar(256),
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)