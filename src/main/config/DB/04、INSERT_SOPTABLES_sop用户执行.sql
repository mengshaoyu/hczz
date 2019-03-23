prompt PL/SQL Developer import file
prompt Created on 2017年9月5日 by mengsy
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
values (1, '青岛市公安局', '370200000000', 0, null, null, null, 0, '1', to_date('04-09-2017', 'dd-mm-yyyy'), to_date('04-09-2017', 'dd-mm-yyyy'), '1', null, 'B');
commit;
prompt 2 records loaded
prompt Loading AU_T_FUNCTION...
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010101, '列表', 'usr/getPList', '列表', '0', '列表', null, null, null, null, 'Y', 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010102, '新增', ' ', '进入用户新增', '1', '用户新增', 'add', 'icon-add', 1, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010103, '新增保存', 'usr/save', '新增保存', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010106, '新增初始化', 'usr/initadd', '新增保存', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010201, '编辑', 'usr/upd', '进入用户编辑', '1', '用户编辑', 'edit', 'icon-edit', 3, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010301, '删除', 'usr/del', '删除', '1', '用户删除', 'del', 'icon-no', 6, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010401, '启用', 'usr/upuse', '启用', '1', '用户启用', 'onuse', 'icon-ok', 4, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010501, '停用', 'usr/upuse', '停用', '1', '用户停用', 'nouse', 'icon-remove', 5, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohshaohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010601, '授予角色', 'role/getList', '授予角色', '1', '授予角色', 'addRole', 'icon-role', 7, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010701, '查看权限', 'permission/getUPermissionTree', '查看权限', '1', '查看权限', 'showPower', 'icon-power', 8, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010801, '密码重置', 'usr/resetPw', '密码重置', '1', '密码重置', 'resetPw', 'icon-rekey', 9, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010901, '导出', 'usr/expUsers', '导出', '1', '导出', 'excel', 'icon-excel', 10, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010104, '唯一校验', 'usr/getByAccount', '唯一校验', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010602, '保存角色', 'role/addRole', '保存角色', '0', '保存角色', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010105, '初始化部门树', 'deptment/getDeptTree', '初始化部门树', '0', '初始化部门树', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010202, '编辑基本数据加载', 'usr/getById', '编辑基本数据加载', '0', '编辑基本数据加载', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010203, '编辑页面初始化', 'usr/initedit', '编辑页面初始化', '0', '编辑页面初始化', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010100, '进入用户管理', 'usr/init', '进入用户管理', '0', null, null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001011101, '查询', ' ', '查询', '1', '查询', 'termquery', 'icon-search', 0, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010902, '请求导出文件', 'usr/download', '导出', '0', '导出', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010903, '导出模板', 'usr/getModel', '导出模板', '0', '导出模板', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001010904, '导出验证', 'usr/expCheck', '导出验证', '0', '导出验证', null, null, null, null, null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001011201, '导入', 'usr/excelImp', '导入', '1', '导入', 'excelimp', 'icon-xls-imp', 11, '2', null, 'haohs', to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'haohs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020101, '列表', 'deptment/getList', '列表', '0', '列表', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020102, '新增', ' ', '进入部门新增', '1', '部门新增', 'add', 'icon-add', 1, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020103, '新增保存', 'deptment/add', '新增保存', '0', '新增保存', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020104, '进入列表', 'deptment/init', '进入列表', '0', '进入列表', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020201, '编辑', 'deptment/edit', '进入部门编辑', '1', '部门编辑', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020202, '编辑_部门树', 'deptment/getDeptTree', '编辑_部门树', '0', '编辑_部门树', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020203, '编辑部门', 'deptment/upd', '编辑部门', '0', '编辑部门树', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020301, '删除', 'deptment/del', '删除', '1', '部门删除', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020302, '验证下属部门或用户', 'deptment/CheckDept', '验证下属部门或用户', '0', '验证下属部门或用户', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020401, '部门查看', 'deptment/getById', '进入部门查看', '1', '部门查看', 'showInfo', 'icon-search', 4, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001020402, '排它操作验证', 'deptment/checkDname', '排它操作验证', '0', '排它操作验证', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030101, '列表分页', 'role/rolePList', '列表分页', '0', '列表分页', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030102, '新增', ' ', '进入角色新增', '1', '角色新增', 'add', 'icon-add', 1, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030103, '新增保存', 'role/add', '新增保存', '0', '新增保存', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030104, '进入列表', 'role/init', '进入列表', '0', '进入列表', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030105, '角色唯一性验证', 'role/checkRole', '角色唯一性验证', '0', '角色唯一性验证', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030201, '编辑', ' ', '进入角色编辑', '1', '角色编辑', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030202, '编辑角色', 'role/edit', '编辑角色', '0', '编辑角色', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030301, '删除', 'role/del', '角色删除', '1', '角色删除', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030302, '删除验证', 'role/CheckDept', '删除验证', '0', '删除验证', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030401, '授予权限', 'permission/getPermissionTree', '授予权限', '1', '授予权限', 'pow', 'icon-role', 4, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030402, '授予角色权限', 'role/addPow', '授予角色权限', '0', '授予角色权限', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030501, '数据权限', 'role/datapow', '数据权限', '1', '数据权限', 'dataPow', 'icon-filter', 5, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030502, '授予角色数据权限', 'role/addDataPow', '授予角色数据权限', '0', '授予角色数据权限', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030601, '查看权限', 'role/lookPow', '查看权限', '1', '查看权限', 'lookPow', 'icon-search', 6, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001030503, '排它操作验证', 'role/checkRoleById', '排它操作验证', '0', '排它操作验证', null, null, null, null, null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040101, '列表分页', 'code/getPageCodeList', '列表', '0', '列表', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040105, '进入列表', 'code/init', '列表', '0', '列表', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040102, '编辑', 'code/edit', '编辑', '1', '字典表编辑', 'edit', 'icon-edit', 1, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040103, '字典表编辑保存', 'code/upd', '编辑', '0', '字典表编辑', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040104, '字典表编辑进入界面', 'code/getCodeValueListByTypeId', '编辑', '0', '字典表编辑', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040106, '校验要编辑的数据是否存在', 'code/checkCode', '校验要编辑的数据是否存在', '0', '校验要编辑的数据是否存在', null, null, null, null, null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040201, '删除', 'code/del', '删除', '1', '字典表删除', 'del', 'icon-no', 2, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001040301, '查看', 'code/lookPow', '查看', '1', '字典表查看', 'lookPow', 'icon-search', 3, '2', null, 'yangzs', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'yangzs', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050101, '列表', 'sysparam/getPsysparam', '列表', '0', '列表', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050102, '进入列表', 'sysparam/init', '进入列表', '0', '进入列表', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050103, '编辑', ' ', '进入系统参数编辑', '1', '系统参数编辑', 'edit', 'icon-edit', 2, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050104, '编辑系统参数', 'sysparam/updSysparam', '编辑系统参数', '0', '编辑系统参数', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050105, '所属域树', 'sysparam/getDemainTree', '所属域树', '0', '所属域树', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050106, '编辑唯一性校验', 'sysparam/ckeckSysParam', '编辑唯一性校验', '0', '编辑唯一性校验', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050201, '删除', 'sysparam/delSysparam', '删除', '1', '系统参数删除', 'del', 'icon-no', 3, '2', null, 'wangdh', to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 17:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050107, '校验要操作的数据是否已被删除', 'sysparam/ckeckSysP', '校验要操作的数据是否已被删除', '0', '校验要操作的数据是否已被删除', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001050108, '系统参数编辑jsp', 'sysparam/sysparam_edit', '系统参数编辑JSP', '0', '系统参数编辑JSP', null, null, null, null, null, 'wangdh', to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-04-2014 16:03:00', 'dd-mm-yyyy hh24:mi:ss'), 'wangdh', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060101, '初始化界面', 'auditlog/init', '初始化界面', '0', '初始化界面', null, null, null, null, null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060102, '列表分页', 'auditlog/getList', '列表分页', '0', '列表分页', null, null, null, null, null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060201, '查询', 'auditlog/getList', '查询', '1', '查询', 'openQuery', 'icon-search', 10, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060202, '重置', ' ', '重置', '1', '重置', 'resetQuery', 'icon-broom', 15, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_FUNCTION (function_id, functionname, url, descpt, is_toolbar, toobar_title, js_method, icon_css, toolbar_order, open_method, hasaudata, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001060301, '刷新', 'areaGang/getList', '刷新', '1', '刷新', 'refresh', 'icon-reload', 50, '2', null, 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 71 records loaded
prompt Loading AU_T_MENU...
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100106, '日志管理', 'auditlog/init', 1001, '0', 10, null, '0', 'fuyd', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'fuyd', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, '系统管理', null, 0, '0', 100, 'nav-title2', '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, '你好你好你好你好你好');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100101, '用户管理', 'usr/init', 1001, '0', 1, null, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100102, '部门管理', 'deptment/init', 1001, '0', 2, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100103, '角色管理', 'role/init', 1001, '0', 3, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100104, '字典表管理', 'code/init', 1001, '0', 5, null, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_MENU (menu_id, menu_name, menu_action, menu_parentid, menu_disabled, menu_sort_order, iconcls, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100105, '系统参数', 'sysparam/init', 1001, '0', 5, null, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
commit;
prompt 7 records loaded
prompt Loading AU_T_PERMISSION...
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010601, '查询', 100106, 1, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010102, '编辑', 100101, 2, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010103, '删除', 100101, 3, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010104, '启用', 100101, 4, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010105, '停用', 100101, 5, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010106, '授予角色', 100101, 6, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010107, '查看权限', 100101, 7, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010108, '密码重置', 100101, 8, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010109, '导出', 100101, 9, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, '系统管理', 0, 100, null, 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100101, '用户管理', 1001, 1, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010101, '新增', 100101, 1, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010110, '数据列表', 100101, 10, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010111, '查询', 100101, 11, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010112, '导入', 100101, 12, '0', 'admin', to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-04-2014 12:12:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100102, '部门管理', 1001, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010201, '新增', 100102, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010202, '编辑', 100102, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010203, '删除', 100102, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010204, '部门查看', 100102, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100103, '角色管理', 1001, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010301, '新增', 100103, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010302, '编辑', 100103, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010303, '删除', 100103, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010304, '授予权限', 100103, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010305, '数据权限', 100103, 5, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010306, '查看权限', 100103, 6, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100104, '字典表管理', 1001, 4, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010401, '编辑', 100104, 1, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010402, '删除', 100104, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010403, '查看', 100104, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100105, '系统参数', 1001, 5, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010501, '编辑', 100105, 2, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010502, '删除', 100105, 3, '0', 'admin', to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('17-04-2014 16:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (100106, '日志管理', 1001, 6, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
insert into AU_T_PERMISSION (permission_id, authority_name, parent_id, sort_order, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10010602, '刷新', 100106, 2, '0', 'admin', to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2016 20:14:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, 'b');
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
values (2, '系统管理员', null, '0', '1', to_date('04-09-2017 13:02:56', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:02:56', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '0');
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
values (1001, '系统所属域', '系统所属域', '1', '0', 'init', to_date('09-05-2014', 'dd-mm-yyyy'), to_date('09-05-2014', 'dd-mm-yyyy'), 'init', null, 'B');
commit;
prompt 1 records loaded
prompt Loading SY_T_CODEVALUE...
insert into SY_T_CODEVALUE (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, '系统管理', '0', 1001, 'init', to_date('09-05-2014', 'dd-mm-yyyy'), to_date('09-05-2014', 'dd-mm-yyyy'), 'init', null, 'B');
commit;
prompt 1 records loaded
prompt Loading SY_T_PARAM...
insert into SY_T_PARAM (param_key, sys_key, sys_value, edit_flag, description, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, 'SysParam_maxImport', '1000', '1', '单次数据最大导入数', '0', '1', to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into SY_T_PARAM (param_key, sys_key, sys_value, edit_flag, description, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, 'SysParam_maxExport', '1000', '1', '单次数据最大导出数', '0', '1', to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
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
