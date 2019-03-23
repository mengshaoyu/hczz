
--20171023 评价按钮改为核实
update au_t_function f set f.toobar_title = '核实',f.functionname = '核实' where f.function_id = '1002020906';
update au_t_permission p set p.authority_name = '核实' where p.permission_id = '10020210';
commit;

