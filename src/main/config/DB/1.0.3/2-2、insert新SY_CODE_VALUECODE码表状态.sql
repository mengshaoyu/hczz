insert into SY_T_CODEVALUE
  (PK,
   VALUE_DESC,
   CODE_VALUE,
   TYPE_ID,
   CREATE_BY,
   CREATE_DT,
   UPDATE_DT,
   UPDATE_BY,
   ACT_BY_TYPE)
  select SQ_CODE_VALUE.nextval,
         mc,
         dm,
         2001,
         1,
         sysdate,
         sysdate as sysdate1,
         1,
         'b'
    from gxpt.sop_case_ajlx t
  
   where t.DM not in
         (select k.CODE_VALUE from SY_T_CODEVALUE k where k.type_id = '2001');
commit;

insert into SY_T_CODEVALUE
  (PK,
   VALUE_DESC,
   CODE_VALUE,
   TYPE_ID,
   CREATE_BY,
   CREATE_DT,
   UPDATE_DT,
   UPDATE_BY,
   ACT_BY_TYPE)
  select SQ_CODE_VALUE.nextval,
         mc,
         dm,
         1005,
         1,
         sysdate,
         sysdate as sysdate1,
         1,
         'b'
    from gxpt.sop_case_ajzt t
  
   where t.DM not in
         (select k.CODE_VALUE from SY_T_CODEVALUE k where k.type_id = '1005');
commit;

insert into SY_T_CODEVALUE
  (PK,
   VALUE_DESC,
   CODE_VALUE,
   TYPE_ID,
   CREATE_BY,
   CREATE_DT,
   UPDATE_DT,
   UPDATE_BY,
   ACT_BY_TYPE)
  select SQ_CODE_VALUE.nextval,
         mc,
         dm,
         1008,
         1,
         sysdate,
         sysdate as sysdate1,
         1,
         'b'
    from gxpt.sop_case_jqlb t
   where t.DM not in
         (select k.CODE_VALUE from SY_T_CODEVALUE k where k.type_id = '1008');
commit;

insert into SY_T_CODEVALUE
  (PK,
   VALUE_DESC,
   CODE_VALUE,
   TYPE_ID,
   CREATE_BY,
   CREATE_DT,
   UPDATE_DT,
   UPDATE_BY,
   ACT_BY_TYPE)
  select SQ_CODE_VALUE.nextval,
         mc,
         dm,
         2005,
         1,
         sysdate,
         sysdate as sysdate1,
         1,
         'b'
    from gxpt.sop_case_aybh t
  
   where t.DM not in
         (select k.CODE_VALUE from SY_T_CODEVALUE k where k.type_id = '2005');
commit;