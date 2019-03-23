delete from au_t_menu where MENU_ID='100303';
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100303', '碰撞分析', 'clue/building', '1003', '0', '3', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_permission where PERMISSION_ID = '100303';
commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100303', '碰撞分析', '1003', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_function where function_id like '100303%';
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003030001', '初始化', 'clue/building', '初始化','0','初始化', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission_function where PERMISSION_ID='100303' and FUNCTION_ID like '100303%';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100303', '1003030001', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

----AU_T_ROLE_PERMISSION 

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100303, 1000, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100303, 1001, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100303, 1002, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100303, 1003, '1', sysdate, sysdate, '1', null, 'b');

commit;




