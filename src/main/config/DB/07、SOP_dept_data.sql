delete from AU_T_DEPT;

commit;

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, '�����ع�����', '321324000000', 0, '����ʡ�����ع�����', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), null, 0, '1', sysdate, sysdate, '1', null, 'B');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, '���÷��������', '321324020000', 1, '�����ع����־��÷��������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 2, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (3, '�ΰ�������', '321324030000', 1, '�����ع������ΰ�������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 3, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (4, '���¾�����', '321324050000', 1, '�����ع��������¾�����', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 4, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (5, '���뾳������', '321324060000', 1, '�����ع����ֳ��뾳������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 5, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (6, '���簲ȫ�������', '321324110000', 1, '�����ع��������簲ȫ�������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 6, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (7, '�ŷÿ�', '321324150000', 1, '�����ع������ŷÿ�', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 7, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (8, '��ͨ������', '321324170000', 1, '�����ع����ֽ�ͨ������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 8, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (9, '���ƴ��', '321324180000', 1, '�����ع����ַ��ƴ��', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 9, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10, '��Ϣͨ�Ŵ��', '321324230000', 1, '�����ع�������Ϣͨ�Ŵ��', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 10, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (11, '�й�������ίά���ȶ������쵼С��칫��', '321324290000', 1, '�й�������ίά���ȶ������쵼С��칫��', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 11, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (12, 'ָ������', '321324330000', 1, '�����ع�����ָ������', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 12, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (13, '�鱨��Ϣ��', '321324330100', 12, '�����ع�����ָ�������鱨��Ϣ��', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 13, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (14, '�����ɳ���', '321324520000', 1, '�����ع����������ɳ���', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 14, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

