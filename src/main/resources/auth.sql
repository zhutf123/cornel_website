CREATE TABLE banner_info
(
    id           serial PRIMARY KEY,
    main_image   varchar(256),
    main_source   varchar(256),
    video_url   varchar(256),
    video_source   varchar(256),
    video_id   bigint,
    title        text,
    depict     text,
    type          integer default 1,
    status       integer default 1,
    operate_time timestamptz(6) default now(),
    operator     bigint,
    operator_name varchar(256),
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)