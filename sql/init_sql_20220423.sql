
DROP TABLE IF EXISTS "teleplay";
CREATE TABLE "teleplay" (
"id" serial PRIMARY KEY,
"main_image" varchar(512),
"title" text ,
"desc" text,
"nums" integer,
"vip" integer default 0 ,
"status" integer default 0 ,
"operate_time" timestamptz(6) default now(),
"operator" bigint,
"follow_num" integer default 0 ,
"play_num" integer default 0 ,
"like_num" integer default 0 ,
"share_num" integer default 0 ,
"comment_num" integer default 0 ,
"recommend" integer default 0,
"top" integer default 0,
"ext_info" hstore,
"create_time" timestamptz(6) default now()
)
WITH (OIDS=FALSE)
;

COMMENT ON COLUMN "teleplay"."title" IS '标题电视剧名称';


