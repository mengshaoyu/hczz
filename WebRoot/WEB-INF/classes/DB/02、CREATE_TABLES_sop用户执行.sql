drop table sop.au_t_user;
--用户信息表
create table sop.au_t_user(
  user_id  number(20) not null,
  username  nvarchar2(60) not null,
  gender  char(1) not null,
  age  number(3),
  login_account nvarchar2(50),
  login_pwd nvarchar2(50),
  id_no  nvarchar2(18),
  mobile_phone  nvarchar2(30),
  emial  nvarchar2(100),
  cur_state  char(1),
  login_amount  number(20),
  dept_id  number(20) not null,
  user_no  nvarchar2(30) not null,
  address  nvarchar2(200) not null,
  remark  nvarchar2 (400),
  police_type nvarchar2 (400),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null
  )tablespace sop_sp_data;
comment on table sop.au_t_user is '用户信息表';
comment on column sop.au_t_user.user_id is '主键';
comment on column sop.au_t_user.username is '真实姓名';
comment on column sop.au_t_user.gender is '性别，1:男，2:女';
comment on column sop.au_t_user.age is '年龄';
comment on column sop.au_t_user.mobile_phone is '手机号';
comment on column sop.au_t_user.emial is '电子邮箱';
comment on column sop.au_t_user.cur_state is '目前状态';
comment on column sop.au_t_user.login_amount is '登陆次数';
comment on column sop.au_t_user.dept_id is '部门';
comment on column sop.au_t_user.user_no is '警号';
comment on column sop.au_t_user.police_type is '警种，见code类型为1006';
comment on column sop.au_t_user.id_no is '身份证号';
comment on column sop.au_t_user.login_amount is '登录账号';
comment on column sop.au_t_user.login_pwd is '登录密码';
comment on column sop.au_t_user.address is '联系地址';
comment on column sop.au_t_user.remark is '备注';
comment on column sop.au_t_user.create_by is '创建人';
comment on column sop.au_t_user.create_dt is '创建时间';
comment on column sop.au_t_user.update_dt is '更新时间';
comment on column sop.au_t_user.update_by is '更新人';
comment on column sop.au_t_user.delete_by is '删除人';
comment on column sop.au_t_user.act_by_type is '操作用户类型';
alter table sop.au_t_user add constraint pk_au_user primary key(user_id) using index tablespace sop_sp_idx;

drop table sop.au_t_role;
--角色信息表
create table sop.au_t_role(
  role_id  number(20) not null,
  rolename  nvarchar2(50) not null,
  description  nvarchar2(200),
  domain_name  nvarchar2(10),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null
  )tablespace sop_sp_data;
comment on table sop.au_t_role is '角色信息表';
comment on column sop.au_t_role.role_id is '主键';
comment on column sop.au_t_role.rolename is '角色名';
comment on column sop.au_t_role.description is '角色描述';
comment on column sop.au_t_role.domain_name is '所属系统域';
comment on column sop.au_t_role.create_by is '创建人';
comment on column sop.au_t_role.create_dt is '创建时间';
comment on column sop.au_t_role.update_dt is '更新时间';
comment on column sop.au_t_role.update_by is '更新人';
comment on column sop.au_t_role.delete_by is '删除人';
comment on column sop.au_t_role.act_by_type is '操作用户类型';
alter table sop.au_t_role add constraint pk_au_role primary key(role_id) using index tablespace sop_sp_idx;

drop table sop.au_t_user_role;
--用户角色管理表
create table sop.au_t_user_role(
  rel_id  number(20) not null,
  user_id  number(20) not null,
  role_id  number(20) not null,
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
  tablespace sop_sp_data;
comment on table sop.au_t_user_role is '用户角色管理表';
comment on column sop.au_t_user_role.rel_id is '主键';
comment on column sop.au_t_user_role.user_id is '用户ID';
comment on column sop.au_t_user_role.role_id is '角色ID';
comment on column sop.au_t_user_role.create_by is '创建人';
comment on column sop.au_t_user_role.create_dt is '创建时间';
comment on column sop.au_t_user_role.update_dt is '更新时间';
comment on column sop.au_t_user_role.update_by is '更新人';
comment on column sop.au_t_user_role.delete_by is '删除人';
comment on column sop.au_t_user_role.act_by_type is '操作用户类型';
alter table sop.au_t_user_role add constraint pk_user_role primary key(rel_id) using index tablespace sop_sp_idx;

drop table sop.au_t_dept;
--部门信息表
create table sop.au_t_dept(
  dept_id  number(20) not null,
  dept_name  nvarchar2(100) not null,
  dept_no  nvarchar2(50),
  parent_id  number(20),
  address  nvarchar2(200),
  create_date  nvarchar2(20),
  leaf    NUMBER(1),
  SORT_NO  NUMBER(10), 
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
  tablespace sop_sp_data;
comment on table sop.au_t_dept is '部门信息表';
comment on column sop.au_t_dept.dept_id is '主键';
comment on column sop.au_t_dept.dept_name is '部门表名称';
comment on column sop.au_t_dept.dept_no is '部门编号';
comment on column sop.au_t_dept.parent_id is '上级部门';
comment on column sop.au_t_dept.address is '地址';
comment on column sop.au_t_dept.create_date is '部门创建日期';
comment on column sop.au_t_dept.leaf is '是否是叶子节点 0 否 1 是';
comment on column sop.au_t_dept.SORT_NO is '同级排序号';
comment on column sop.au_t_dept.create_by is '创建人';
comment on column sop.au_t_dept.create_dt is '创建时间';
comment on column sop.au_t_dept.update_dt is '更新时间';
comment on column sop.au_t_dept.update_by is '更新人';
comment on column sop.au_t_dept.delete_by is '删除人';
comment on column sop.au_t_dept.act_by_type is '操作用户类型';
alter table sop.au_t_dept add constraint pk_au_dept primary key(dept_id) using index tablespace sop_sp_idx;

drop table sop.au_t_menu;
--菜单信息表
create table sop.au_t_menu(
  menu_id  number(20) not null,
  menu_name  nvarchar2(100) not null,
  menu_action  nvarchar2(500),
  menu_parentid  number(20),
  menu_disabled  char(1),
  menu_sort_order  number(10),
  iconcls   NVARCHAR2(20),
  domain_name  nvarchar2(10),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
    tablespace sop_sp_data;
  comment on table sop.au_t_menu is '菜单信息表';
comment on column sop.au_t_menu.menu_id is '主键';
comment on column sop.au_t_menu.menu_name is '菜单名';
comment on column sop.au_t_menu.menu_action is '对应的url';
comment on column sop.au_t_menu.menu_parentid is '父菜单id';
comment on column sop.au_t_menu.menu_disabled is '菜单是否可用，0-启用，1-禁用';
comment on column sop.au_t_menu.menu_sort_order is '构成树形菜单时的排序字段';
comment on column sop.au_t_menu.iconcls is '菜单图标的样式 需与css文件名称一致';
comment on column sop.au_t_menu.domain_name is '所属系统域';
comment on column sop.au_t_menu.create_by is '创建人';
comment on column sop.au_t_menu.create_dt is '创建时间';
comment on column sop.au_t_menu.update_dt is '更新时间';
comment on column sop.au_t_menu.update_by is '更新人';
comment on column sop.au_t_menu.delete_by is '删除人';
comment on column sop.au_t_menu.act_by_type is '操作用户类型';
alter table sop.au_t_menu add constraint pk_au_menu primary key(menu_id) using index tablespace sop_sp_idx;

drop table sop.au_t_function;
--模块功能信息表
create table sop.au_t_function(
  function_id  number(19) not null,
  functionname  nvarchar2 (50) not null,
  url  nvarchar2 (200) not null,
  descpt  nvarchar2 (200),
  is_toolbar  char(1),
  toobar_title  nvarchar2(128),
  js_method  nvarchar2(30),
  ICON_CSS  nvarchar2(50),
  toolbar_order  number(10),
  open_method  char(1),
  hasaudata  char(1),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
      tablespace sop_sp_data;
comment on table sop.au_t_function is '模块功能信息表';
comment on column sop.au_t_function.function_id is '主键';
comment on column sop.au_t_function.functionname is '功能名称';
comment on column sop.au_t_function.url is 'URL';
comment on column sop.au_t_function.descpt is '描述';
comment on column sop.au_t_function.is_toolbar is '是否是toolbar,1:是，0:否';
comment on column sop.au_t_function.toobar_title is 'toolbar的提示信息';
comment on column sop.au_t_function.js_method is 'JS方法名字';
comment on column sop.au_t_function.ICON_CSS is '按钮样式';
comment on column sop.au_t_function.toolbar_order is 'toolbar顺序字段';
comment on column sop.au_t_function.open_method is '点击toolbar对应的打开方式，1：打开页签  2 : _openMethod  ';
comment on column sop.au_t_function.hasaudata is '是否数据权限过滤(待考虑是否需要)，Y\N';
comment on column sop.au_t_function.create_by is '创建人';
comment on column sop.au_t_function.create_dt is '创建时间';
comment on column sop.au_t_function.update_dt is '更新时间';
comment on column sop.au_t_function.update_by is '更新人';
comment on column sop.au_t_function.delete_by is '删除人';
comment on column sop.au_t_function.act_by_type is '操作用户类型';
alter table sop.au_t_function add constraint pk_au_function primary key(function_id) using index tablespace sop_sp_idx;

drop table sop.au_t_permission;
--权限信息表
create table sop.au_t_permission(
  permission_id  number(20) not null,
  authority_name  nvarchar2 (50) not null,
  parent_id  number(20),
  sort_order  number(10) not null,
  domain_name  nvarchar2(10),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
tablespace sop_sp_data; 
comment on table sop.au_t_permission is '权限信息表';
comment on column sop.au_t_permission.permission_id is '主键';
comment on column sop.au_t_permission.authority_name is '权限名称';
comment on column sop.au_t_permission.parent_id is '父节点';
comment on column sop.au_t_permission.sort_order is '构成树形时的排序字段';
comment on column sop.au_t_permission.domain_name is '所属系统域';
comment on column sop.au_t_permission.create_by is '创建人';
comment on column sop.au_t_permission.create_dt is '创建时间';
comment on column sop.au_t_permission.update_dt is '更新时间';
comment on column sop.au_t_permission.update_by is '更新人';
comment on column sop.au_t_permission.delete_by is '删除人';
comment on column sop.au_t_permission.act_by_type is '操作用户类型';
alter table sop.au_t_permission add constraint pk_au_permission primary key(permission_id) using index tablespace sop_sp_idx;


drop table sop.au_t_permission_function;
--功能权限关联关系表
create table sop.au_t_permission_function(
  pk  number(20) not null,
  permission_id  number(20) not null,
  function_id  number(20) not null,
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
  tablespace sop_sp_data; 
  comment on table sop.au_t_permission_function is '功能权限关联关系表';
comment on column sop.au_t_permission_function.pk is '主键';
comment on column sop.au_t_permission_function.permission_id is '权限ID';
comment on column sop.au_t_permission_function.function_id is '功能ID';
comment on column sop.au_t_permission_function.create_by is '创建人';
comment on column sop.au_t_permission_function.create_dt is '创建时间';
comment on column sop.au_t_permission_function.update_dt is '更新时间';
comment on column sop.au_t_permission_function.update_by is '更新人';
comment on column sop.au_t_permission_function.delete_by is '删除人';
comment on column sop.au_t_permission_function.act_by_type is '操作用户类型';
alter table sop.au_t_permission_function add constraint pk_permission_function primary key(pk) using index tablespace sop_sp_idx;



drop table sop.au_t_role_permission;
--角色权限关联关系表
create table sop.au_t_role_permission(
  pk  number(20) not null,
  permission_id  number(20) not null,
  role_id  number(20) not null,
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
  tablespace sop_sp_data; 
comment on table sop.au_t_role_permission is '角色权限关联关系表';
comment on column sop.au_t_role_permission.pk is '主键';
comment on column sop.au_t_role_permission.permission_id is '权限ID';
comment on column sop.au_t_role_permission.role_id is '角色ID';
comment on column sop.au_t_role_permission.create_by is '创建人';
comment on column sop.au_t_role_permission.create_dt is '创建时间';
comment on column sop.au_t_role_permission.update_dt is '更新时间';
comment on column sop.au_t_role_permission.update_by is '更新人';
comment on column sop.au_t_role_permission.delete_by is '删除人';
comment on column sop.au_t_role_permission.act_by_type is '操作用户类型';
alter table sop.au_t_role_permission add constraint pk_role_permission primary key(pk) using index tablespace sop_sp_idx;


drop table sop.au_t_role_dataauth;
--角色数据权限信息表
create table sop.au_t_role_dataauth(
  role_id  number(20) not null,
  auth_type  nvarchar2(20),
  sql_statment  nvarchar2(1024),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
    tablespace sop_sp_data; 
comment on table sop.au_t_role_dataauth is '角色数据权限信息表';
comment on column sop.au_t_role_dataauth.role_id is '角色ID';
comment on column sop.au_t_role_dataauth.auth_type is '数据权限认证方式，逗号间隔';
comment on column sop.au_t_role_dataauth.sql_statment is '对应Sql注入中的动态sql语句，冗余，考虑后续扩展';
comment on column sop.au_t_role_dataauth.create_by is '创建人';
comment on column sop.au_t_role_dataauth.create_dt is '创建时间';
comment on column sop.au_t_role_dataauth.update_dt is '更新时间';
comment on column sop.au_t_role_dataauth.update_by is '更新人';
comment on column sop.au_t_role_dataauth.delete_by is '删除人';
comment on column sop.au_t_role_dataauth.act_by_type is '操作用户类型';
alter table sop.au_t_role_dataauth add constraint pk_role_dataauth primary key(role_id) using index tablespace sop_sp_idx;

drop table sop.sy_t_code;

--系统码表信息表
create table sop.sy_t_code(
  type_id  number(20) not null,
  type_name  nvarchar2(50),
  type_desc  nvarchar2(500),
  edit_flag  char(1),
  domain_name  nvarchar2(10),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null
)    tablespace sop_sp_data; 
comment on table sop.sy_t_code is '系统码表信息表';
comment on column sop.sy_t_code.type_name is '码表名称';
comment on column sop.sy_t_code.type_id is '码表ID';
comment on column sop.sy_t_code.type_desc is '码表描述';
comment on column sop.sy_t_code.edit_flag is '是否可编辑，0-只读，1-可编辑';
comment on column sop.sy_t_code.domain_name is '所属系统域';
comment on column sop.sy_t_code.create_by is '创建人';
comment on column sop.sy_t_code.create_dt is '创建时间';
comment on column sop.sy_t_code.update_dt is '更新时间';
comment on column sop.sy_t_code.update_by is '更新人';
comment on column sop.sy_t_code.delete_by is '删除人';
comment on column sop.sy_t_code.act_by_type is '操作用户类型';
alter table sop.sy_t_code add constraint pk_sy_code primary key(type_id) using index tablespace sop_sp_idx;


drop table sop.sy_t_codevalue;
--系统码表值域信息表
create table sop.sy_t_codevalue(
  pk  number(20) not null,
  value_desc  nvarchar2(100),
  code_value  nvarchar2(20),
  type_id  number(20),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)  tablespace sop_sp_data; 
  
comment on table sop.sy_t_codevalue is '系统码表值域信息表';
comment on column sop.sy_t_codevalue.pk is '主键';
comment on column sop.sy_t_codevalue.value_desc is '码表值描述';
comment on column sop.sy_t_codevalue.code_value is '码表值';
comment on column sop.sy_t_codevalue.type_id is '所属码表TYPE';
comment on column sop.sy_t_codevalue.create_by is '创建人';
comment on column sop.sy_t_codevalue.create_dt is '创建时间';
comment on column sop.sy_t_codevalue.update_dt is '更新时间';
comment on column sop.sy_t_codevalue.update_by is '更新人';
comment on column sop.sy_t_codevalue.delete_by is '删除人';
comment on column sop.sy_t_codevalue.act_by_type is '操作用户类型';
alter table sop.sy_t_codevalue add constraint pk_code_value primary key(pk) using index tablespace sop_sp_idx;


drop table sop.sy_t_param;
--系统参数信息表
create table sop.sy_t_param(
  param_key  number(20) not null,
  sys_key  nvarchar2(50),
  sys_value  nvarchar2(200),
  edit_flag  char(1),
  DESCRIPTION  nvarchar2(100),
  domain_name  nvarchar2(10),
  create_by  nvarchar2(50) not null,
  create_dt  date not null,
  update_dt  date not null,
  update_by  nvarchar2(50) not null,
  delete_by  nvarchar2(50),
  act_by_type  nvarchar2(10) not null)
  tablespace sop_sp_data; 
comment on table sop.sy_t_param is '系统参数信息表';
comment on column sop.sy_t_param.param_key is '主键';
comment on column sop.sy_t_param.sys_key is '系统参数KEY';
comment on column sop.sy_t_param.sys_value is '系统参数VALUE';
comment on column sop.sy_t_param.edit_flag is '是否可编辑，0-只读，1-可编辑';
comment on column sop.sy_t_param.domain_name is '所属系统域';
comment on column sop.sy_t_param.DESCRIPTION is '参数描述';
comment on column sop.sy_t_param.create_by is '创建人';
comment on column sop.sy_t_param.create_dt is '创建时间';
comment on column sop.sy_t_param.update_dt is '更新时间';
comment on column sop.sy_t_param.update_by is '更新人';
comment on column sop.sy_t_param.delete_by is '删除人';
comment on column sop.sy_t_param.act_by_type is '操作用户类型';
alter table sop.sy_t_param add constraint pk_sy_param primary key(param_key) using index tablespace sop_sp_idx;

drop table sop.SY_T_AUDITLOG;
-- Create table
create table SY_T_AUDITLOG
(
  UUID         NVARCHAR2(32) not null,
  USER_ID      NUMBER(20),
  USER_IP      NVARCHAR2(50),
  FUNCTION     NVARCHAR2(100),
  OPER_TYPE    NVARCHAR2(2),
  OPER_CONTENT NVARCHAR2(200),
  RESOURCE_ID  NVARCHAR2(50),
  RESULT       CHAR(1),
  CREATE_BY    NVARCHAR2(50) not null,
  CREATE_DT    DATE not null,
  UPDATE_DT    DATE not null,
  UPDATE_BY    NVARCHAR2(50) not null,
  DELETE_BY    NVARCHAR2(50),
  ACT_BY_TYPE  NVARCHAR2(10) not null
)
tablespace sop_sp_data
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SY_T_AUDITLOG.UUID
  is '主键';
comment on column SY_T_AUDITLOG.USER_ID
  is '操作者';
comment on column SY_T_AUDITLOG.USER_IP
  is '操作IP';
comment on column SY_T_AUDITLOG.FUNCTION
  is '功能模块';
comment on column SY_T_AUDITLOG.OPER_TYPE
  is '操作类型:1-查询,2-查看,3-新增,4-编辑,5-删除,6-导出,9-其他';
comment on column SY_T_AUDITLOG.OPER_CONTENT
  is '操作内容';
comment on column SY_T_AUDITLOG.RESOURCE_ID
  is '被操作资源唯一标示';
comment on column SY_T_AUDITLOG.RESULT
  is '操作结果 0：成功 1：失败';
comment on column SY_T_AUDITLOG.CREATE_BY
  is '创建人';
comment on column SY_T_AUDITLOG.CREATE_DT
  is '创建时间';
comment on column SY_T_AUDITLOG.UPDATE_DT
  is '更新时间';
comment on column SY_T_AUDITLOG.UPDATE_BY
  is '更新人';
comment on column SY_T_AUDITLOG.DELETE_BY
  is '删除人';
comment on column SY_T_AUDITLOG.ACT_BY_TYPE
  is '操作用户类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SY_T_AUDITLOG
  add constraint PK_SY_SYSLOG primary key (UUID)
  using index 
  tablespace sop_sp_idx
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


drop table sop.FDI_T_ATTACH;

-- Create table 非公共框架表，用于公共框架中做的批量附件上传的例子
create table FDI_T_ATTACH
(
  ATT_ID      VARCHAR2(32) not null,
  BUS_ID      VARCHAR2(32),
  MODULE_TYPE VARCHAR2(50) not null,
  ATT_NAME    VARCHAR2(100) not null,
  ATT_RNAME   VARCHAR2(100),
  ATT_PATH    VARCHAR2(500) not null,
  CREATE_BY   VARCHAR2(50),
  CREATE_DT   DATE,
  UPDATE_DT   DATE,
  UPDATE_BY   VARCHAR2(50),
  DELETE_BY   VARCHAR2(50),
  ACT_BY_TYPE VARCHAR2(10)
)
tablespace sop_sp_data
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
comment on column FDI_T_ATTACH.MODULE_TYPE
  is '1来源证明  2受案登记表 3立案决定书 4呈请立案报告书 5配帧反馈';
-- Create/Recreate primary, unique and foreign key constraints 
alter table FDI_T_ATTACH
  add primary key (ATT_ID)
  using index 
  tablespace sop_sp_data
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
drop table CASE_INFO;  
-- Create table
create table CASE_INFO
(
  case_id             VARCHAR2(36) not null,
  case_no             VARCHAR2(36) not null,
  case_name           VARCHAR2(200),
  case_status         VARCHAR2(36),
  case_type           VARCHAR2(100),
  case_date           DATE,
  case_desc           VARCHAR2(1500),
  belong_area         VARCHAR2(100),
  incident_place      VARCHAR2(150),
  incident_date       DATE,
  incident_date_begin DATE,
  incident_date_end   DATE,
  punish_date         DATE,
  handle_unit         VARCHAR2(100),
  handle_people       VARCHAR2(36),
  handle_people_id    VARCHAR2(36),
  handle_tel          VARCHAR2(36),
  accept_date         DATE,
  accept_unit         VARCHAR2(100),
  undertake_unit      VARCHAR2(36),
  undertake_area      VARCHAR2(200),
  undertake_area_id   VARCHAR2(36),
  call_no             VARCHAR2(36),
  criminal_name       VARCHAR2(100),
  edit_flag           VARCHAR2(5) default '0' not null,
  create_by           VARCHAR2(50),
  create_dt           DATE,
  update_dt           DATE,
  update_by           VARCHAR2(50),
  delete_by           VARCHAR2(50),
  act_by_type         VARCHAR2(10),
  case_type_imp       VARCHAR2(10)
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
comment on table CASE_INFO
  is '案件信息';
-- Add comments to the columns 
comment on column CASE_INFO.case_id
  is '  ---案件信息主键:案件信息主键';
comment on column CASE_INFO.case_no
  is '  ---案件编号:案件编号';
comment on column CASE_INFO.case_name
  is '  ---案件名称:案件名称';
comment on column CASE_INFO.case_status
  is '  ---案件状态:案件状态';
comment on column CASE_INFO.case_type
  is '  ---案件类型:案件类型';
comment on column CASE_INFO.case_date
  is '  ---立案时间';
comment on column CASE_INFO.case_desc
  is '  ---案件描述:案件描述';
comment on column CASE_INFO.belong_area
  is '  ---所属辖区:所属辖区';
comment on column CASE_INFO.incident_place
  is '  ---案发地点:案发地点';
comment on column CASE_INFO.incident_date
  is '  ---案发时间';
comment on column CASE_INFO.incident_date_begin
  is '  ---案发时间起:案发时间起';
comment on column CASE_INFO.incident_date_end
  is '  ---案发时间止:案发时间止';
comment on column CASE_INFO.punish_date
  is '  ---处罚时间';
comment on column CASE_INFO.handle_unit
  is '  ---主办单位';
comment on column CASE_INFO.handle_people
  is '  ---主办人员';
comment on column CASE_INFO.handle_people_id
  is '  ---主办人员身份证号';
comment on column CASE_INFO.handle_tel
  is '  ---主办人电话';
comment on column CASE_INFO.accept_date
  is '  ---受理时间';
comment on column CASE_INFO.accept_unit
  is '  ---受理单位';
comment on column CASE_INFO.undertake_unit
  is '  ---承办单位';
comment on column CASE_INFO.undertake_area
  is '  ---承办区域';
comment on column CASE_INFO.undertake_area_id
  is '  ---承办区域编号';
comment on column CASE_INFO.call_no
  is '  ---警情编号';
comment on column CASE_INFO.criminal_name
  is '  ---嫌疑人姓名';
comment on column CASE_INFO.create_by
  is '  ---创建人:创建人';
comment on column CASE_INFO.create_dt
  is '  ---创建时间:创建时间';
comment on column CASE_INFO.update_dt
  is '  ---更新时间:更新时间';
comment on column CASE_INFO.update_by
  is '  ---更新人:更新人';
comment on column CASE_INFO.delete_by
  is '  ---删除人:删除人';
comment on column CASE_INFO.act_by_type
  is '  ---操作用户类型:操作用户类型';

-- Add/modify columns 
alter table CASE_INFO add jq_no VARCHAR2(36);
alter table CASE_INFO add lat_x number;
alter table CASE_INFO add lon_y number;
alter table CASE_INFO add case_ajly varchar2(36);
-- Add comments to the columns 
comment on column CASE_INFO.case_type
  is '  ---案件类型:案由';
comment on column CASE_INFO.case_type_imp
  is '案件类型';
comment on column CASE_INFO.jq_no
  is '警情编号';
comment on column CASE_INFO.lat_x
  is '维度';
comment on column CASE_INFO.lon_y
  is '精度';
comment on column CASE_INFO.case_ajly
  is '案件来源';
  
drop table PZ_T_APPLY;
-- Create table
create table PZ_T_APPLY
(
  pz_apply_id      VARCHAR2(36) not null,
  pz_apply_no      NVARCHAR2(50) not null,
  case_id          NVARCHAR2(36),
  pz_apply_type    VARCHAR2(5),
  pz_apply_grade   VARCHAR2(5),
  advance_end_time DATE,
  flow_id NVARCHAR2(64),
  landing          VARCHAR2(500),
  feedback_used    VARCHAR2(20),
  usability        VARCHAR2(20),
  feedback_aging   VARCHAR2(20),
  sign_aging       VARCHAR2(20),
  is_open          VARCHAR2(5) default '0',
  pz_main_accept   VARCHAR2(50),
  remark           NVARCHAR2(500),
  SUM_UP           VARCHAR2(1500),
  create_by        NVARCHAR2(50) not null,
  create_dt        DATE not null,
  update_dt        DATE not null,
  update_by        NVARCHAR2(50) not null,
  delete_by        NVARCHAR2(50),
  act_by_type      NVARCHAR2(10) not null
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
comment on table PZ_T_APPLY
  is '配侦申请表';
-- Add comments to the columns 
comment on column PZ_T_APPLY.pz_apply_id
  is '主键';
comment on column PZ_T_APPLY.pz_apply_no
  is '配侦申请编号';
comment on column PZ_T_APPLY.case_id
  is '案件ID';
comment on column PZ_T_APPLY.pz_apply_type
  is '任务类型';
comment on column PZ_T_APPLY.pz_apply_grade
  is '任务等级';
comment on column PZ_T_APPLY.advance_end_time
  is '预计有效结束时间';
comment on column PZ_T_APPLY.remark
  is '备注';
  comment on column pz_t_apply.flow_id 
is '申请所在流程ID';
comment on column PZ_T_APPLY.landing
  is '落地情况反馈';
comment on column PZ_T_APPLY.feedback_used
  is '反馈效能';
comment on column PZ_T_APPLY.usability
  is '使用效能';
comment on column PZ_T_APPLY.feedback_aging
  is '反馈时长';
comment on column PZ_T_APPLY.sign_aging
  is '签收时长';
omment on column PZ_T_APPLY.SUM_UP
  is '综述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_APPLY
  add constraint PK_PZ_APPLY primary key (PZ_APPLY_ID)
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


drop table PZ_T_FLOW;
-- Create table
create table PZ_T_FLOW
(
  FLOW_ID      NUMBER(20) not null,
  NEXT_ID      NUMBER(20),
  IS_START     VARCHAR2(5),
  START_ROLE   VARCHAR2(36),
  FLOW_DESC    NVARCHAR2(500),
  IS_SPECIFIED VARCHAR2(5),
  FLOW_TYPE    VARCHAR2(5) default '1' not null,
  REPARE_USERS    VARCHAR2(100) default null,
  REPARE_ROLES    VARCHAR2(100) default null,
  REMARK           NVARCHAR2(500),
  flow_status   VARCHAR2(5),
  flow_diff number(20),
  rebut_id number(20) default 0,
  CREATE_BY        NVARCHAR2(50) not null,
  CREATE_DT        DATE not null,
  UPDATE_DT        DATE not null,
  UPDATE_BY        NVARCHAR2(50) not null,
  DELETE_BY        NVARCHAR2(50),
  ACT_BY_TYPE      NVARCHAR2(10) not null
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table PZ_T_FLOW
  is '配侦流程表';
comment on column PZ_T_FLOW.FLOW_ID
  is '流程ID';
comment on column PZ_T_FLOW.NEXT_ID
  is '下级流程ID';
comment on column PZ_T_FLOW.IS_START
  is '该流程是否是开始节点 1是 其它不是';
comment on column PZ_T_FLOW.START_ROLE
  is '开启该流程的角色ID';
comment on column PZ_T_FLOW.FLOW_STATUS
  is '该流程对应配侦状态';
comment on column PZ_T_FLOW.FLOW_DESC
  is '流程简述';
comment on column PZ_T_FLOW.IS_SPECIFIED
  is '当前流程是否可指定下级流转人 1是 其它否';
comment on column PZ_T_FLOW.FLOW_TYPE
  is '流程类型 1并行流转（多个流传人一个审批通过即通过） 0串行流转（所有通过才通过）';
comment on column PZ_T_FLOW.REMARK
  is '备注';
comment on column pz_t_flow.rebut_id 
is '驳回流程ID';
comment on column pz_t_flow.flow_diff
is '该字段用于区分不同的流程';

-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_FLOW
  add constraint PK_PZ_FLOW primary key (FLOW_ID)
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

drop table PZ_T_APPROVE;
-- Create table
create table PZ_T_APPROVE
(
  PZ_APPROVE_ID      VARCHAR2(32) not null,
  USER_ID      NUMBER(20),
  ROLE_ID         NUMBER(20),
  APPLY_ID     VARCHAR2(32),
  FLOW_ID   NUMBER(20),
  RESULT  VARCHAR2(5) default '0',
  REASON    NVARCHAR2(500),
  CREATE_BY        NVARCHAR2(50) not null,
  CREATE_DT        DATE not null,
  UPDATE_DT        DATE not null,
  UPDATE_BY        NVARCHAR2(50) not null,
  DELETE_BY        NVARCHAR2(50),
  ACT_BY_TYPE      NVARCHAR2(10) not null
)
tablespace SOP_SP_DATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table PZ_T_APPROVE
  is '配侦审批记录表';
comment on column PZ_T_APPROVE.PZ_APPROVE_ID
  is '配侦审批主键';
comment on column PZ_T_APPROVE.USER_ID
  is '操作人ID';
comment on column PZ_T_APPROVE.APPLY_ID
  is '配侦申请主键';
comment on column PZ_T_APPROVE.FLOW_ID
  is '流程ID';
comment on column PZ_T_APPROVE.RESULT
  is '审批结果 0无结果，1通过，2未通过';
comment on column PZ_T_APPROVE.REASON
  is '审批意见';

-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_APPROVE
  add constraint PK_PZ_APPROVE primary key (PZ_APPROVE_ID)
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

DROP TABLE PZ_T_CLUE_INFO;

CREATE TABLE PZ_T_CLUE_INFO(CLUE_ID VARCHAR2(36) NOT NULL,  ---线索信息主键
                            CASE_ID VARCHAR2(36),  ---关联案件ID
                            CASE_NO VARCHAR2(36) NOT NULL,  ---关联案件编号
                            PZ_ID varchar2(36) not null,
                            CLUE_NAME VARCHAR2(200) NOT NULL,  ---线索名称
                            CLUE_TYPE NUMBER(2),  ---线索类型
                            CLUE_SOURCE VARCHAR2(1500),  ---线索来源描述
                            CLUE_DESC VARCHAR2(1500),  ---线索内容描述
			    CLUE_SUMUP VARCHAR2(1500),  ---线索综述
                            CLUE_TABLE_NAME VARCHAR2(200),  ---线索详情表
                            PZ_TYPE VARCHAR2(36),  ---业务类型
                            PZ_TYPE_DETAIL VARCHAR2(36),  ---业务小类型
                            Task_Glass_Id VARCHAR2(36),  ---任务等级
                            CREATE_BY VARCHAR2(50),  ---创建人
                            CREATE_DT DATE,  ---创建时间
                            UPDATE_DT DATE,  ---更新时间
                            UPDATE_BY VARCHAR2(50),  ---更新人
                            DELETE_BY VARCHAR2(50),  ---删除人
                            ACT_BY_TYPE VARCHAR2(10) , ---操作用户类型
							CLUE_AUXILIARY    NUMBER
                            )TABLESPACE SOP_SP_DATA;
ALTER TABLE PZ_T_CLUE_INFO ADD CONSTRAINT PY_T_CLUE_ID PRIMARY KEY(CLUE_ID) USING INDEX TABLESPACE SOP_SP_IDX;

COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_ID  IS '  ---线索信息主键:线索信息主键';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CASE_ID  IS '  ---关联案件ID:关联案件ID';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CASE_NO  IS '  ---关联案件编号:关联案件编号';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_NAME  IS '  ---线索名称:文件名称';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_TYPE  IS '  ---线索类型:线索类型（配侦申请：1，任务反馈：2，资源采集：3）';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_SOURCE  IS '  ---线索来源描述';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_DESC  IS '  ---线索内容描述:内容描述';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_SUMUP  IS '  ---线索综述';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_TABLE_NAME  IS '  ---线索详情表:对应线索所在表名';
COMMENT ON COLUMN PZ_T_CLUE_INFO.PZ_TYPE  IS '  ---业务类型:qbyp情报研判txzc图像侦查jzyw技侦业务wjyw网警业务';
COMMENT ON COLUMN PZ_T_CLUE_INFO.PZ_TYPE_DETAIL  IS '  ---业务小类型:根据业务类型不同对应业务小类型不同，具体参照合成作战要求';
COMMENT ON COLUMN PZ_T_CLUE_INFO.Task_Glass_Id  IS '  ---任务等级:Task_Glass_1：一级：反恐维稳等时效高的Task_Glass_2：二级：重大刑事案件Task_Glass_3,：三级：一般刑事案件';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CREATE_BY  IS '  ---创建人:创建人';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CREATE_DT  IS '  ---创建时间:创建时间';
COMMENT ON COLUMN PZ_T_CLUE_INFO.UPDATE_DT  IS '  ---更新时间:更新时间';
COMMENT ON COLUMN PZ_T_CLUE_INFO.UPDATE_BY  IS '  ---更新人:更新人';
COMMENT ON COLUMN PZ_T_CLUE_INFO.DELETE_BY  IS '  ---删除人:删除人';
COMMENT ON COLUMN PZ_T_CLUE_INFO.ACT_BY_TYPE  IS '  ---操作用户类型:操作用户类型';
COMMENT ON COLUMN PZ_T_CLUE_INFO.CLUE_AUXILIARY  IS '  办理人ID';
 

DROP TABLE PZ_T_RESULT;

CREATE TABLE PZ_T_RESULT(Result_ID VARCHAR2(36) NOT NULL,  ---反馈记录ID
                         CLUE_ID VARCHAR2(36) NOT NULL,  ---关联线索信息主键
                         CASE_ID VARCHAR2(36),  ---关联案件ID
                         CASE_NO VARCHAR2(36),  ---关联案件编号
                         PZ_ID VARCHAR2(36) NOT NULL,  ---关联配帧id
                         RESULT_DESC VARCHAR2(2000) NOT NULL,  ---反馈记录内容描述
                         RESULT_Date DATE NOT NULL,  ---反馈内容的登记时间
                         RESULT_BY VARCHAR2(400) NOT NULL,  ---反馈来源
                         is_files NUMBER(2),  ---反馈记录附件
                         receiver_BY VARCHAR2(50),  ---签收人,
			 RESULT_REMARK VARCHAR2(2000),  ---详情描述
			 IS_NORMAL_PZ_CLUE NUMBER(2) default 0,
                         CREATE_BY VARCHAR2(50),  ---创建人
                         CREATE_DT DATE,  ---创建时间
                         UPDATE_DT DATE,  ---更新时间
                         UPDATE_BY VARCHAR2(50),  ---更新人
                         DELETE_BY VARCHAR2(50),  ---删除人
                         ACT_BY_TYPE VARCHAR2(10)  ---操作用户类型
                         )TABLESPACE SOP_SP_DATA;
ALTER TABLE PZ_T_RESULT ADD CONSTRAINT PK_Result_ID PRIMARY KEY(Result_ID) USING INDEX TABLESPACE SOP_SP_IDX;

COMMENT ON COLUMN PZ_T_RESULT.Result_ID  IS '  ---反馈记录ID:反馈记录ID';
COMMENT ON COLUMN PZ_T_RESULT.CLUE_ID  IS '  ---关联线索信息主键:线索信息主键';
COMMENT ON COLUMN PZ_T_RESULT.CASE_ID  IS '  ---关联案件ID:关联案件ID';
COMMENT ON COLUMN PZ_T_RESULT.CASE_NO  IS '  ---关联案件编号:关联案件编号';
COMMENT ON COLUMN PZ_T_RESULT.PZ_ID  IS '  ---关联配帧id';
COMMENT ON COLUMN PZ_T_RESULT.RESULT_DESC  IS '  ---反馈记录内容描述';
COMMENT ON COLUMN PZ_T_RESULT.RESULT_Date  IS '  ---反馈内容的登记时间';
COMMENT ON COLUMN PZ_T_RESULT.RESULT_BY  IS '  ---反馈来源';
COMMENT ON COLUMN PZ_T_RESULT.RESULT_REMARK  IS '  ---详细描述';
COMMENT ON COLUMN PZ_T_RESULT.is_files  IS '  ---反馈记录附件:1有附件，0无附件';
COMMENT ON COLUMN PZ_T_RESULT.receiver_BY  IS '  ---签收人:签收人';
comment on column PZ_T_RESULT.is_normal_pz_clue is '是否同步线索库 0不同步';
COMMENT ON COLUMN PZ_T_RESULT.CREATE_BY  IS '  ---创建人:创建人';
COMMENT ON COLUMN PZ_T_RESULT.CREATE_DT  IS '  ---创建时间:创建时间';
COMMENT ON COLUMN PZ_T_RESULT.UPDATE_DT  IS '  ---更新时间:更新时间';
COMMENT ON COLUMN PZ_T_RESULT.UPDATE_BY  IS '  ---更新人:更新人';
COMMENT ON COLUMN PZ_T_RESULT.DELETE_BY  IS '  ---删除人:删除人';
COMMENT ON COLUMN PZ_T_RESULT.ACT_BY_TYPE  IS '  ---操作用户类型:操作用户类型';


DROP TABLE PZ_TASK_LOG;
create table PZ_TASK_LOG
(
  feature_person_id NUMBER(20) not null,
  clue_no_ref       VARCHAR2(36),
  case_no_ref       VARCHAR2(36),
  pz_apply_id       VARCHAR2(36) not null,
  flow_id           VARCHAR2(36) not null,
  task_status       VARCHAR2(20),
  next_by           VARCHAR2(36),
  task_desc         VARCHAR2(500),
  create_by         VARCHAR2(50) not null,
  create_dt         DATE not null,
  update_dt         DATE not null,
  update_by         VARCHAR2(50) not null,
  delete_by         NVARCHAR2(50),
  act_by_type       VARCHAR2(10) not null
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
comment on table PZ_TASK_LOG
  is '配侦日志记录表';
-- Add comments to the columns 
comment on column PZ_TASK_LOG.feature_person_id
  is '配侦记录主键';
comment on column PZ_TASK_LOG.clue_no_ref
  is '关联线索编号';
comment on column PZ_TASK_LOG.case_no_ref
  is '关联案件编号';
comment on column PZ_TASK_LOG.pz_apply_id
  is '关联配侦申请';
comment on column PZ_TASK_LOG.flow_id
  is '流程ID';
comment on column PZ_TASK_LOG.task_status
  is '任务状态';
comment on column PZ_TASK_LOG.next_by
  is '下一级流转人';
comment on column PZ_TASK_LOG.task_desc
  is '该任务简要描述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_TASK_LOG
  add constraint PY_T_FEATURE_PERSON_ID primary key (FEATURE_PERSON_ID)
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


  
drop table ALARM_INFO;  
-- Create table
create table ALARM_INFO
(
  ALARM_ID             VARCHAR2(36) not null,
  ALARM_NO             VARCHAR2(36) not null,
  ALARM_PEOPLE         VARCHAR2(50),
  ALARM_PHONE          VARCHAR2(36) not null,
  ALARM_TYPE           VARCHAR2(100) not null,
  ALARM_CONTENT        VARCHAR2(1500),
  UNIT_ID              VARCHAR2(36),
  UNINT_NAME           VARCHAR2(200),
  create_by           VARCHAR2(50),
  create_dt           DATE,
  update_dt           DATE,
  update_by           VARCHAR2(50),
  delete_by           VARCHAR2(50),
  act_by_type         VARCHAR2(10)
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
comment on table ALARM_INFO
  is '警情信息';
-- Add comments to the columns 
comment on column ALARM_INFO.ALARM_ID
  is '  ---警情信息主键:警情信息主键';
comment on column ALARM_INFO.ALARM_NO
  is '  ---警情编号:警情编号';
comment on column ALARM_INFO.ALARM_PEOPLE
  is '  ---报警人';
comment on column ALARM_INFO.ALARM_PHONE
  is '  ---报警电话';
comment on column ALARM_INFO.ALARM_TYPE
  is '  ---警情类型';
comment on column ALARM_INFO.ALARM_CONTENT
  is '  ---警情内容';
comment on column ALARM_INFO.UNIT_ID
  is '  ---管辖单位ID';
comment on column ALARM_INFO.UNINT_NAME
  is '  ---管辖单位名称';
comment on column ALARM_INFO.create_by
  is '  ---创建人:创建人';
comment on column ALARM_INFO.create_dt
  is '  ---创建时间:创建时间';
comment on column ALARM_INFO.update_dt
  is '  ---更新时间:更新时间';
comment on column ALARM_INFO.update_by
  is '  ---更新人:更新人';
comment on column ALARM_INFO.delete_by
  is '  ---删除人:删除人';
comment on column ALARM_INFO.act_by_type
  is '  ---操作用户类型:操作用户类型';



DROP TABLE PZ_T_COMMON_LANGUAGE;

CREATE TABLE PZ_T_COMMON_LANGUAGE(COMMON_LANGUAGE VARCHAR2(400) NOT NULL,  ---常用语
                                  LANG_TYPE CHAR(1) NOT NULL,  ---类型
                                  CREATE_BY VARCHAR2(50),  ---创建人
                                  CREATE_DT DATE,  ---创建时间
                                  UPDATE_DT DATE,  ---更新时间
                                  UPDATE_BY VARCHAR2(50),  ---更新人
                                  DELETE_BY VARCHAR2(50),  ---删除人
                                  ACT_BY_TYPE VARCHAR2(10)  ---操作用户类型
                                  )TABLESPACE SOP_SP_DATA;

COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.COMMON_LANGUAGE  IS '  ---常用语:活动id';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.LANG_TYPE  IS '  ---类型:类型 通过--1、退查--2';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.CREATE_BY  IS '  ---创建人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.CREATE_DT  IS '  ---创建时间';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.UPDATE_DT  IS '  ---更新时间';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.UPDATE_BY  IS '  ---更新人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.DELETE_BY  IS '  ---删除人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.ACT_BY_TYPE  IS '  ---操作用户类型';

  
drop table PZ_T_SERVICE_MANAGE;  
-- Create table
create table PZ_T_SERVICE_MANAGE
(
  UUID             VARCHAR2(36) not null,
  SERVICE_REQUEST             VARCHAR2(300) not null,
  FEEDBACK_TIME         VARCHAR2(36) not null,
  SERVICE_TYPE          VARCHAR2(36) not null,
  create_by           VARCHAR2(50),
  create_dt           DATE,
  update_dt           DATE,
  update_by           VARCHAR2(50),
  delete_by           VARCHAR2(50),
  act_by_type         VARCHAR2(10)
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
comment on table PZ_T_SERVICE_MANAGE
  is '业务请求配置';
-- Add comments to the columns 
comment on column PZ_T_SERVICE_MANAGE.UUID
  is '  ---主键';
comment on column PZ_T_SERVICE_MANAGE.SERVICE_REQUEST
  is '  ---业务请求';
comment on column PZ_T_SERVICE_MANAGE.FEEDBACK_TIME
  is '  ---反馈时效';
comment on column PZ_T_SERVICE_MANAGE.SERVICE_TYPE
  is '  ---业务请求类型';
comment on column ALARM_INFO.create_by
  is '  ---创建人:创建人';
comment on column PZ_T_SERVICE_MANAGE.create_dt
  is '  ---创建时间:创建时间';
comment on column PZ_T_SERVICE_MANAGE.update_dt
  is '  ---更新时间:更新时间';
comment on column PZ_T_SERVICE_MANAGE.update_by
  is '  ---更新人:更新人';
comment on column PZ_T_SERVICE_MANAGE.delete_by
  is '  ---删除人:删除人';
comment on column PZ_T_SERVICE_MANAGE.act_by_type
  is '  ---操作用户类型:操作用户类型';



DROP TABLE PZ_T_COMMON_LANGUAGE;

CREATE TABLE PZ_T_COMMON_LANGUAGE(COMMON_LANGUAGE VARCHAR2(400) NOT NULL,  ---常用语
                                  LANG_TYPE CHAR(1) NOT NULL,  ---类型
                                  CREATE_BY VARCHAR2(50),  ---创建人
                                  CREATE_DT DATE,  ---创建时间
                                  UPDATE_DT DATE,  ---更新时间
                                  UPDATE_BY VARCHAR2(50),  ---更新人
                                  DELETE_BY VARCHAR2(50),  ---删除人
                                  ACT_BY_TYPE VARCHAR2(10)  ---操作用户类型
                                  )TABLESPACE SOP_SP_DATA;

COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.COMMON_LANGUAGE  IS '  ---常用语:活动id';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.LANG_TYPE  IS '  ---类型:类型 通过--1、退查--2';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.CREATE_BY  IS '  ---创建人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.CREATE_DT  IS '  ---创建时间';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.UPDATE_DT  IS '  ---更新时间';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.UPDATE_BY  IS '  ---更新人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.DELETE_BY  IS '  ---删除人';
COMMENT ON COLUMN PZ_T_COMMON_LANGUAGE.ACT_BY_TYPE  IS '  ---操作用户类型';

alter table PZ_T_SERVICE_MANAGE
  add constraint PK_SERVICE_UUID primary key (UUID)
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

alter table PZ_TASK_LOG modify task_status VARCHAR2(20);

--已读未读
drop table pz_t_apply_exp;
-- Create table
create table PZ_T_APPLY_EXP
(
  ae_id    NUMBER(20) not null,
  apply_id VARCHAR2(36) not null,
  user_id  VARCHAR2(36),
  create_dt date
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
comment on table PZ_T_APPLY_EXP
  is '配侦扩展表';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PZ_T_APPLY_EXP
  add constraint PK_APPLY_EXP primary key (AE_ID)
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
------------------------  DB_CLUE_APPLY------------------------
DROP TABLE DB_CLUE_APPLY;


CREATE TABLE DB_CLUE_APPLY(UUID VARCHAR2(36) NOT NULL,  ---UUID
                           CASE_ID VARCHAR2(36),  ---关联案件ID
                           CLUE_ID VARCHAR2(36) NOT NULL,  ---关联线索信息
                           CLUE_CONTENT VARCHAR2(600) NOT NULL,  ---线索内容
                           UPLOADER VARCHAR2(50) NOT NULL,  ---上传人
                           WRITE_TIME DATE NOT NULL,  ---录入时间
                           CREATE_BY VARCHAR2(50),  ---创建人
                           CREATE_DT DATE,  ---创建时间
                           UPDATE_DT DATE,  ---更新时间
                           UPDATE_BY VARCHAR2(50),  ---更新人
                           DELETE_BY VARCHAR2(50),  ---删除人
                           ACT_BY_TYPE VARCHAR2(10)  ---操作用户类型
                           )TABLESPACE SOP_SP_DATA;
ALTER TABLE DB_CLUE_APPLY ADD CONSTRAINT PK_CLUE_CONTENT PRIMARY KEY(UUID) USING INDEX TABLESPACE SOP_SP_IDX;

COMMENT ON COLUMN DB_CLUE_APPLY.UUID  IS '  ---UUID:线索内容主键';
COMMENT ON COLUMN DB_CLUE_APPLY.CASE_ID  IS '  ---关联案件ID:关联案件ID';
COMMENT ON COLUMN DB_CLUE_APPLY.CLUE_ID  IS '  ---关联线索信息:关联线索信息';
COMMENT ON COLUMN DB_CLUE_APPLY.CLUE_CONTENT  IS '  ---线索内容:线索内容';
COMMENT ON COLUMN DB_CLUE_APPLY.UPLOADER  IS '  ---上传人:上传人';
COMMENT ON COLUMN DB_CLUE_APPLY.WRITE_TIME  IS '  ---录入时间:录入时间';
COMMENT ON COLUMN DB_CLUE_APPLY.CREATE_BY  IS '  ---创建人:创建人';
COMMENT ON COLUMN DB_CLUE_APPLY.CREATE_DT  IS '  ---创建时间:创建时间';
COMMENT ON COLUMN DB_CLUE_APPLY.UPDATE_DT  IS '  ---更新时间:更新时间';
COMMENT ON COLUMN DB_CLUE_APPLY.UPDATE_BY  IS '  ---更新人:更新人';
COMMENT ON COLUMN DB_CLUE_APPLY.DELETE_BY  IS '  ---删除人:删除人';
COMMENT ON COLUMN DB_CLUE_APPLY.ACT_BY_TYPE  IS '  ---操作用户类型:操作用户类型';




--------------------------------------------------------









