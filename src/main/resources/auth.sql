CREATE TABLE channel
(
    id            serial PRIMARY KEY,
    name          varchar(256),
    type          text,
    weight        integer,
    status        integer default 0,
    operate_time  timestamptz(6) default now(),
    operator      bigint,
    operator_name varchar(256),
    ext_info      hstore,
    create_time   timestamptz(6) default now()
)