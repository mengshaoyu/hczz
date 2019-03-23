-- Create sequence 部门sequence,起始1000 
drop sequence SQ_AU_DEPT;

create sequence SQ_AU_DEPT
minvalue 1
maxvalue 9999999999
start with 1010000
increment by 1
cache 20;

-- Create sequence 角色sequence 
drop sequence SQ_AU_ROLE;

create sequence SQ_AU_ROLE
minvalue 1
maxvalue 9999999999999999
start with 2000
increment by 1
cache 20;

-- Create sequence 用户sequence 
drop sequence SQ_AU_USER;

create sequence SQ_AU_USER
minvalue 1
maxvalue 999999999999999
start with 1000
increment by 1
cache 20;

-- Create sequence 功能权限关联关系sequence 
drop sequence SQ_PERMISSION_FUNCTION;

create sequence SQ_PERMISSION_FUNCTION
minvalue 1
maxvalue 999999999999999
start with 1000
increment by 1
cache 20;
-- Create sequence 角色权限关联关系sequence 
drop sequence SQ_ROLE_PERMISSION;

create sequence SQ_ROLE_PERMISSION
minvalue 1
maxvalue 999999999999999
start with 1000
increment by 1
cache 20;

-- Create sequence 角色数据权限sequence 
drop sequence SQ_ROLE_DATAAUTH;

create sequence SQ_ROLE_DATAAUTH
minvalue 1
maxvalue 999999999999999
start with 1000
increment by 1
cache 20;

drop sequence ATTACH_TB_SEQ;
create sequence ATTACH_TB_SEQ
minvalue 1
maxvalue 999999999999999
start with 1000
increment by 1
cache 20;

drop sequence SQ_SY_AUDITLOG;
create sequence SQ_SY_AUDITLOG
minvalue 1
maxvalue 999999999999999999999
start with 1000
increment by 1
cache 20;


-- Create sequence 
drop sequence SQ_CODE_VALUE;
create sequence SQ_CODE_VALUE
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

-- Create sequence 
drop sequence SQ_USER_ROLE;
create sequence SQ_USER_ROLE
minvalue 1
maxvalue 999999999999999999999999999
start with 10000
increment by 1
cache 20;

-- Create ROTA sequence 
drop sequence SQ_ROTA;
create sequence SQ_ROTA
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

drop sequence SQ_APPLY;
create sequence SQ_APPLY
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- Create sequence 
drop sequence SQ_APPROVE;
create sequence SQ_APPROVE
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- Create sequence 
drop sequence SQ_TASK_LOG;
create sequence SQ_TASK_LOG
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- Create sequence 
drop sequence SQ_APPLY_EXP;
create sequence SQ_APPLY_EXP
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;