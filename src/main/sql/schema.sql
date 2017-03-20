create database seckill;

use seckill;

-- 创建秒杀库存表
create table seckill (
	`seckill_id` bigint not null auto_increment comment '商品库存id',
	`name` varchar(20) not null comment '商品名称',
	`number` int not null comment '库存数量',
	`start_time` timestamp not null comment '秒杀开启时间',
	`end_time` timestamp not null comment '秒杀结束时间',
	`create_time` timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	primary key (seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

insert into 
	seckill(name,number,start_time,end_time)
values
	('1000元秒杀iPhone7',1000,'2017-03-19 00:00:00','2017-03-22 00:00:00'),
	('100元秒杀iPhone5',1000,'2017-03-19 00:00:00','2017-03-22 00:00:00'),
	('200元秒杀iPhone7',1000,'2017-03-19 00:00:00','2017-03-22 00:00:00'),
	('300元秒杀iPhone7',1000,'2017-03-19 00:00:00','2017-03-22 00:00:00'),
	('5000元秒杀Mac',1000,'2017-03-19 00:00:00','2017-03-22 00:00:00');
	
	
-- 秒杀成功明细表
create table success_killed(
	`seckill_id` bigint not null comment '秒杀商品id',
	`user_phone` bigint not null comment '用户手机号码',
	`state` tinyint not null default -1 comment '-1 表示无效  0 成功',
	`create_time` timestamp not null comment '创建时间',
	primary key(seckill_id,user_phone),/*联合主键*/
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';


	
	
	
	
	
	
	
	
	
	
	