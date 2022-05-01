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


DROP TABLE IF EXISTS teleplay;
CREATE TABLE teleplay
(
    id           serial PRIMARY KEY,
    main_image   varchar(256),
    main_source   varchar(256),
    title        text,
    depict     text,
    nums         integer,
    channel      integer[],
    vip          integer default 0,
    status       integer default 1,
    operate_time timestamptz(6) default now(),
    operator     bigint,
    operator_name varchar(256),
    follow_num   integer default 0,
    play_num     integer default 0,
    like_num     integer default 0,
    share_num    integer default 0,
    comment_num  integer default 0,
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

