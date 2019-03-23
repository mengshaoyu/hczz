update AU_T_DEPT t set t.dept_name='青岛市公安局市北分局'||t.dept_name where t.dept_name like'%派出所%';
commit;

update AU_T_DEPT t set t.dept_name='青岛市公安局市北分局' where t.dept_id=3;
commit;