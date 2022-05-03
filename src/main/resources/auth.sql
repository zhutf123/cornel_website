CREATE TABLE channel_group
(
    id            serial PRIMARY KEY,
    name          varchar(256),
    recommend    integer default 0,
    status        integer default 1,
    operate_time  timestamptz(6) default now(),
    operator      bigint,
    operator_name varchar(256),
    ext_info      hstore,
    create_time   timestamptz(6) default now()
)