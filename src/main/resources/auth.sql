CREATE TABLE comment_info
(
    id           serial PRIMARY KEY,
    content   varchar(256),
    user_id   varchar(256),
    video_id   bigint,
    parent_path text,
    level   integer default 0,
    top   integer default 0,
    reply_num,   integer default 0,
    like_num,   integer default 0,
    bullet_chat       integer default 2,
    weight        integer,
    status      integer default 1,
    system_status       integer default 0,
    operator_status       integer default 0,
    operator     bigint,
    operator_name varchar(256),
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)