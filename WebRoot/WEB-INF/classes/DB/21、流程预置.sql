
delete from PZ_T_FLOW;
commit;

insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1001, 1002, '1', '1000', '申请配侦', null, '1', null, null, null, '1', 1001, 1010, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1002, 1003, null, '1001', '分管领导审批', null, '1', null, null, null, '2', 1001, 1010, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1003, 1004, null, '1002', '值班长签收', null, '1', null, 1002, null, '3', 1001, 1010, '1', to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1004, 1005, null, '1002', '待值班长审批', null, '1', null, null, null, '9', 1001, 1010, '1', to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1005, 1006, null, '1002', '待分配', null, '1', null, null, null, '4', 1001, 1004, '1', to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:43', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1006, 1007, null, '1003', '待反馈', null, '1', null, null, null, '5', 1001, 1005, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1007, 1008, null, '1000', '待核实', null, '1', null, null, null, '6', 1001, 1008, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1008, 0, null, '1000', '完结', null, '1', null, null, null, '7', 1001, 0, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
insert into PZ_T_FLOW (flow_id, next_id, is_start, start_role, flow_desc, is_specified, flow_type, repare_users, repare_roles, remark, flow_status, flow_diff, rebut_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1010, 1002, null, '1000', '退侦补充', null, '1', null, null, null, '8', 1001, 1010, '1', to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-09-2017 14:27:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
commit;

