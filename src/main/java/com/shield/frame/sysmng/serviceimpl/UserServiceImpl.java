package com.shield.frame.sysmng.serviceimpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.base.persistence.impl.DeptmentMapperImpl;
import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.frame.sysmng.service.UserService;
import com.shield.frame.utils.CommonUtil;
import com.shield.frame.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperImpl userMapperImpl;
    @Autowired
    private DeptmentMapperImpl deptmentMapperImpl;
    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getList(HashMap<String, Object> map) {
        return this.userMapperImpl.getList(map);
    }

    /**
     * @description 分页查询
     * @param map
     * */
    public List<UserDTO> getPaginationList(HashMap<String, Object> map, int start, int limit) {
        List<UserDTO> list = null;
        list = userMapperImpl.getPaginationList(map, start, limit);
        return list;
    }

    public int save(UserDTO userManager) {
        return this.userMapperImpl.add(userManager);
    }

    public int del(String[] ids) {
        int count = 0;
        for (String id : ids) {
            this.userMapperImpl.delByPK(id);
        }
        return count;
    }

    public UserDTO getById(String id) {
        return this.userMapperImpl.getByPK(id);
    }

    public int updateByPK(UserDTO userManger) {
        return this.userMapperImpl.updateByPK(userManger);
    }

    /**
     * @description 获取总条数
     * */
    public int getCount(HashMap<String, Object> map) {
        return this.userMapperImpl.getCount(map);
    }

    /**
     * @description 停用/启用
     * @param map
     * */
    public int upuse(String[] ids, String useflag) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        for (String id : ids) {
            map.put("userId", id);
            map.put("useflag", useflag);
            int flag = this.userMapperImpl.upuse(map);
            if (flag > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * @description 密码重置
     * @param map
     * @throws Exception 
     * @throws Exception 
     * */
    public int resetPw(String[] ids, String repwd) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        repwd = MD5Util.getMd5(repwd);
        int count = 0;
        for (String id : ids) {
            map.put("userId", id);
            map.put("loginPwd", repwd);
            int flag = this.userMapperImpl.resetPw(map);
            if (flag > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * @description 通过id查询
     * @param account
     * */
    public UserDTO getByAccount(String account) {
        return this.userMapperImpl.getByAccount(account);
    }

    /**
     * @description 批量导入用户
     * @param resultList
     * */
    public HashMap<String, Object> addUsers(List<Map<String, String>> resultList,
        List<Map<String, String>> errorList) throws Exception {
        HashMap<String, Object> datamap = new HashMap<String, Object>();
        int count = 0;
        String[] regs = null;
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, String> map = resultList.get(i);
            if (!CommonUtil.isEmpty(map.get("用户名"))) {
                if (null == getByAccount(map.get("用户名"))) {//验证账号唯一性
                    regs = new String[] { "^([\u4E00-\uFA29]*[a-z]*[A-Z]*)+$" };
                    HashMap<String, Object> resultmap = this.dataCheck("用户姓名", "java.lang.String",
                        map.get("用户姓名"), 1, 30, regs, "and", "只能使用英文或汉字");//验证姓名
                    if ("true".equals("" + resultmap.get("result"))) {
                        resultmap = this.dataCheck("用户名", "java.lang.String", map.get("用户名"), 1,
                            30, null, null, null);//验证账号
                        if ("true".equals("" + resultmap.get("result"))) {
                            resultmap = this.dataCheck("警号", "java.lang.Long", map.get("警号"), 1,
                                30, null, null, null);//验证警号
                            if ("true".equals("" + resultmap.get("result"))) {
                                regs = new String[] { "^[1-9][0-9]{14}$|^[1-9]{1}[0-9]{16}[0-9xX]{1}$" };
                                resultmap = this.dataCheck("身份证号码", "java.lang.String",
                                    map.get("身份证号码"), 18, 18, regs, "or", "格式错误");//验证身份证号码
                                if ("true".equals("" + resultmap.get("result"))) {
                                    regs = new String[] { "^([1-9]{1})|([1-9]{1}[0-9]{1})|(100)$" };
                                    resultmap = this.dataCheck("年龄", "java.lang.Long",
                                        map.get("年龄"), 1, 3, regs, "and", "有效范围1-100的整数值");//验证年龄
                                    if ("true".equals("" + resultmap.get("result"))) {
                                        regs = new String[] { "^[0][1-9]{2,3}-[0-9]{5,10}$",
                                            "^[1-9]{1}[0-9]{5,8}$", "^[1][3,4,5,8][0-9]{9}$" };
                                        resultmap = this.dataCheck("联系方式", "java.lang.String",
                                            map.get("联系方式"), 0, 30, regs, "or", "号码格式错误");//验证联系方式
                                        if ("true".equals("" + resultmap.get("result"))) {
                                            regs = new String[] { "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$" };
                                            resultmap = this.dataCheck("邮箱地址", "java.lang.String",
                                                map.get("邮箱地址"), 0, 100, regs, "and", "格式错误");//验证邮箱地址
                                            if ("true".equals("" + resultmap.get("result"))) {
                                                resultmap = this.dataCheck("联系地址",
                                                    "java.lang.String", map.get("联系地址"), 1, 200,
                                                    null, null, null);//验证联系地址
                                                if ("true".equals("" + resultmap.get("result"))) {
                                                    if (!CommonUtil.isEmpty(map.get("性别"))
                                                        && ("男".equals(map.get("性别")) || "女"
                                                            .equals(map.get("性别")))) {
                                                        //判断部门
                                                        String deptid = deptmentMapperImpl
                                                            .getIdByName(CommonUtil.isEmpty(map
                                                                .get("所在部门")) ? "-" : map
                                                                .get("所在部门"));
                                                        if (!CommonUtil.isEmpty(map.get("所在部门"))
                                                            && !CommonUtil.isEmpty(deptid)) {
                                                            continue;
                                                        }
                                                        else {
                                                            map.put("errmsg", "未找到所在部门信息！");
                                                            map.put("direct", "第 " + (i + 1) + " 条");
                                                            errorList.add(map);
                                                        }
                                                    }
                                                    else {
                                                        map.put("errmsg", "性别只能输入男或女！");
                                                        map.put("direct", "第 " + (i + 1) + " 条");
                                                        errorList.add(map);
                                                    }
                                                }
                                                else {
                                                    map.put("errmsg", resultmap.get("resultmsg")
                                                        + "");
                                                    map.put("direct", "第 " + (i + 1) + " 条");
                                                    errorList.add(map);
                                                }
                                            }
                                            else {
                                                map.put("errmsg", resultmap.get("resultmsg") + "");
                                                map.put("direct", "第 " + (i + 1) + " 条");
                                                errorList.add(map);
                                            }
                                        }
                                        else {
                                            map.put("errmsg", resultmap.get("resultmsg") + "");
                                            map.put("direct", "第 " + (i + 1) + " 条");
                                            errorList.add(map);
                                        }
                                    }
                                    else {
                                        map.put("errmsg", resultmap.get("resultmsg") + "");
                                        map.put("direct", "第 " + (i + 1) + " 条");
                                        errorList.add(map);
                                    }
                                }
                                else {
                                    map.put("errmsg", resultmap.get("resultmsg") + "");
                                    map.put("direct", "第 " + (i + 1) + " 条");
                                    errorList.add(map);
                                }
                            }
                            else {
                                map.put("errmsg", resultmap.get("resultmsg") + "");
                                map.put("direct", "第 " + (i + 1) + " 条");
                                errorList.add(map);
                            }
                        }
                        else {
                            map.put("errmsg", resultmap.get("resultmsg") + "");
                            map.put("direct", "第 " + (i + 1) + " 条");
                            errorList.add(map);
                        }
                    }
                    else {
                        map.put("errmsg", resultmap.get("resultmsg") + "");
                        map.put("direct", "第 " + (i + 1) + " 条");
                        errorList.add(map);
                    }
                }
                else {
                    String errmsg = "用户名已存在！";
                    String direct = "第 " + (i + 1) + " 条";
                    map.put("errmsg", errmsg);
                    map.put("direct", direct);
                    errorList.add(map);
                }
            }
            else {
                String errmsg = "用户名为空！";
                String direct = "第 " + (i + 1) + " 条";
                map.put("errmsg", errmsg);
                map.put("direct", direct);
                errorList.add(map);
            }
        }
        if (null != errorList && errorList.size() < 1) {//如果数据正常，进行保存操作
            //String userId = LocalUtil.getUserId();
            //List<UserDTO> list = new ArrayList<UserDTO>();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = resultList.get(i);
                int num = dataDeal(map);
                //list.add( dataDeal(resultList.get(i),userId));
                if (num >= 0) {
                    count++;
                }
                else {
                    String errmsg = "系统异常！";
                    map.put("errmsg", errmsg);
                    errorList.add(map);
                }
            }
            //this.userMapper.addMore(list);
        }
        datamap.put("errorList", errorList);
        datamap.put("count", count);
        return datamap;
    }

    /**
     * @description 批量数据处理
     * */
    public int dataDeal(Map<String, String> map) throws Exception {
        UserDTO user = new UserDTO();
        user.setUserName(map.get("用户姓名"));
        user.setLoginAccount(map.get("用户名"));
        user.setLoginPwd(MD5Util.getMd5("888888"));
        user.setUserNo(map.get("警号"));
        user.setCurState("启用".equals(map.get("状态")) ? "0" : "1");
        user.setIdNo(map.get("身份证号码"));
        user.setAge(Short.parseShort((map.get("年龄"))));
        user.setGender("男".equals(map.get("性别")) ? "1" : "2");
        String deptid = CommonUtil.isEmpty(deptmentMapperImpl.getIdByName(map.get("所在部门"))) ? ""
            : deptmentMapperImpl.getIdByName(map.get("所在部门"));
        user.setDeptId(new BigDecimal(deptid));
        user.setMobilePhone(CommonUtil.isEmpty(map.get("联系方式")) ? "-" : map.get("联系方式"));
        user.setAddress(CommonUtil.isEmpty(map.get("联系地址")) ? "-" : map.get("联系地址"));
        user.setEmial(CommonUtil.isEmpty(map.get("邮箱地址")) ? "-" : map.get("邮箱地址"));
        user.setActByType("b");
        return this.userMapperImpl.add(user);
        //return user;
    }

    /**
     * @description 数据有效性验证  长度、格式
     * @param colu 列名
     * @param type 类型,例 ：java.lang.String
     * @param str 验证字符串
     * @param minLength 最小长度(如果字段不允许空 则1否则0)
     * @param maxLength 最大长度
     * @param regs 正则表达式组
     * @param 多个正则表达式间关系类型
     * @param 正则表达式验证提示信息
     * */
    public HashMap<String, Object> dataCheck(String colu, String type, String str, int minLength,
        int maxLength, String[] regs, String regType, String regmsg) {
        HashMap<String, Object> map = new HashMap<String, Object>();//返回验证结果集
        int length = (CommonUtil.isEmpty(str) ? "" : str).trim().length();
        if (length < minLength || length > maxLength) {//数据有效性 长度判断
            map.put("result", false);
            map.put("resultmsg", "" + colu + "长度不合法，有效长度("
                + ((minLength == maxLength) ? (minLength) : (minLength + "-" + maxLength)) + ")！");
        }
        else if ((regs == null || regs.length < 1) || RegCheck(str, regs, regType)) {
            if (type.indexOf("Date") > 0) {//验证日期格式
                try {
                    new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date(str));
                    map.put("result", true);
                    map.put("resultmsg", "");
                }
                catch (Exception e) {
                    map.put("result", false);
                    map.put("resultmsg", "" + colu + "不合法，有效格式(yyyy/MM/dd hh:mm:ss)！");
                }
            }
            else if (type.indexOf("Double") > 0) {//验证double格式
                try {
                    Double.parseDouble(str);
                    map.put("result", true);
                    map.put("resultmsg", "");
                }
                catch (Exception e) {
                    map.put("result", false);
                    map.put("resultmsg", "" + colu + "不合法，有效值为小数！");
                }
            }
            else if (type.indexOf("Long") > 0) {//验证整形格式
                try {
                    Long.parseLong(str);
                    map.put("result", true);
                    map.put("resultmsg", "");
                }
                catch (Exception e) {
                    map.put("result", false);
                    map.put("resultmsg", "" + colu + "不合法，有效值为整数！");
                }
            }
            else {
                map.put("result", true);
                map.put("resultmsg", "");
            }
        }
        else {
            map.put("result", false);
            map.put("resultmsg", "" + colu + "" + regmsg + "！");
        }
        return map;
    }

    /**
     * 多正则表达式验证
     * str  验证字符串
     * regs: 正则表达式集合
     * type: 多个正则表达式间关系类型  or  /  and
     * */
    public static boolean RegCheck(String str, String[] regs, String type) {
        boolean flag = true;
        if (CommonUtil.isEmpty(str)) {
            flag = true;
        }
        else {
            Pattern p = null;
            Matcher m = null;
            for (String reg : regs) {
                p = Pattern.compile(reg);
                m = p.matcher(str);
                flag = m.matches();
                if (m.matches() && type.equals("or")) {
                    break;
                }
                else if (!m.matches() && type.equals("and")) {
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public String getRoleStr(String userId) {
        List<Role> roles = userMapperImpl.getRolesById(userId);
        StringBuffer sb = new StringBuffer();
        for (Role role : roles) {
            sb.append(role.getRoleId().intValue());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public int blindPT(String ids, String userId) throws Exception {
        int updated = 0;
        if (userId != null && StringUtils.isNotBlank(userId.toString())) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();

            hashMap.put("ids", ids);
            hashMap.put("userId", userId);

            updated = this.userMapperImpl.blindPT(hashMap);
        }
        return updated;
    }
}
