
delete from PZ_T_SERVICE_MANAGE;

commit;


insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001', '模糊图像处理', '4', '1', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002', '车辆布控', '4', '1', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003', '人像比对', '4', '1', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2001', '人员全国轨迹查询', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2002', '车辆全国轨迹查询', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2003', '手机号码关联信息查询', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2004', '话单查询', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2005', '上控', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2006', '手机定位', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2007', '通过虚拟身份查询真实身份', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2008', 'IP地址落地', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

insert into PZ_T_SERVICE_MANAGE (UUID, SERVICE_REQUEST, FEEDBACK_TIME, SERVICE_TYPE, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2009', '手机号码机主查询', '4', '2', '1', sysdate, sysdate, '1', null, 'b');

commit;
