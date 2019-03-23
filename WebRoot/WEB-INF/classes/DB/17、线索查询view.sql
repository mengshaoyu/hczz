create or replace view pz_v_clue as
select c.clue_id clue_id,
       '' case_no,
       '配侦申请' clue_type_name,
       1 clue_type,
       c.clue_content clue_desc,
      (select  to_char(wm_concat(t.att_rname)) att_rname from fdi_t_attach t where t.bus_id=c.clue_id and t.module_type=1) fileName,
       c.uploader create_by,
       u.username username,
       c.write_time create_dt,
       d.dept_id
  from db_clue_apply c
  join au_t_user u on c.uploader=u.user_id
  join au_t_dept d on u.dept_id=d.dept_id
union
select r.clue_id clue_id,
       r.case_no case_no,
       '反馈报告'clue_type_name,
       2 clue_type,
       r.result_desc clue_desc,
       (select to_char(wm_concat(t.att_rname)) att_rname from fdi_t_attach t where t.bus_id=r.result_id and t.module_type=5) fileName,
      r.create_by create_by,
       u.username username,
       r.create_dt create_dt,
       d.dept_id
  from pz_t_result r
  join au_t_user u on r.create_by=u.user_id
  join au_t_dept d on u.dept_id=d.dept_id where r.is_normal_pz_clue=1;
/