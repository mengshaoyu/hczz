drop table PZ_T_CLUE_TYPE_TAB;

-- Create table
create table PZ_T_CLUE_TYPE_TAB
(
  CLUE_TYPE_PK    VARCHAR2(36) not null,
  CLUE_TABLE_NAME VARCHAR2(200) not null,
  CLUE_TYPE       NUMBER(2) not null,
  CLUE_TYPE_NAME  VARCHAR2(100) not null
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table PZ_T_CLUE_TYPE_TAB
  is '线索类型表';
-- Add comments to the columns 
comment on column PZ_T_CLUE_TYPE_TAB.CLUE_TABLE_NAME
  is ' ---线索详情表:对应线索所在表名';
comment on column PZ_T_CLUE_TYPE_TAB.CLUE_TYPE
  is '关联clueInfo表中CLUE_TYPE';
comment on column PZ_T_CLUE_TYPE_TAB.CLUE_TYPE_NAME
  is '线索类型名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_CLUE_TYPE_TAB
  add constraint PK_CLUE_TYPE primary key (CLUE_TYPE_PK)
  using index 
  tablespace SOP_SP_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

delete from PZ_T_CLUE_TYPE_TAB;

insert into PZ_T_CLUE_TYPE_TAB (CLUE_TYPE_PK, CLUE_TABLE_NAME, CLUE_TYPE, CLUE_TYPE_NAME)
values ('1', 'DB_CLUE_APPLY', 1, '配侦申请');
insert into PZ_T_CLUE_TYPE_TAB (CLUE_TYPE_PK, CLUE_TABLE_NAME, CLUE_TYPE, CLUE_TYPE_NAME)
values ('2', 'PZ_T_RESULT', 2, '任务反馈');
commit;