--业务类型 code
delete from sy_t_code where type_id = 1002;
insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1002,'业务类型','业务类型','1','0','1',sysdate,'1',sysdate,'b');
delete from sy_t_code where type_id = 1007;
insert into SY_T_CODE (TYPE_ID, TYPE_NAME, TYPE_DESC, EDIT_FLAG, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1007', '查询要求', '查询要求', '1', '0', '1', to_date('12-09-2017 09:38:38', 'dd-mm-yyyy hh24:mi:ss'), to_date('12-09-2017 09:38:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into SY_T_CODE (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2005, '案别', '案别', '1', '0', 'weizd', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'weizd', null, 'b');

--业务类型 codevalue
delete from sy_t_codevalue where type_id = 1002;
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'图像侦查','1',1002,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'合成研判','2',1002,'1',sysdate,'1',sysdate,'b');

delete from sy_t_codevalue where type_id = 1007;
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100701', '模糊图像处理', '1001', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100702', '车辆布控', '1002', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100703', '人像比对', '1003', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100704', '人员全国轨迹查询', '2001', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100705', '车辆全国卡口查询', '2002', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100706', '手机号码关联信息查询', '2003', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100707', '话单查询', '2004', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100708', '上控', '2005', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100709', '手机定位', '2006', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100710', '通过虚拟身份查询真实身份', '2007', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100711', 'IP地址落地', '2008', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into sy_t_codevalue (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100712', '手机号码机主查询', '2009', '1007', '1', to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2017 14:33:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

--任务等级 code
delete from sy_t_code where type_id = 1003;
insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1003,'任务等级','任务等级','1','0','1',sysdate,'1',sysdate,'b');
--任务等级 codevalue
delete from sy_t_codevalue where type_id = 1003;
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'一级','1',1003,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'二级','2',1003,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'三级','3',1003,'1',sysdate,'1',sysdate,'b');

--任务状态 code
delete from sy_t_code where type_id = 1004;
insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1004,'任务状态','任务状态','1','0','1',sysdate,'1',sysdate,'b');
--任务状态 codevalue
delete from sy_t_codevalue where type_id = 1004;

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待提交','HCZZ_1001',1004,'1',sysdate,'1',sysdate,'等待提交合成申请');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待本单位审批','HCZZ_1002',1004,'1',sysdate,'1',sysdate,'等待本单位领导审批');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待签收','HCZZ_1003',1004,'1',sysdate,'1',sysdate,'等待合成作战中心签收');

insert into sy_t_codevalue 
(pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待值班长审批','HCZZ_1004',1004,'1',sysdate,'1',sysdate,'等待合成作战中心审批');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待分发','HCZZ_1005',1004,'1',sysdate,'1',sysdate,'等待任务分发');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待反馈','HCZZ_1006',1004,'1',sysdate,'1',sysdate,'等待线索反馈');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'确认反馈','HCZZ_1007',1004,'1',sysdate,'1',sysdate,'等待确认生成反馈表');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'待核实','HCZZ_1008',1004,'1',sysdate,'1',sysdate,'等待申请民警核实评价');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'完结','end',1004,'1',sysdate,'1',sysdate,'任务完成');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'退侦补充','HCZZ_1010',1004,'1',sysdate,'1',sysdate,'退侦补充待重新提交');

commit;


--案件状态 code
delete from sy_t_code where type_id = 1005;
insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1005,'案件状态','案件状态','1','0','1',sysdate,'1',sysdate,'b');
--案件状态 codevalue
delete from sy_t_codevalue where type_id = 1005;
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已受理','1',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已立案','2',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已移送','3',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已撤销','4',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已合并','5',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'已结案','6',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'不予立案','7',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'补充侦查','8',1005,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'移送审查起诉','9',1005,'1',sysdate,'1',sysdate,'b');

--警种类型 code
delete from sy_t_code where type_id = 1006;
insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1006,'警种类型','警种类型','1','0','1',sysdate,'1',sysdate,'b');
--业务类型 codevalue
delete from sy_t_codevalue where type_id = 1006;
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (1024,'图侦','1',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (1025,'情报','2',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (1026,'技侦','3',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (1027,'网侦','4',1006,'1',sysdate,'1',sysdate,'b');

commit;

--警情类别 code
delete from sy_t_code where type_id = 1008;
commit;

insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1008,'警情类别','警情类别','1','0','1',sysdate,'1',sysdate,'b');
--业务类型 codevalue
delete from sy_t_codevalue where type_id = 1008;
commit;

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'溺水求助','1',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'打架斗殴','2',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'交通事故','3',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'打杂哄抢','4',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'盗窃','5',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'妨碍公务','6',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'群众械斗','7',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'交通障碍','8',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'扰乱秩序','9',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'其他治安事件','10',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'其他群众事件','11',1008,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'群众求助','12',1008,'1',sysdate,'1',sysdate,'b');

commit;

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1724', '污染环境案', '060601', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1725', '强迫劳动案', '040116', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1726', '食品监管渎职案', '030133', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1727', '拒不支付劳动报酬案', '040126', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1728', '虚开发票案', '030614', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1729', '组织出卖人体器官案', '060580', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1730', '对外国公职人员、国际公共组织官员行贿案', '030343', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1731', '生产、销售不符合安全标准的食品案', '030131', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1732', '持有伪造的发票案', '030671', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1733', '危险驾驶案', '020928', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1734', '未携带有效证件出海', '910039', '2005', 'init', to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:25', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1735', '组织他人非法进入他国海域', '910020', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1736', '非法进入他国海域', '910019', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1737', '非法打捞沉船沉物', '910023', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1738', '非法打捞海底文物', '910022', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1739', '未按规定办理船舶变更、注销手续', '910011', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1740', '非法使用危害公共安全方式作业', '910018', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1741', '非法扣押他人船舶、财物', '910017', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1742', '未按规定办理边防进出港查验手续', '910002', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1743', '未按规定办理出海人员证件变更手续', '910001', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1744', '违反《山东省沿海船舶边防治安管理条例》行为', '910000', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1745', '未按规定编刷船名、船号出海', '910004', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1746', '非法扣押他人', '910016', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1747', '擅自搭靠香港、澳门特别行政区、台湾地区船舶', '910015', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1748', '出海人员未申领有效证件擅自出海', '910003', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1749', '建造、改造、拆解出海船舶未按规定报备', '910009', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1750', '紧急搭靠香港、澳门特别行政区、台湾地区船舶未及时报告', '910008', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1751', '紧急搭靠外国籍船舶未及时报告', '910007', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1752', '紧急进入国家禁止或者限制进入的海域、岛屿、临时性警戒区域未及时报告', '910006', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1753', '出海船舶未申领有效证件擅自出海', '910010', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1754', '擅自搭靠外国籍船舶', '910014', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1755', '拒绝驶离临时性警戒区域', '910013', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1756', '盗采海砂', '910021', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1757', '擅自拆换、遮盖、涂改、伪造船名、船号出海', '910012', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1758', '船名、船号模糊不清出海', '910005', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1759', '盗窃车内物品', '050243', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1760', '盗窃电动车', '050241', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1761', '盗窃电缆', '050242', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1762', '违规批发零售烟花爆竹', '3F0101', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1763', '违规制作烟花爆竹', '3F0102', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1764', '邮寄或托运烟花爆竹', '3F0104', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1765', '携带烟花爆竹搭乘交通工具', '3F0105', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1766', '携犬进入养犬禁入区域', '3F0201', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1767', '单位违规养犬', '3F0202', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1768', '在禁止养犬区域养犬', '3F0203', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1769', '养犬不佩戴免疫标志和犬链', '3F0204', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1770', '违规携犬进入公共场所', '3F0205', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1771', '养犬措施不当干扰他人正常生活', '3F0206', '2005', 'init', to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:26', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1772', '擅自停用、拆除消防设施、器材', '3A3949', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1773', '占用、堵塞、封闭疏散通道、安全出口', '3A3950', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1774', '不履行组织、引导在场人员疏散义务', '3A3951', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1775', '故意破坏、伪造火灾现场', '3A3952', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1776', '阻碍执行职务', '3A3953', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1777', '违反《中华人民共和国集会游行示威法》', '3A4000', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1778', '破坏集会、游行、示威', '3A4001', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1779', '非法集会、游行、示威', '3A4002', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1780', '违反《中华人民共和国人民币管理条例》', '3A4100', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1781', '故意毁损人民币', '3A4101', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1782', '违反《台湾渔船停泊点边防治安管理办法》', '3C9300', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1783', '骗领对台劳务人员登轮作业证', '3C9301', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1784', '涂改、转让对台劳务人员登轮作业证', '3C9302', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1785', '未在指定停泊点登、离台湾渔船', '3C9303', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1786', '大陆劳务人员携带违禁物品、国家机密资料', '3C9304', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1787', '擅自启用电台', '3C9305', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1788', '台湾渔船播放非法广播', '3C9306', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1789', '台湾渔船悬挂、显示非法标志', '3C9307', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1790', '台湾渔船从事有损两岸关系活动', '3C9308', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1791', '擅自引带大陆居民登船', '3C9309', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1792', '台湾居民擅自上岸', '3C9310', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

insert into SY_T_CODEVALUE (PK, VALUE_DESC, CODE_VALUE, TYPE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1793', '涂改、转让台湾居民证件', '3C9311', '2005', 'init', to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-09-2017 16:35:27', 'dd-mm-yyyy hh24:mi:ss'), 'init', null, 'B');

--20171010 xuqiu 添加配侦状态 “全部”
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'全部','',1004,'1',sysdate,'1',sysdate,'b');
commit;

delete from sy_t_code where type_id='1010';
insert into sy_t_code (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('1010', '常用语', '常用语', '1', '0', '1', sysdate, sysdate, '1', '', 'b');
commit;
delete from sy_t_codevalue where type_id='1010';
insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('101001', '同意', '1', '1010', '0', sysdate, sysdate, '1', '', 'b');
insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('101002', '退帧补充', '2', '1010', '0', sysdate, sysdate, '1', '', 'b');
commit;

-----工作流程状态
delete from sy_t_code where type_id='1011';
insert into sy_t_code (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('1011', '工作流程状态', '工作流程状态', '1', '0', '1', sysdate, sysdate, '1', '', 'b');
commit;

delete from sy_t_codevalue where type_id='1011';

commit;

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '申请提交', 'HCZZ_1001', '1011', '1', sysdate, sysdate, '1', '', '合成申请已提交');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '分管领导审批', 'HCZZ_1002', '1011', '1', sysdate, sysdate, '1', '', '等待领导审批');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '已签收', 'HCZZ_1003', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '合成中心审批', 'HCZZ_1004', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '任务分发', 'HCZZ_1005', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '线索反馈', 'HCZZ_1006', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '确认反馈', 'HCZZ_1007', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '等待评价核实', 'HCZZ_1008', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '任务完成', 'end', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '退侦补充', 'HCZZ_1010', '1011', '1', sysdate, sysdate, '1', '', 'b');

commit;


delete from sy_t_code where type_id='1011';
insert into sy_t_code (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('1011', '工作流程状态', '工作流程状态', '1', '0', '1', sysdate, sysdate, '1', '', 'b');
commit;

delete from sy_t_codevalue where type_id='1011';

commit;

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '申请提交', 'HCZZ_1001', '1011', '1', sysdate, sysdate, '1', '', '合成申请已提交');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '分管领导审批', 'HCZZ_1002', '1011', '1', sysdate, sysdate, '1', '', '等待领导审批');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '已签收', 'HCZZ_1003', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '合成中心审批', 'HCZZ_1004', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '任务分发', 'HCZZ_1005', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '线索反馈', 'HCZZ_1006', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '确认反馈', 'HCZZ_1007', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '评价核实', 'HCZZ_1008', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '任务完成', 'end', '1011', '1', sysdate, sysdate, '1', '', 'b');

insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_CODE_VALUE.nextval, '退侦补充', 'HCZZ_1010', '1011', '1', sysdate, sysdate, '1', '', 'b');

commit;