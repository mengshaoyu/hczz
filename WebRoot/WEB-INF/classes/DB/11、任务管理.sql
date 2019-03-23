------au_t_menu 

delete from au_t_menu where MENU_ID in('1002','100202');
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002', '合成作战', '', '0', '0', '2', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100202', '任务管理', 'pztask/init', '1002', '0', '1', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

------AU_T_FUNCTION

delete from AU_T_FUNCTION  t where t.FUNCTION_ID like'100202%';
commit;
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020100, '进入任务管理', 'pztask/init', '进入任务管理', '0', '', '', '', '', '', '', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020101, '查询', 'pztask/getlist', '任务查询', '1', '查询', 'query', 'icon-search', 1, '2', 'Y', 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020102, '重置', ' ', '任务重置', '1', '重置', 'resetQuery', 'icon-clear', 2, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020104, '刷新', ' ', '任务刷新', '1', '刷新', 'query', 'icon-reload', 6, '2', 'Y', 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020105, '查询代办事项', 'pztask/getToDoCounts', '查询代办事项', '0', '', '', '', '', '', 'Y', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020201, '添加', 'pztb/init', '添加任务', '1', '添加', 'openAdd', 'icon-add', 3, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020501, '签收', ' pzapprove/approve', '签收按钮', '1', '签收', 'startReceive', 'icon-ok', 4, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020601, '删除', 'pztask/delete', '删除', '1', '删除', 'deleteApply', 'icon-no', 5, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020701, '分发', 'pzapprove/addAssign', '分发', '1', '分发', 'taskMission', 'icon-role', 4, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020801, '反馈表导出', 'pztask/exportWord', '反馈表导出', '0', '反馈表导出', 'exportFeedBack', 'icon-role', 1, '1', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020702, '加载线索列表', 'pzsp/loadClue', '加载线索列表', '0', '加载线索列表', 'taskMission', 'icon-role', 4, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020703, '分发初始化', 'pzsp/initdis', '分发初始化', '0', '分发初始化', 'taskMission', 'icon-role', 4, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020704, '选择民警', 'pzapprove/getDeptTree', '选择民警', '0', '选择民警', 'taskMission', 'icon-role', 4, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

------AU_T_PERMISSION 

delete from AU_T_PERMISSION t where t.PERMISSION_ID like'100202%';
commit;

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002, '合成作战', 0, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100202, '任务管理', 1002, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020201, '查询', 100202, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020202, '添加', 100202, 1, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020205, '签收', 100202, 2, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020203, '删除', 100202, 4, null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020206, '分发', 100202, 5, null, 'admin', sysdate,sysdate, 'admin', null, 'b');


commit;

delete from AU_T_PERMISSION_FUNCTION t where t.PERMISSION_ID like'100202%';
commit;

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020100, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020101, 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020102, 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020104, 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020105, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020202, 1002020201, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020205, 1002020501, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020203, 1002020601, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002020701, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020201, 1002020801, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002020702, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002020703, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002020704, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;


-------AU_T_ROLE_PERMISSION 

-----普通民警
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1002, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100202, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020201, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020202, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020203, 1000, '1', sysdate,sysdate, '1', null, 'b');

---分管领导
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1002, 1001, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100202, 1001, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020201, 1001, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020202, 1001, '1', sysdate,sysdate, '1', null, 'b');

---值班长
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1002, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100202, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020201, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020205, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020206, 1002, '1', sysdate,sysdate, '1', null, 'b');

---中心民警
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1002, 1003, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100202, 1003, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020201, 1003, '1', sysdate,sysdate, '1', null, 'b');

commit;

--20171012 列表操作按钮添加
delete from au_t_function where function_id like '10020209%';

--insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
--values (1002020901, '取回', ' ', '取回配侦申请', '2', '取回', 'back', '', 1, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020902, '审批', ' ', '审批', '2', '审批', 'approve', '', 2, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020903, '编辑',  ' ', '配侦编辑', '2', '编辑', 'edit', '', 3, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020904, '分发',  ' ', '任务分发', '2', '分发', 'taskMission', '', 4, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020905, '反馈',  ' ', '任务反馈', '2', '反馈', 'feedback', '', 5, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020906, '评价',  ' ', '评价核实', '2', '评价', 'evaluate1', '', 6, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020907, '删除',  ' ', '配侦删除', '2', '删除', 'deleteApply', '', 7, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

--20171012 权限添加
delete from AU_T_PERMISSION where PERMISSION_ID in (10020204,10020207,10020208,10020209,10020210);

--insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
--values (10020204, '取回', 100202, 3, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020207, '审批', 100202, 6, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020208, '编辑', 100202, 7, null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020209, '反馈', 100202, 8, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020210, '评价', 100202, 9, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

--20171012 permission-function关联关系添加
delete from AU_T_PERMISSION_FUNCTION where function_id like '10020209%';

--insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
--values (SQ_PERMISSION_FUNCTION.nextval, 10020204, 1002020901, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020207, 1002020902, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020208, 1002020903, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002020904, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020209, 1002020905, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020210, 1002020906, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020203, 1002020907, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

---任务详情界面按钮控制
insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002021001, '编辑', ' ', '编辑', '3', '编辑', 'editApply', '', 1, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002021002, '取回', ' ', '取回配侦申请', '3', '', 'back', '', 1, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002021003, '分发', ' ', '分发', '3', '分发', 'addAssign', '', 2, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002021004, '审批', ' ', '审批', '3', '审批', 'approve', '', 2, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

---控制任务详情界面的权限

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020208, 1002021001, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020204, 1002021002, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020206, 1002021003, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020207, 1002021004, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

---分管领导
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020207, 1001, '1', sysdate,sysdate, '1', null, 'b');
--值班长
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020207, 1002, '1', sysdate,sysdate, '1', null, 'b');
--普通民警
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020203, 1000, '1', sysdate,sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020210, 1000, '1', sysdate,sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020208, 1000, '1', sysdate,sysdate, '1', null, 'b');
--合成中心民警
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020209, 1003, '1', sysdate,sysdate, '1', null, 'b');
commit;

--20171013 删除多余的按钮
update au_t_function f set f.is_toolbar = 0 where f.function_id in (1002020104,1002020201,1002020501,1002020601,1002020701);
commit;

--20171023 评价按钮改为核实
update au_t_function f set f.toobar_title = '核实',f.functionname = '核实' where f.function_id = '1002020906';
update au_t_permission p set p.authority_name = '核实' where p.permission_id = '10020210';
commit;

--20171027 添加报告按钮
insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002020908, '报告',  ' ', '查看报告', '2', '报告', 'toreports', '', 8, '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10020211, '报告', 100202, 10, null, 'admin', sysdate,sysdate, 'admin', null, 'b');
insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10020211, 1002020908, 'admin', sysdate,sysdate, 'admin', null, 'b');
--20171027 预置报告权限
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020211, 1000, '1', sysdate,sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020211, 1001, '1', sysdate,sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020211, 1002, '1', sysdate,sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10020211, 1003, '1', sysdate,sysdate, '1', null, 'b');
commit;


