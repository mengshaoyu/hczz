delete from au_t_menu where MENU_ID='100205';
commit;
insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100205', '合成统计', 'analyze/init', '1002', '0', '5', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from AU_T_PERMISSION t where t.PERMISSION_ID like '100205%';
commit;
insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100205, '合成统计', 1002, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');
commit;


delete from AU_T_FUNCTION  t where t.FUNCTION_ID like '100205%';
commit;
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002050100', '合成统计', 'analyze/init', '合成统计', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_permission_function where PERMISSION_ID='100205' and FUNCTION_ID like '100205%';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100205', '1002050100', 'admin', sysdate, sysdate, 'admin', null, 'b');


delete from AU_T_ROLE_PERMISSION where PERMISSION_ID = 100205;
commit;

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100205, 1006, '1', sysdate, sysdate, '1', null, 'b');
commit;
