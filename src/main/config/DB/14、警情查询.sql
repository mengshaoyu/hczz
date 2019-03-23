delete from au_t_menu t where t.menu_id in('100502');
commit;

insert into AU_T_MENU (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100502, '警情查询', 'alarm/init', 1005, '0', 2, null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission where PERMISSION_ID like '100502%';

commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100502', '警情查询', '1005', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10050201', '查询', '100502', '1', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10050202', '导入', '100502', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_function where function_id like '100502%';
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005020001', '进入警情查询', 'alarm/init', '初始化警情查询', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005020002', '查询', 'alarm/getlist', '查询', '1', '', 'query', '', '1', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005020003', '导入', 'alarm/import', '导入', '1', '', 'openImport', '', '2', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

----警情 重置按钮
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005020004', '重置', ' ', '重置', '1', '重置', 'resetQuery', '', '2', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission_function where PERMISSION_ID like '100502%';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100502', '1005020001', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10050201', '1005020002', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10050202', '1005020003', 'admin', sysdate, sysdate, 'admin', null, 'b');

---- 警情 重置按钮
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10050202', '1005020004', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

----AU_T_ROLE_PERMISSION 


delete from AU_T_ROLE_PERMISSION where PERMISSION_ID like'100502%';
commit;

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100502, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10050201, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10050202, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100502, 1003, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10050201, 1003, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 10050202, 1003, '1', sysdate, sysdate, '1', null, 'b');

commit;
