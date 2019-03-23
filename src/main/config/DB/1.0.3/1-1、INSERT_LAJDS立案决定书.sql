-- Create table
create table SOP_LAJDS
(
  id          VARCHAR2(32) not null,
  isdel       NUMBER(1),
  dataversion CHAR(14),
  lrr_sfzh    VARCHAR2(50),
  lrsj        DATE,
  xgr_sfzh    VARCHAR2(50),
  xgsj        DATE,
  wszt        VARCHAR2(2),
  cbqy_bh     VARCHAR2(100),
  ajbh        VARCHAR2(30),
  cbdw_bh     VARCHAR2(12),
  cbdw_mc     VARCHAR2(200),
  cbdw_jc     VARCHAR2(100),
  cbr_sfzh    VARCHAR2(200),
  cbr_xm      VARCHAR2(200),
  tfsj        DATE,
  wsh         VARCHAR2(5),
  ajmc        VARCHAR2(1000),
  rybh        VARCHAR2(50),
  ryxm        VARCHAR2(100),
  xyrxb       VARCHAR2(20),
  xyrcsrq     DATE,
  xyrzz       VARCHAR2(100),
  xyrdwzy     VARCHAR2(100),
  pzr_sfzh    VARCHAR2(50),
  pzr_xm      VARCHAR2(100),
  pzsj        DATE,
  fltk        VARCHAR2(100),
  tfr_xm      VARCHAR2(100),
  tfr_sfzh    VARCHAR2(30),
  signname    VARCHAR2(200),
  psignname   VARCHAR2(200),
  ywid        VARCHAR2(32),
  rksj        DATE
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
