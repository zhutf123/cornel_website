CREATE TABLE rank_info_ext
(
    id           serial PRIMARY KEY,
    weight        integer,
    teleplay_id     bigint,
    rank_info     bigint,
    status      integer default 1,
    operator     bigint,
    operator_name varchar(256),
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)