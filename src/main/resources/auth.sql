CREATE TABLE user_follow_video
(
    id           serial PRIMARY KEY,
    teleplay_id     bigint,
    user_id     bigint,
    status      integer default 1,
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)