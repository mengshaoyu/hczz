--�û�����-->��������
insert into au_t_permission (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('10010113', '����', '100101', '0', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001011301', '���ְ�', 'code/getCodeValueListByTypeId', '���ְ�ť', '1', '����', 'openPolice', 'icon-edit', '13', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_function (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001011302', '���ֱ༭', 'usr/blindPT', '���澯�ְ�', '', '', '', '', '14', '2', null, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010113', '1001011301', 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into au_t_permission_function (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (sq_permission_function.nextval, '10010113', '1001011302', 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;


---�ö�����Ȩ��ɫ



