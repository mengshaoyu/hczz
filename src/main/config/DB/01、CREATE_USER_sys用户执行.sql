CREATE TABLESPACE SOP_sp_data DATAFILE '/oradata/sop/SOP_SP_DATA.dbf'  
SIZE 100M AUTOEXTEND ON NEXT 1M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK OFF;


--PDCË÷Òý±í¿Õ¼ä
CREATE TABLESPACE SOP_sp_idx  DATAFILE '/oradata/sop/SOP_IDX_DATA.dbf'  
SIZE 10M AUTOEXTEND ON NEXT 1M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK OFF;  

-- Create the user 
create user sop
  identified by sop
  default tablespace sop_sp_data
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to sop;
grant resource to sop;
-- Grant/Revoke system privileges 
grant unlimited tablespace to sop;
grant create public database link to sop; 
grant create view to sop; 