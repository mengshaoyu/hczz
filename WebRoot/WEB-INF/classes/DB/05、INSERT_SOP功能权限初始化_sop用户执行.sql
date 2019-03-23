insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100107', '值班长管理', 'rota/init', '1001', '1', '0', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100107', '值班长管理', '1001', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010701', '查询', '100107', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010702', '添加', '100107', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010703', '导入', '100107', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010704', '删除', '100107', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010705', '自动排班', '100107', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001070100', '进入值班长管理', 'rota/init', '进入值班长管理', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001070101', '查询', ' ', '查询按钮', '1', '查询', 'query', 'icon-search', '1', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001070102', '列表', 'rota/list', '列表', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010701', '1001070100', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010701', '1001070101', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010701', '1001070102', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;


insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002020401', '进入任务查看详情', 'pztask/initDetail', '进入任务查看详情', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10020201', '1002020401', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (38, 10010602, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002020301', '审批', ' pzapprove/approve', '审批按钮', '1', '审批', 'openApprove', 'icon-edit', '3', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10020203', '审批', '100202', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10020203', '1002020301', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002020501', '签收', ' pzapprove/approve', '签收按钮', '1', '签收', 'startReceive', 'icon-ok', '4', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10020205', '签收', '100202', '4', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10020205', '1002020501', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

--用户管理-->警种类型
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010113', '警种', '100101', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001011301', '警种绑定', 'code/getCodeValueListByTypeId', '警种按钮', '1', '警种', 'openPolice', 'icon-edit', '13', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001011302', '警种编辑', 'usr/blindPT', '保存警种绑定', '', '', '', '', '14', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010113', '1001011301', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010113', '1001011302', 'admin', sysdate, sysdate, 'admin', null, 'b');

---该动能授权角色

commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002020601', '分发查询', 'pzapprove/getSumlist', '分发查询', '1', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10020206', '分发', '100202', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10020206', '1002020601', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (40, 10020206, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003010101', '线索管理', 'clue/init', '进入值班长管理', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003010102', '线索查询', 'clue/getlist', '线索查询', '1', '线索查询', 'query', 'icon-search', '1', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10030101', '线索查询', '100301', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10030101', '1003010101', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10030101', '1003010102', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (41, 10030101, 2, '1', to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-09-2017 13:07:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');


insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003', '线索库管理', '', '0', '0', '2', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100301', '线索检索', 'clue/init', '1003', '0', '1', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;