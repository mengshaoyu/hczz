create or replace function SOP_APPLY_FLOW_TIME(APPLY_ID IN VARCHAR2,START_FLOW IN VARCHAR2,END_FLOW IN VARCHAR2) return varchar2 is
--定义变量与查询表中的字段名冲突时，会出现所查非所想的情况
  time_diff varchar2(100);
begin

 select to_char(wm_concat(sign_time))||','||to_char(wm_concat(back_time)) into time_diff  from (

  select to_char(max(t.start_time_),'yyyy-MM-dd hh24:mi:ss') sign_time,'' back_time from act_hi_taskinst t
  join pz_t_apply p on t.proc_inst_id_=p.flow_id where p.pz_apply_id=APPLY_ID
  and t.task_def_key_=START_FLOW

  union all

  select '' sign_time, to_char(max(t.start_time_),'yyyy-MM-dd hh24:mi:ss') back_time from act_hi_taskinst t
  join pz_t_apply p on t.proc_inst_id_=p.flow_id where p.pz_apply_id=APPLY_ID
  and t.task_def_key_=END_FLOW
);

if length(time_diff)<21 then
  time_diff:=null;
end if;

  return(time_diff);
end SOP_APPLY_FLOW_TIME;

/