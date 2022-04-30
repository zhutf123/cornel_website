
DROP TABLE IF EXISTS acl_info;
CREATE TABLE acl_info
(
    id           serial PRIMARY KEY,
    name         varchar(40),
    code         varchar(40),
    url          varchar(256),
    module       integer,
    status       integer default 1,
    ext_info     hstore,
    create_time  timestamptz(6) default now(),
    operate_time timestamptz(6) default now()
)