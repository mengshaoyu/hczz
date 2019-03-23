---- 案件表
alter table CASE_INFO modify(CASE_DESC varchar(4000));

alter table CASE_INFO modify(CRIMINAL_NAME varchar(1000));

alter table CASE_INFO modify(CASE_NAME varchar(500));

alter table CASE_INFO  add modiftime date;

---- 受案登记表
alter table sop_sadjb modify(bar_dw varchar(500));

alter table sop_sadjb modify(ysdwlxdh varchar(500));

alter table sop_sadjb modify(bar_zjhm varchar(500));

alter table sop_sadjb modify(bar_xm varchar(500));

alter table sop_sadjb modify(bar_lxfs varchar(500));

alter table sop_sadjb  add modiftime date;

---- 警情表
ALTER TABLE ALARM_INFO MODIFY ALARM_TYPE  NULL;

ALTER TABLE ALARM_INFO MODIFY ALARM_PHONE  NULL;

alter table ALARM_INFO modify(ALARM_CONTENT varchar(4000));

alter table ALARM_INFO modify(ALARM_PHONE varchar(100));

alter table ALARM_INFO modify(ALARM_PEOPLE varchar(100));

alter table ALARM_INFO add modiftime date;

---- 立案决定表
alter table sop_lajds  add modiftime date;