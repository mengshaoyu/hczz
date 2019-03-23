---AU_T_MENU

delete from au_t_menu t where t.menu_id in('1005','100501');
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005', '警情概览', '', '0', '0', '1', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_MENU (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100501, '警情统计', 'clue/building', 1005, '0', 1, null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission where PERMISSION_ID = '1005';
delete from au_t_permission where PERMISSION_ID = '100501';

commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005', '警情概览', '0', '1', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100501', '警情统计', '1005', '1', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_function where function_id like '100501%';
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1005010001', '进入警情统计', 'clue/building', '初始化警情统计', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission_function where PERMISSION_ID='100501';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100501', '1005010001', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

----AU_T_ROLE_PERMISSION 

delete from AU_T_ROLE_PERMISSION where PERMISSION_ID='1005';
commit;

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1005, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 1005, 1003, '1', sysdate, sysdate, '1', null, 'b');

commit;

delete from AU_T_ROLE_PERMISSION where PERMISSION_ID='100501';
commit;

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100501, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100501, 1003, '1', sysdate, sysdate, '1', null, 'b');

commit;
