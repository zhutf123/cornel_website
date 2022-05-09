CREATE TABLE user_watch_video_log
(
    id           serial PRIMARY KEY,
    video_id     bigint,
    status      integer default 1,
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)