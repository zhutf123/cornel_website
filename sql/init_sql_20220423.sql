

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
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN acl_info.name IS '名称';
COMMENT
ON COLUMN acl_info.code IS '标记';
COMMENT
ON COLUMN acl_info.url IS '链接';
COMMENT
ON COLUMN acl_info.module IS '模块类型 1 菜单 2按钮';
COMMENT
ON COLUMN acl_info.status IS '1 有效  2 无效';


DROP TABLE IF EXISTS role_info;
CREATE TABLE role_info
(
    id           serial PRIMARY KEY,
    name         varchar(40),
    status       integer default 1,
    ext_info     hstore,
    acl_code     varchar(40),
    create_time  timestamptz(6) default now(),
    operate_time timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN role_info.name IS '角色名称';
COMMENT
ON COLUMN role_info.status IS '1 有效  2 无效';
COMMENT
ON COLUMN role_info.acl_code IS '角色对应的权限';
COMMENT
ON COLUMN role_info.role_id IS '角色id';

insert into role_info(name,acl_code) values('user','user');
insert into role_info(name,acl_code) values('sys-user','sys-user');


DROP TABLE IF EXISTS user_role_info;
CREATE TABLE user_role_info
(
    id           serial PRIMARY KEY,
    user_id      bigint,
    role_id      bigint,
    status       integer default 1,
    ext_info     hstore,
    create_time  timestamptz(6) default now(),
    operate_time timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN user_role_info.user_id IS '用户id';
COMMENT
ON COLUMN user_role_info.role IS '角色id';
COMMENT
ON COLUMN user_role_info.status IS '1 有效  2 无效';


DROP TABLE IF EXISTS user_acl_info;
CREATE TABLE user_acl_info
(
    id           serial PRIMARY KEY,
    user_id      bigint,
    acl_code     bigint,
    status       integer default 1,
    ext_info     hstore,
    create_time  timestamptz(6) default now(),
    operate_time timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN user_acl_info.user_id IS '用户id';
COMMENT
ON COLUMN user_acl_info.acl_id IS '权限id';
COMMENT
ON COLUMN user_acl_info.status IS '1 有效  2 无效';


DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info
(
    id              serial PRIMARY KEY,
    user_id         varchar(50),
    open_id         varchar(50),
    name            varchar(40),
    passwd          varchar(128),
    gender          integer,
    birthday        varchar(20),
    head_img        varchar(128),
    nick_name       varchar(64),
    id_type         integer,
    id_card         varchar(64),
    term_validity   varchar(20),
    mobile          varchar(64),
    mail          varchar(64),
    score           integer,
    status          integer default 1,
    ext_info        hstore,
    last_login_time timestamptz(6) default now(),
    create_time     timestamptz(6) default now(),
    operate_time    timestamptz(6) default now(),
    role            integer
)
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN user_info.user_id IS '用户ID,具有唯一性';
COMMENT
ON COLUMN user_info.open_id IS 'openid,存在一个用户对应多个openID';
COMMENT
ON COLUMN user_info.name IS '姓名';
COMMENT
ON COLUMN user_info.gender IS '性别 0 女 1 男';
COMMENT
ON COLUMN user_info.id_type IS '证件类型';
COMMENT
ON COLUMN user_info.head_img IS '头像';
COMMENT
ON COLUMN user_info.nick_name IS '昵称';
COMMENT
ON COLUMN user_info.id_card IS '证件号';
COMMENT
ON COLUMN user_info.term_validity IS '证件有效期';
COMMENT
ON COLUMN user_info.mobile IS '电话';
COMMENT
ON COLUMN user_info.score IS '积分';
COMMENT
ON COLUMN user_info.status IS '状态 1:有效  2无效';
COMMENT
ON COLUMN user_info.ext_info IS '扩展信息';
COMMENT
ON COLUMN user_info.last_login_time IS '最后登录时间';
COMMENT
ON COLUMN user_info.role IS '';

insert into user_info(name,passwd,role) values('test','e10adc3949ba59abbe56e057f20f883e',2);


DROP TABLE IF EXISTS user_pay_info;
CREATE TABLE user_pay_info
(
    id           serial PRIMARY KEY,
    user_id   bigint,
    product_id  bigint,
    order_id bigint,
    product_name varchar(256),
    money      numeric(10, 2),
    status          integer default 0,
    pay_time  timestamptz(6),
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;
COMMENT
ON COLUMN user_pay_info.product_name IS '产品标题';
COMMENT
ON COLUMN user_pay_info.money IS '支付金额';
COMMENT
ON COLUMN user_pay_info.status IS '0 待支付 1 已支付';

insert into user_pay_info(user_id,product_id,order_id,product_name,money,status,pay_time,create_time) values(2,1,1,'测试产品',95.00,1,now(),now());


DROP TABLE IF EXISTS teleplay;
CREATE TABLE teleplay
(
    id           serial PRIMARY KEY,
    main_image   varchar(256),
    main_source   varchar(256),
    title        text,
    depict     text,
    nums         integer,
    channel      varchar(40)[],
    vip          integer default 0,
    status       integer default 1,
    operate_time timestamptz(6) default now(),
    operator     bigint,
    operator_name varchar(256),
    recommend    integer default 0,
    top          integer default 0,
    ext_info     hstore,
    create_time  timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;

COMMENT
ON COLUMN teleplay.title IS '标题电视剧名称';
COMMENT
ON COLUMN teleplay.channel IS '频道';


DROP TABLE IF EXISTS teleplay_video;
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
    WITH (OIDS = FALSE)
;

COMMENT
ON COLUMN teleplay_video.title IS '剧集名称';


DROP TABLE IF EXISTS teleplay_video_browse_data;
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
    play_time  bigint default 0,
    all_play_time  bigint default 0,
    use_day varchar(256),
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;


DROP TABLE IF EXISTS teleplay_video;
CREATE TABLE video_user_play_info
(
    id         serial PRIMARY KEY,
    video_id   bigint,
    user_id    bigint,
    play_time  bigint,
    ext_info     hstore,
    operate_time timestamptz(6) default now(),
    create_time  timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;


DROP TABLE IF EXISTS channel;
CREATE TABLE channel
(
    id            serial PRIMARY KEY,
    name          varchar(256),
    type          integer,
    weight        integer,
    status        integer default 1,
    operate_time  timestamptz(6) default now(),
    operator      bigint,
    operator_name varchar(256),
    ext_info      hstore,
    create_time   timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;

COMMENT
ON COLUMN channel.name IS '标签名称';
create unique index idx_unique_name_type on channel(name,type);


DROP TABLE IF EXISTS channel_group;
CREATE TABLE channel_group
(
    id            serial PRIMARY KEY,
    name          varchar(256),
    channel      varchar(40)[],
    recommend    integer default 0,
    status        integer default 1,
    operate_time  timestamptz(6) default now(),
    operator      bigint,
    operator_name varchar(256),
    ext_info      hstore,
    create_time   timestamptz(6) default now()
)
    WITH (OIDS = FALSE)
;

COMMENT
ON COLUMN channel_group.name IS '聚合标签名称';


DROP TABLE IF EXISTS banner_info;
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
    WITH (OIDS = FALSE)
;

COMMENT ON COLUMN banner_info.title IS '标题';
COMMENT ON COLUMN banner_info.type IS '1剧集 2广告';


DROP TABLE IF EXISTS comment_info;
CREATE TABLE comment_info
(
    id           serial PRIMARY KEY,
    content   varchar(256),
    user_id   varchar(256),
    video_id   bigint,
    parent_path text,
    top,   integer default 0,
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
    WITH (OIDS = FALSE)
;

COMMENT ON COLUMN comment_info.parent_path IS '被回复的id 从根结点开始 如：1.10.100';
COMMENT ON COLUMN banner_info.status IS '品论状态 1通过 4 删除';
COMMENT ON COLUMN banner_info.bullet_chat IS '1作为弹幕，2不在弹幕出现';
COMMENT ON COLUMN banner_info.system_status IS '0 系统待审核 1审核通过 2 审核不通过';
COMMENT ON COLUMN banner_info.operator_status IS '0 人工待审核 1审核通过 2 审核不通过';




