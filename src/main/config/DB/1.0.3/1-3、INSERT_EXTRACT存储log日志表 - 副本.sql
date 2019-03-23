-- Create table
create table EXTRACT_LOG
(
  start_date DATE,
  end_date   DATE,
  sqlerrm    NVARCHAR2(800),
  error_flag NVARCHAR2(800)
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EXTRACT_LOG
  is '闭环抽取存储日志表';
-- Add comments to the columns 
comment on column EXTRACT_LOG.start_date
  is '开始时间';
comment on column EXTRACT_LOG.end_date
  is '结束时间';
comment on column EXTRACT_LOG.sqlerrm
  is '错误描述';
comment on column EXTRACT_LOG.error_flag
  is '错误标志位';
