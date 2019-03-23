drop table PZ_T_EFFECT;
-- Create table 效能记录表
create table PZ_T_EFFECT
(
  pz_effect_id    VARCHAR2(32) not null,
  pz_apply_id    VARCHAR2(32),
  effect_type    VARCHAR(8),
  total       NUMBER(20),
  type1      NUMBER(20) default 0,
  type2       NUMBER(20) default 0,
  type3        NUMBER(20) default 0,
  type4        NUMBER(20) default 0,
  type5        NUMBER(20) default 0,
  remark       NVARCHAR2(500),
  create_by     NVARCHAR2(50) not null,
  create_dt     DATE not null,
  update_dt     DATE not null,
  update_by     NVARCHAR2(50) not null,
  delete_by     NVARCHAR2(50),
  act_by_type   NVARCHAR2(10) not null
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table PZ_T_EFFECT
  is '效能记录表';
-- Add comments to the columns 
comment on column PZ_T_EFFECT.pz_effect_id
  is '效能记录主键';
comment on column PZ_T_EFFECT.pz_apply_id
  is '配侦ID';
comment on column PZ_T_EFFECT.effect_type
  is '效能评定类型，0小领导评定 1大领导评定';
comment on column PZ_T_EFFECT.total
  is '总分';
comment on column PZ_T_EFFECT.type1
  is '比重：图侦';
comment on column PZ_T_EFFECT.type2
  is '比重：情报';
comment on column PZ_T_EFFECT.type3
  is '比重：技侦';
comment on column PZ_T_EFFECT.type4
  is '比重：网侦';
comment on column PZ_T_EFFECT.type5
  is '比重：刑警';
comment on column PZ_T_EFFECT.remark
  is '评语';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_EFFECT
  add constraint PK_PZ_EFFECT primary key (PZ_EFFECT_ID)
  using index 
  tablespace SOP_SP_IDX
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
