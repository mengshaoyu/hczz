prompt PL/SQL Developer import file
prompt Created on 2017��9��5�� by mengsy
set feedback off
set define off
prompt Disabling triggers for AU_T_DEPT...
alter table AU_T_DEPT disable all triggers;
prompt Disabling triggers for AU_T_FUNCTION...
alter table AU_T_FUNCTION disable all triggers;
prompt Disabling triggers for AU_T_MENU...
alter table AU_T_MENU disable all triggers;
prompt Disabling triggers for AU_T_PERMISSION...
alter table AU_T_PERMISSION disable all triggers;
prompt Disabling triggers for AU_T_PERMISSION_FUNCTION...
alter table AU_T_PERMISSION_FUNCTION disable all triggers;
prompt Disabling triggers for AU_T_ROLE...
alter table AU_T_ROLE disable all triggers;
prompt Disabling triggers for AU_T_ROLE_DATAAUTH...
alter table AU_T_ROLE_DATAAUTH disable all triggers;
prompt Disabling triggers for AU_T_ROLE_PERMISSION...
alter table AU_T_ROLE_PERMISSION disable all triggers;
prompt Disabling triggers for AU_T_USER...
alter table AU_T_USER disable all triggers;
prompt Disabling triggers for AU_T_USER_ROLE...
alter table AU_T_USER_ROLE disable all triggers;
prompt Disabling triggers for FDI_T_ATTACH...
alter table FDI_T_ATTACH disable all triggers;
prompt Disabling triggers for SY_T_AUDITLOG...
alter table SY_T_AUDITLOG disable all triggers;
prompt Disabling triggers for SY_T_CODE...
alter table SY_T_CODE disable all triggers;
prompt Disabling triggers for SY_T_CODEVALUE...
alter table SY_T_CODEVALUE disable all triggers;
prompt Disabling triggers for SY_T_PARAM...
alter table SY_T_PARAM disable all triggers;
prompt Deleting SY_T_PARAM...
delete from SY_T_PARAM;
commit;
prompt Deleting SY_T_CODEVALUE...
delete from SY_T_CODEVALUE;
commit;
prompt Deleting SY_T_CODE...
delete from SY_T_CODE;
commit;
prompt Deleting SY_T_AUDITLOG...
delete from SY_T_AUDITLOG;
commit;
prompt Deleting FDI_T_ATTACH...
delete from FDI_T_ATTACH;
commit;
prompt Deleting AU_T_USER_ROLE...
delete from AU_T_USER_ROLE;
commit;
prompt Deleting AU_T_USER...
delete from AU_T_USER;
commit;
prompt Deleting AU_T_ROLE_PERMISSION...
delete from AU_T_ROLE_PERMISSION;
commit;
prompt Deleting AU_T_ROLE_DATAAUTH...
delete from AU_T_ROLE_DATAAUTH;
commit;
prompt Deleting AU_T_ROLE...
delete from AU_T_ROLE;
commit;
prompt Deleting AU_T_PERMISSION_FUNCTION...
delete from AU_T_PERMISSION_FUNCTION;
commit;
prompt Deleting AU_T_PERMISSION...
delete from AU_T_PERMISSION;
commit;
prompt Deleting AU_T_MENU...
delete from AU_T_MENU;
commit;
prompt Deleting AU_T_FUNCTION...
delete from AU_T_FUNCTION;
commit;
prompt Deleting AU_T_DEPT...
delete from AU_T_DEPT;
commit;
prompt Loading AU_T_DEPT...
insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, '�ൺ�й�����', '370200000000', 0, null, null, null, 0, '1', to_date('04-09-2017', 'dd-mm-yyyy'), to_date('04-09-2017', 'dd-mm-yyyy'), '1', null, 'B');
commit;
prompt 2 records loaded
prompt Loading AU_T_FUNCTION...
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010101, '�б�', 'usr/getPList', '�б�', '0', '�б�', null, null, null, null, 'Y', 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010102, '����', ' ', '�����û�����', '1', '�û�����', 'add', 'icon-add', 1, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010103, '��������', 'usr/save', '��������', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010106, '������ʼ��', 'usr/initadd', '��������', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010201, '�༭', 'usr/upd', '�����û��༭', '1', '�û��༭', 'edit', 'icon-edit', 3, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010301, 'ɾ��', 'usr/del', 'ɾ��', '1', '�û�ɾ��', 'del', 'icon-no', 6, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010401, '����', 'usr/upuse', '����', '1', '�û�����', 'onuse', 'icon-ok', 4, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010501, 'ͣ��', 'usr/upuse', 'ͣ��', '1', '�û�ͣ��', 'nouse', 'icon-remove', 5, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohshaohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010601, '�����ɫ', 'role/getList', '�����ɫ', '1', '�����ɫ', 'addRole', 'icon-role', 7, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010701, '�鿴Ȩ��', 'permission/getUPermissionTree', '�鿴Ȩ��', '1', '�鿴Ȩ��', 'showPower', 'icon-power', 8, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010801, '��������', 'usr/resetPw', '��������', '1', '��������', 'resetPw', 'icon-rekey', 9, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010901, '����', 'usr/expUsers', '����', '1', '����', 'excel', 'icon-excel', 10, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010104, 'ΨһУ��', 'usr/getByAccount', 'ΨһУ��', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010602, '�����ɫ', 'role/addRole', '�����ɫ', '0', '�����ɫ', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010105, '��ʼ��������', 'deptment/getDeptTree', '��ʼ��������', '0', '��ʼ��������', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010202, '�༭�������ݼ���', 'usr/getById', '�༭�������ݼ���', '0', '�༭�������ݼ���', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010203, '�༭ҳ���ʼ��', 'usr/initedit', '�༭ҳ���ʼ��', '0', '�༭ҳ���ʼ��', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010100, '�����û�����', 'usr/init', '�����û�����', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001011101, '��ѯ', ' ', '��ѯ', '1', '��ѯ', 'termquery', 'icon-search', 0, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010902, '���󵼳��ļ�', 'usr/download', '����', '0', '����', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010903, '����ģ��', 'usr/getModel', '����ģ��', '0', '����ģ��', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010904, '������֤', 'usr/expCheck', '������֤', '0', '������֤', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001011201, '����', 'usr/excelImp', '����', '1', '����', 'excelimp', 'icon-xls-imp', 11, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020101, '�б�', 'deptment/getList', '�б�', '0', '�б�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020102, '����', ' ', '���벿������', '1', '��������', 'add', 'icon-add', 1, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020103, '��������', 'deptment/add', '��������', '0', '��������', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020104, '�����б�', 'deptment/init', '�����б�', '0', '�����б�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020201, '�༭', 'deptment/edit', '���벿�ű༭', '1', '���ű༭', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020202, '�༭_������', 'deptment/getDeptTree', '�༭_������', '0', '�༭_������', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020203, '�༭����', 'deptment/upd', '�༭����', '0', '�༭������', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020301, 'ɾ��', 'deptment/del', 'ɾ��', '1', '����ɾ��', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020302, '��֤�������Ż��û�', 'deptment/CheckDept', '��֤�������Ż��û�', '0', '��֤�������Ż��û�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020401, '���Ų鿴', 'deptment/getById', '���벿�Ų鿴', '1', '���Ų鿴', 'showInfo', 'icon-search', 4, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020402, '����������֤', 'deptment/checkDname', '����������֤', '0', '����������֤', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030101, '�б��ҳ', 'role/rolePList', '�б��ҳ', '0', '�б��ҳ', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030102, '����', ' ', '�����ɫ����', '1', '��ɫ����', 'add', 'icon-add', 1, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030103, '��������', 'role/add', '��������', '0', '��������', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030104, '�����б�', 'role/init', '�����б�', '0', '�����б�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030105, '��ɫΨһ����֤', 'role/checkRole', '��ɫΨһ����֤', '0', '��ɫΨһ����֤', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030201, '�༭', ' ', '�����ɫ�༭', '1', '��ɫ�༭', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030202, '�༭��ɫ', 'role/edit', '�༭��ɫ', '0', '�༭��ɫ', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030301, 'ɾ��', 'role/del', '��ɫɾ��', '1', '��ɫɾ��', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030302, 'ɾ����֤', 'role/CheckDept', 'ɾ����֤', '0', 'ɾ����֤', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030401, '����Ȩ��', 'permission/getPermissionTree', '����Ȩ��', '1', '����Ȩ��', 'pow', 'icon-role', 4, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030402, '�����ɫȨ��', 'role/addPow', '�����ɫȨ��', '0', '�����ɫȨ��', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030501, '����Ȩ��', 'role/datapow', '����Ȩ��', '1', '����Ȩ��', 'dataPow', 'icon-filter', 5, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030502, '�����ɫ����Ȩ��', 'role/addDataPow', '�����ɫ����Ȩ��', '0', '�����ɫ����Ȩ��', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030601, '�鿴Ȩ��', 'role/lookPow', '�鿴Ȩ��', '1', '�鿴Ȩ��', 'lookPow', 'icon-search', 6, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030503, '����������֤', 'role/checkRoleById', '����������֤', '0', '����������֤', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040101, '�б��ҳ', 'code/getPageCodeList', '�б�', '0', '�б�', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040105, '�����б�', 'code/init', '�б�', '0', '�б�', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040102, '�༭', 'code/edit', '�༭', '1', '�ֵ��༭', 'edit', 'icon-edit', 1, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040103, '�ֵ��༭����', 'code/upd', '�༭', '0', '�ֵ��༭', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040104, '�ֵ��༭�������', 'code/getCodeValueListByTypeId', '�༭', '0', '�ֵ��༭', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040106, 'У��Ҫ�༭�������Ƿ����', 'code/checkCode', 'У��Ҫ�༭�������Ƿ����', '0', 'У��Ҫ�༭�������Ƿ����', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040201, 'ɾ��', 'code/del', 'ɾ��', '1', '�ֵ��ɾ��', 'del', 'icon-no', 2, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040301, '�鿴', 'code/lookPow', '�鿴', '1', '�ֵ��鿴', 'lookPow', 'icon-search', 3, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050101, '�б�', 'sysparam/getPsysparam', '�б�', '0', '�б�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050102, '�����б�', 'sysparam/init', '�����б�', '0', '�����б�', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050103, '�༭', ' ', '����ϵͳ�����༭', '1', 'ϵͳ�����༭', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050104, '�༭ϵͳ����', 'sysparam/updSysparam', '�༭ϵͳ����', '0', '�༭ϵͳ����', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050105, '��������', 'sysparam/getDemainTree', '��������', '0', '��������', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050106, '�༭Ψһ��У��', 'sysparam/ckeckSysParam', '�༭Ψһ��У��', '0', '�༭Ψһ��У��', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050201, 'ɾ��', 'sysparam/delSysparam', 'ɾ��', '1', 'ϵͳ����ɾ��', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050107, 'У��Ҫ�����������Ƿ��ѱ�ɾ��', 'sysparam/ckeckSysP', 'У��Ҫ�����������Ƿ��ѱ�ɾ��', '0', 'У��Ҫ�����������Ƿ��ѱ�ɾ��', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050108, 'ϵͳ�����༭jsp', 'sysparam/sysparam_edit', 'ϵͳ�����༭JSP', '0', 'ϵͳ�����༭JSP', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060101, '��ʼ������', 'auditlog/init', '��ʼ������', '0', '��ʼ������', null, null, null, null, null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060102, '�б��ҳ', 'auditlog/getList', '�б��ҳ', '0', '�б��ҳ', null, null, null, null, null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060201, '��ѯ', 'auditlog/getList', '��ѯ', '1', '��ѯ', 'openQuery', 'icon-search', 10, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060202, '����', ' ', '����', '1', '����', 'resetQuery', 'icon-broom', 15, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060301, 'ˢ��', 'areaGang/getList', 'ˢ��', '1', 'ˢ��', 'refresh', 'icon-reload', 50, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 71 records loaded
prompt Loading AU_T_MENU...
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100106, '��־����', 'auditlog/init', 1001, '0', 10, null, '0', 'fuyd', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'fuyd', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, 'ϵͳ����', null, 0, '0', 100, 'nav-title2', '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, '���������������');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100101, '�û�����', 'usr/init', 1001, '0', 1, null, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100102, '���Ź���', 'deptment/init', 1001, '0', 2, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100103, '��ɫ����', 'role/init', 1001, '0', 3, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100104, '�ֵ�����', 'code/init', 1001, '0', 5, null, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100105, 'ϵͳ����', 'sysparam/init', 1001, '0', 5, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 7 records loaded
prompt Loading AU_T_PERMISSION...
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010601, '��ѯ', 100106, 1, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010102, '�༭', 100101, 2, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010103, 'ɾ��', 100101, 3, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010104, '����', 100101, 4, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010105, 'ͣ��', 100101, 5, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010106, '�����ɫ', 100101, 6, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010107, '�鿴Ȩ��', 100101, 7, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010108, '��������', 100101, 8, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010109, '����', 100101, 9, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, 'ϵͳ����', 0, 100, null, 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100101, '�û�����', 1001, 1, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010101, '����', 100101, 1, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010110, '�����б�', 100101, 10, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010111, '��ѯ', 100101, 11, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010112, '����', 100101, 12, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100102, '���Ź���', 1001, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010201, '����', 100102, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010202, '�༭', 100102, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010203, 'ɾ��', 100102, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010204, '���Ų鿴', 100102, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100103, '��ɫ����', 1001, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010301, '����', 100103, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010302, '�༭', 100103, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010303, 'ɾ��', 100103, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010304, '����Ȩ��', 100103, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010305, '����Ȩ��', 100103, 5, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010306, '�鿴Ȩ��', 100103, 6, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100104, '�ֵ�����', 1001, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010401, '�༭', 100104, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010402, 'ɾ��', 100104, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010403, '�鿴', 100104, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100105, 'ϵͳ����', 1001, 5, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010501, '�༭', 100105, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010502, 'ɾ��', 100105, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100106, '��־����', 1001, 6, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010602, 'ˢ��', 100106, 2, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 36 records loaded
prompt Loading AU_T_PERMISSION_FUNCTION...
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, 100101, 1001010101, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 10010101, 1001010102, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (3, 10010101, 1001010103, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (4, 10010101, 1001010106, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (5, 10010101, 1001010104, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (6, 10010102, 1001010201, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (7, 10010103, 1001010301, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (8, 10010104, 1001010401, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (9, 10010105, 1001010501, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10, 10010106, 1001010601, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (11, 10010107, 1001010701, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (12, 10010108, 1001010801, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (13, 10010109, 1001010901, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (14, 10010110, 1001010101, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (15, 10010101, 1001010105, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (16, 10010106, 1001010602, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (17, 10010102, 1001010202, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (18, 10010102, 1001010203, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (19, 10010101, 1001010100, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (20, 10010111, 1001011101, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (21, 10010109, 1001010902, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (22, 10010109, 1001010903, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (23, 10010109, 1001010904, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (24, 10010112, 1001011201, 'haohs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (25, 100102, 1001020101, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (26, 100102, 1001020104, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (27, 10010201, 1001020102, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (28, 10010201, 1001020103, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (29, 10010202, 1001020201, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (30, 10010202, 1001020202, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (31, 10010202, 1001020203, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (32, 10010203, 1001020301, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (33, 10010203, 1001020302, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (34, 10010204, 1001020401, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (35, 10010201, 1001020401, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (36, 10010202, 1001020402, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (37, 100103, 1001030101, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (38, 100103, 1001030104, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (39, 10010301, 1001030102, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (40, 10010301, 1001030103, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (41, 10010302, 1001030201, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (42, 10010302, 1001030202, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (43, 10010303, 1001030301, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (44, 10010303, 1001030302, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (45, 10010304, 1001030401, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (46, 10010304, 1001030402, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (47, 10010305, 1001030501, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (48, 10010305, 1001030502, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (49, 10010306, 1001030601, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (50, 10010301, 1001030105, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (51, 10010302, 1001030105, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (52, 10010302, 1001030503, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (53, 10010304, 1001030503, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (54, 10010305, 1001030503, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (55, 10010306, 1001030503, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (56, 100104, 1001040101, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (57, 100104, 1001040105, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (58, 10010401, 1001040102, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (59, 10010401, 1001040103, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (60, 10010401, 1001040106, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (61, 10010401, 1001040104, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (62, 10010402, 1001040201, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (63, 10010403, 1001040301, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (64, 10010403, 1001040106, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (65, 100105, 1001050101, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (66, 100105, 1001050102, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (67, 10010501, 1001050103, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (68, 10010501, 1001050104, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (69, 10010501, 1001050105, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (70, 10010502, 1001050201, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (71, 10010501, 1001050106, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (72, 10010501, 1001050107, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (73, 10010501, 1001050108, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (168, 100106, 1001060101, 'admin', to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (169, 100106, 1001060102, 'admin', to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (170, 10010601, 1001060201, 'admin', to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (171, 10010601, 1001060202, 'admin', to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (pk, permission_id, function_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (172, 10010602, 1001060301, 'admin', to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 78 records loaded
prompt Loading AU_T_ROLE...
insert into AU_T_ROLE (role_id, rolename, description, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 'ϵͳ����Ա', null, '0', '1', to_date('04-09-2017 13:02:56', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:02:56', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '0');
commit;
prompt 1 records loaded
prompt Loading AU_T_ROLE_DATAAUTH...
prompt Table is empty
prompt Loading AU_T_ROLE_PERMISSION...
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 1001, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (3, 100101, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (4, 10010101, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (5, 10010102, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (6, 10010103, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (7, 10010104, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (8, 10010105, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (9, 10010106, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10, 10010107, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (11, 10010108, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (12, 10010109, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (13, 10010110, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (14, 10010111, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (15, 10010112, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (16, 100102, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (17, 10010201, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (18, 10010202, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (19, 10010203, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (20, 10010204, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (21, 100103, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (22, 10010301, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (23, 10010302, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (24, 10010303, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (25, 10010304, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (26, 10010305, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (27, 10010306, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (28, 100104, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (29, 10010401, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (30, 10010402, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (31, 10010403, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (32, 100105, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (33, 10010501, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (34, 10010502, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (35, 100106, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (36, 10010601, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (37, 10010602, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
commit;

prompt Loading SY_T_CODE...
insert into SY_T_CODE (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, 'ϵͳ������', 'ϵͳ������', '1', '0', 'init', to_date('09-05-2014', 'dd-mm-yyyy'), to_date('09-05-2014', 'dd-mm-yyyy'), 'init', null, 'B');
commit;
prompt 1 records loaded
prompt Loading SY_T_CODEVALUE...
insert into SY_T_CODEVALUE (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 'ϵͳ����', '0', 1001, 'init', to_date('09-05-2014', 'dd-mm-yyyy'), to_date('09-05-2014', 'dd-mm-yyyy'), 'init', null, 'B');
commit;
prompt 1 records loaded
prompt Loading SY_T_PARAM...
insert into SY_T_PARAM (param_key, sys_key, sys_value, edit_flag, description, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, 'SysParam_maxImport', '1000', '1', '���������������', '0', '1', to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into SY_T_PARAM (param_key, sys_key, sys_value, edit_flag, description, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 'SysParam_maxExport', '1000', '1', '����������󵼳���', '0', '1', to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
commit;
prompt 2 records loaded
prompt Enabling triggers for AU_T_DEPT...
alter table AU_T_DEPT enable all triggers;
prompt Enabling triggers for AU_T_FUNCTION...
alter table AU_T_FUNCTION enable all triggers;
prompt Enabling triggers for AU_T_MENU...
alter table AU_T_MENU enable all triggers;
prompt Enabling triggers for AU_T_PERMISSION...
alter table AU_T_PERMISSION enable all triggers;
prompt Enabling triggers for AU_T_PERMISSION_FUNCTION...
alter table AU_T_PERMISSION_FUNCTION enable all triggers;
prompt Enabling triggers for AU_T_ROLE...
alter table AU_T_ROLE enable all triggers;
prompt Enabling triggers for AU_T_ROLE_DATAAUTH...
alter table AU_T_ROLE_DATAAUTH enable all triggers;
prompt Enabling triggers for AU_T_ROLE_PERMISSION...
alter table AU_T_ROLE_PERMISSION enable all triggers;
prompt Enabling triggers for AU_T_USER...
alter table AU_T_USER enable all triggers;
prompt Enabling triggers for AU_T_USER_ROLE...
alter table AU_T_USER_ROLE enable all triggers;
prompt Enabling triggers for FDI_T_ATTACH...
alter table FDI_T_ATTACH enable all triggers;
prompt Enabling triggers for SY_T_AUDITLOG...
alter table SY_T_AUDITLOG enable all triggers;
prompt Enabling triggers for SY_T_CODE...
alter table SY_T_CODE enable all triggers;
prompt Enabling triggers for SY_T_CODEVALUE...
alter table SY_T_CODEVALUE enable all triggers;
prompt Enabling triggers for SY_T_PARAM...
alter table SY_T_PARAM enable all triggers;
set feedback on
set define on
prompt Done.
