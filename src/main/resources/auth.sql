CREATE TABLE teleplay_video
(
    id           serial PRIMARY KEY,
    teleplay_id   bigint,
    main_image   varchar(256),
    main_source   varchar(256),
    video_url   varchar(256),
    video_source   varchar(256),
    title        text,
    seq         integer,
    video_time   bigint,
    vip          integer default 0,
    status       integer default 1,
    operate_time timestamptz(6) default now(),
    operator     bigint,
    operator_name varchar(256),
    recommend    integer default 0,
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)