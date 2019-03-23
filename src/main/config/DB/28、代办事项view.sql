create or replace view pz_v_flow_wait as
select to_char(rt.assignee_) user_id,
       rt.proc_inst_id_ proc_id,
       rt.id_ task_id,
       rt.task_def_key_ task_key,
       rt.create_time_ + 0 create_dt
  from act_ru_task rt
 where rt.assignee_ is not null
union all
--2、指定多个人
select to_char(ri.user_id_) user_id,
       rt.proc_inst_id_ proc_id,
       rt.id_ task_id,
       rt.task_def_key_ task_key,
       rt.create_time_ + 0 create_dt
  from act_ru_task rt, act_ru_identitylink ri
 where rt.id_ = ri.task_id_
   and ri.user_id_ is not null
union all
--3、指定角色
select to_char(ur.user_id) user_id,
       rt.proc_inst_id_ proc_id,
       rt.id_ task_id,
       rt.task_def_key_ task_key,
       rt.create_time_ + 0 create_dt
  from act_ru_task rt, act_ru_identitylink ri, au_t_user_role ur
 where rt.id_ = ri.task_id_
   and ri.group_id_ = ur.role_id
   and ur.user_id is not null
;
