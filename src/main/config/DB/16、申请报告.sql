delete from au_t_permission where PERMISSION_ID = '100212';
commit;

insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100212', '���뱨��', '100202', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from au_t_function where function_id like '100212%';
commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120001', '�������뱨��', 'pzsp/init', '�������뱨��', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120002', '�������뱨������', 'pzsp/loadHctb', '�������뱨������', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120003', '�������뱨��', 'pzsp/exportWord', '�������뱨��', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120004', '����', 'pzsp/saveNext', '����', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120005', '���طַ���', 'pzapprove/getSumlist', '���طַ���', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120006', '�ַ�', 'pzapprove/addAssign', '�ַ�', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120007', '����', 'commLang/init', '����', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120008', '��ӳ�����', 'commLang/createOrUpdateLangs', '��ӳ�����', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120009', '������ָ�Ĭ��ֵ', 'commLang/deleteLangs', '������ָ�Ĭ��ֵ', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002120010', '��ѯ������', 'commLang/loadLangs', '��ѯ������', '0', '', '', '', '', '', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

delete from au_t_permission_function where PERMISSION_ID='100212' and FUNCTION_ID like '100212%';
commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120001', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120002', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120003', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120004', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120005', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120006', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120007', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120008', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120009', 'admin', sysdate, sysdate, 'admin', null, 'b');
insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '100212', '1002120010', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

delete from AU_T_ROLE_PERMISSION where PERMISSION_ID = 100212;
commit;

insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100212, 1000, '1', sysdate, sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100212, 1001, '1', sysdate, sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100212, 1002, '1', sysdate, sysdate, '1', null, 'b');
insert into AU_T_ROLE_PERMISSION (PK, PERMISSION_ID, ROLE_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_ROLE_PERMISSION.nextval, 100212, 1003, '1', sysdate, sysdate, '1', null, 'b');
commit;