----PZ_T_APPLY
alter table PZ_T_APPLY add sum_up varchar2(1500);
-- Add comments to the columns 
comment on column PZ_T_APPLY.sum_up
  is '����';

----PZ_T_APPLY
comment on column PZ_T_APPLY.remark 
  is '��ע(2017/10/24�ַ�������)';

------PZ_T_CLUE_INFO

alter table PZ_T_CLUE_INFO add clue_sumup varchar2(1500);
-- Add comments to the columns 
comment on column PZ_T_CLUE_INFO.clue_sumup
  is '��������';


------PZ_T_RESULT
alter table PZ_T_RESULT add result_remark varchar2(2000);
-- Add comments to the columns 
comment on column PZ_T_RESULT.result_remark
  is '��������';