-- Create table
create table SOP_OPS
(
  sop_ops_id       VARCHAR2(32) not null,
  sop_ops_content  VARCHAR2(1500),
  sop_ops_state    VARCHAR2(10),
  sop_ops_response VARCHAR2(1500),
  create_by        VARCHAR2(50) not null,
  create_dt        DATE not null,
  update_dt        DATE not null,
  update_by        VARCHAR2(50) not null,
  delete_by        VARCHAR2(50),
  act_by_type      VARCHAR2(50) not null
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SOP_OPS
  is '运维意见反馈表';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SOP_OPS
  add constraint PK_SOP_OPS primary key (SOP_OPS_ID)
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
