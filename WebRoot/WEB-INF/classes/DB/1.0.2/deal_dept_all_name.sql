update AU_T_DEPT t set t.dept_name='�ൺ�й������б��־�'||t.dept_name where t.dept_name like'%�ɳ���%';
commit;

update AU_T_DEPT t set t.dept_name='�ൺ�й������б��־�' where t.dept_id=3;
commit;