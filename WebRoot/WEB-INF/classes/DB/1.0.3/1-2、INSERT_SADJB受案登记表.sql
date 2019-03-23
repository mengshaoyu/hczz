-- Create table
create table SOP_SADJB
(
  id          VARCHAR2(32) not null,
  isdel       NUMBER(1),
  dataversion CHAR(14),
  lrr_sfzh    VARCHAR2(50),
  lrsj        DATE,
  xgr_sfzh    VARCHAR2(50),
  xgsj        DATE,
  wszt        VARCHAR2(2),
  cbqy_bh     VARCHAR2(100) not null,
  ajbh        VARCHAR2(30),
  cbdw_bh     VARCHAR2(12) not null,
  cbdw_mc     VARCHAR2(200) not null,
  cbdw_jc     VARCHAR2(100),
  cbr_sfzh    VARCHAR2(200),
  cbr_xm      VARCHAR2(200),
  aybh        VARCHAR2(200),
  aymc        VARCHAR2(600),
  ajmc        VARCHAR2(1000),
  ajly        VARCHAR2(20),
  slsj        DATE,
  bar_xm      VARCHAR2(40),
  bar_xb      VARCHAR2(20),
  bar_csrq    DATE,
  bar_dw      VARCHAR2(60),
  bar_lxfs    VARCHAR2(30),
  bar_zz      VARCHAR2(200),
  bar_zjzl    VARCHAR2(100),
  bar_zjhm    VARCHAR2(20),
  basj        DATE,
  ysdw        VARCHAR2(200),
  ysrxm       VARCHAR2(200),
  ysdwlxdh    VARCHAR2(20),
  jyaq        VARCHAR2(4000),
  ysdwmc      VARCHAR2(200),
  ysr         VARCHAR2(32),
  slyj        VARCHAR2(100),
  wsh         VARCHAR2(5),
  jqbh        VARCHAR2(150),
  jbr         VARCHAR2(100),
  jbrxm       VARCHAR2(100),
  jbdd        VARCHAR2(2000),
  swdw        VARCHAR2(100),
  sayj1       VARCHAR2(150),
  sayj2       VARCHAR2(150),
  sayj3       VARCHAR2(150),
  sayj4       VARCHAR2(150),
  sayj5       VARCHAR2(150),
  qt          VARCHAR2(100),
  sfjszj      VARCHAR2(10),
  fasj        DATE,
  sayj        VARCHAR2(500),
  signname    VARCHAR2(200),
  glzjqdid    VARCHAR2(32),
  isytzjqd    CHAR(1),
  lrr_xm      VARCHAR2(500),
  fadd        VARCHAR2(200),
  zbdwjz      VARCHAR2(20),
  psignname   VARCHAR2(200),
  swdwbh      VARCHAR2(50),
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
