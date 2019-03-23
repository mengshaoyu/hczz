create or replace function SOP_GET_PZ_APPLY_NO(TOMONTH IN VARCHAR2)
  return varchar2 is
  FunctionResult varchar2(15);
begin
  select '370203' || TOMONTH || lpad(count(1)+1, 3, 0)
    into FunctionResult
    from pz_t_apply t
   where to_char(t.create_dt, 'yyyymm') = TOMONTH;
  return(FunctionResult);
end SOP_GET_PZ_APPLY_NO;
/