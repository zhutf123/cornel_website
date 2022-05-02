CREATE TABLE teleplay_video_browse_data
(
    id           serial PRIMARY KEY,
    video_id   bigint,
    teleplay_id   bigint,
    follow_num   integer default 0,
    all_follow_num   integer default 0,
    play_num     integer default 0,
    all_play_num     integer default 0,
    like_num     integer default 0,
    all_like_num     integer default 0,
    share_num    integer default 0,
    all_share_num    integer default 0,
    comment_num  integer default 0,
    all_comment_num  integer default 0,
    use_day varchar(256),
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)