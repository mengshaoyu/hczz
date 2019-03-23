--授权 dba用户

grant create public database link to sop;  

--创建dblink

create public database link gxpt connect to gxpt identified by haibo using '192.168.6.108/orcl';