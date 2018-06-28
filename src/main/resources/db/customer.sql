-- 创建customer表
CREATE TABLE `customer` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `delFlag` varchar(1) DEFAULT '' COMMENT '逻辑删除标记',
  `opeTime` varchar(25) DEFAULT '' COMMENT '操作时间',
  `oper` varchar(32) DEFAULT '' COMMENT '操作人',
  `createTime` varchar(25) DEFAULT '' COMMENT '创建时间',
  `customerName` varchar(60) DEFAULT '' COMMENT '用户名称',
  `email` varchar(60) DEFAULT '' COMMENT '邮箱',
  `frozenState` varchar(1) DEFAULT '' COMMENT '冻结状态',
  `frozenType` varchar(1) DEFAULT '' COMMENT '冻结类型',
  `lastWrongLoginTime` varchar(25) DEFAULT '' COMMENT '最后登录错误时间',
  `loginErrorTimes` int(11) DEFAULT NULL COMMENT '登录错误次数',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机号',
  `password` varchar(32) DEFAULT '' COMMENT '登录密码',
  `salt` varchar(32) DEFAULT '' COMMENT '密码哈希',
  PRIMARY KEY (`uuid`),
  KEY `customerName_mobile_email` (`customerName`,`mobile`(11),`email`),
  KEY `mobile_index` (`mobile`(11)),
  KEY `email_index` (`email`),
  KEY `idx_mix_customername_createtime` (`customerName`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户主表';

INSERT INTO customer (uuid, delflag, mobile, customername) VALUES (REPLACE(UUID(), '-',''),'1','18829281460','信仰');
