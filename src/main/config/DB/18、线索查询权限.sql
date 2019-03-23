
delete from au_t_menu where MENU_ID in('1003','100301');

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003', '线索库管理', '', '0', '0', '3', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100301', '线索联查', 'clue/init', '1003', '0', '1', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

---au_t_function 

delete from au_t_function where FUNCTION_ID like'100301%';

commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10030101', '线索联查', 'clue/init', '线索联查', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10030102', '线索查询', 'clue/getlist', '线索查询', '1', '线索查询', 'query', 'icon-search', '1', '2', 'Y', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10030103', '重置', 'clue/reload', '重置', '1', '重置', 'resetQuery', 'icon-clear', '2', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10030104', '刷新', 'clue/refresh', '刷新', '1', '刷新', 'refresh', 'icon-reload', '3', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

---au_t_permission 

delete from au_t_permission where permission_id like'100301%';

commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003', '线索库管理', '0', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');


insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100301', '线索查询', '1003', '3', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

---au_t_permission_function 

delete from au_t_permission_function where permission_id like'100301%';

commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100301', '10030101', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100301', '10030102', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100301', '10030103', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100301', '10030104', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;


---AU_T_ROLE_PERMISSION 

delete from AU_T_ROLE_PERMISSION where permission_id like'100301%';

commit;

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 1003, 2, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 1003, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 1003, 1001, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 1003, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 1003, 1003, '1', sysdate,sysdate, '1', null, 'b');

commit;

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 100301, 2, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 100301, 1000, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 100301, 1001, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 100301, 1002, '1', sysdate,sysdate, '1', null, 'b');

insert into AU_T_ROLE_PERMISSION (pk, permission_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_ROLE_PERMISSION.nextval, 100301, 1003, '1', sysdate,sysdate, '1', null, 'b');

commit;


delete from au_t_function t where t.FUNCTION_ID='10030104';

commit;

delete from au_t_permission_function f where f.FUNCTION_ID='10030104';

commit;
