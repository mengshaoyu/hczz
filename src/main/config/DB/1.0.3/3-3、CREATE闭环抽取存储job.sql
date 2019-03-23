declare
  job number;
BEGIN
  DBMS_JOB.SUBMIT(  
        JOB => job,  /*自动生成JOB_ID*/  
        WHAT => 'sop_extract;',  /*需要执行的存储过程名称或SQL语句*/  
        NEXT_DATE => sysdate+3/(24*60),  /*初次执行时间-下一个3分钟*/  
        INTERVAL => 'sysdate+1/24' /*每小时执行一次*/
      );  
  commit;
end;