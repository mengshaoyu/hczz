delete from au_t_menu where MENU_ID='100302';
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100302', '资源导入', 'clue/building', '1003', '0', '2', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_permission where PERMISSION_ID = '100302';
commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100302', '资源导入', '1003', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_function where function_id like '100302%';
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003020001', '初始化', 'clue/building', '初始化', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission_function where PERMISSION_ID='100302' and FUNCTION_ID like '100302%';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100302', '1003020001', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

----AU_T_ROLE_PERMISSION 

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100302, 1000, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100302, 1001, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100302, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100302, 1003, '1', sysdate, sysdate, '1', null, 'b');

commit;




