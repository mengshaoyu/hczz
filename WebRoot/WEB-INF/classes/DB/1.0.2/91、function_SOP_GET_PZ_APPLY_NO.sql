create or replace function SOP_GET_PZ_APPLY_NO(TOMONTH IN VARCHAR2)
  return varchar2 is
  FunctionResult varchar2(15);
begin
  select lpad(count(1)+1, 3, 0)
    into FunctionResult
    from pz_t_apply t
   where to_char(t.create_dt, 'yyyymm') = TOMONTH;
  return('H321324' || TOMONTH || FunctionResult);
end SOP_GET_PZ_APPLY_NO;
/