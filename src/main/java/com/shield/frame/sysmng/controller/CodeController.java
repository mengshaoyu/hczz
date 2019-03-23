package com.shield.frame.sysmng.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.Code;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.sysmng.dto.CodeDTO;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.frame.sysmng.service.CodeService;
import com.shield.frame.sysmng.service.CodeValueService;
import com.shield.frame.sysmng.service.UserService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.CodeTypeUtil;
import com.shield.frame.utils.OsCacheUtil;
import com.shield.frame.utils.SylogicCacheUtil;

/**
 * 字典表管理
 */
@Controller
@RequestMapping(value = "/code/*")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @Autowired
    CodeValueService codeValueService;

    @Autowired
    private UserService userService;

    /**
     * 初始化进入列表界面
     * 
     * @return
     */
    @RequestMapping(value = "init")
    public String init() {
        return "sysmng/codelist";
    }

    // @ResponseBody
    // @RequestMapping(value="getCodeList")
    // public List<CodeDTO> getCodeList(){
    // List<CodeDTO> list=codeService.getCodeList();
    //
    // return list;
    // }

    /**
     * 获取字典表分页列表
     * 
     */
    @ResponseBody
    @RequestMapping(value = "getPageCodeList")
    public HashMap<String, Object> getPageCodeList(String page, String rows) {
        // 当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        // 每页显示条数
        int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        // 开始条数
        HashMap<String, Object> datalist = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<CodeDTO> list = codeService.getPageCodeList(map, intPage, intRows);
        int total = this.codeService.getCount();
        datalist.put("rows", list);
        datalist.put("total", total);
        return datalist;
    }

    /**
     * 字典表编辑
     * 
     * @param typeId
     * @param domainName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCodeValueListByTypeId")
    public JSONArray getCodeValueListByTypeId(String typeId, String domainName) {

        // HashMap<String, Object> hmap=new HashMap<String, Object>();
        List<CodeValueDTO> list = new ArrayList<CodeValueDTO>();
        if (typeId.indexOf(",") != -1) {
            String[] ids = typeId.split(",");
            for (int i = 0; i < ids.length; i++) {
                if (StringUtils.isNotBlank(ids[i])) {
                    List<CodeValueDTO> partList = CodeTypeUtil.getCodeValueByTypeId(ids[i],
                        domainName);
                    list.addAll(partList);
                }
            }
        }
        else {
            list = CodeTypeUtil.getCodeValueByTypeId(typeId, domainName);
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject obj = null;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                obj = new JSONObject();
                CodeValueDTO cdto = list.get(i);
                obj.accumulate("pk", cdto.getPk());
                obj.accumulate("valueDesc", cdto.getValueDesc());
                obj.accumulate("codeValue", cdto.getCodeValue()==null?"":cdto.getCodeValue());
                jsonArray.add(obj);

            }
        }
        return jsonArray;
    }

    /**
     * 字典表编辑保存
     * 
     * @param typeId
     * @param typeName
     * @param typeDesc
     * @param editFlag
     * @param domainName
     * @param data
     */
    @ResponseBody
    @RequestMapping(value = "upd")
    public BaseVO upd(String typeId, String typeName, String typeDesc, String editFlag,
        String domainName, String data, String delData, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        JSONArray jsonArray = JSONArray.fromObject(data);
        JSONArray delJsonArray = JSONArray.fromObject(delData);
        List<CodeValueDTO> list = new ArrayList<CodeValueDTO>();
        List<CodeValueDTO> delList = new ArrayList<CodeValueDTO>();

        if (jsonArray != null) {

            for (int i = 0; i < jsonArray.size(); i++) {
                CodeValueDTO dto = new CodeValueDTO();
                JSONObject obj = jsonArray.getJSONObject(i);
                String valueDesc = obj.getString("valueDesc");
                String codeValue = obj.getString("codeValue");
                dto.setValueDesc(valueDesc);
                dto.setCodeValue(codeValue);
                dto.setActByType("b");
                dto.setTypeId(new BigDecimal(typeId));
                list.add(dto);

            }
        }
        if (delJsonArray != null) {
            for (int i = 0; i < delJsonArray.size(); i++) {
                CodeValueDTO delDto = new CodeValueDTO();
                JSONObject delObj = delJsonArray.getJSONObject(i);
                String delValueDesc = delObj.getString("valueDesc");
                String delCodeValue = delObj.getString("codeValue");
                delDto.setValueDesc(delValueDesc);
                delDto.setCodeValue(delCodeValue);
                delDto.setTypeId(new BigDecimal(typeId));
                delList.add(delDto);

            }
        }
        CodeDTO codeDTO = new CodeDTO();
        codeDTO.setTypeId(new BigDecimal(typeId));
        codeDTO.setTypeName(typeName);
        codeDTO.setTypeDesc(typeDesc);
        codeDTO.setDomainName(domainName);
        codeDTO.setActByType("b");
        if (editFlag.endsWith("可编辑")) {
            codeDTO.setEditFlag("1");
        }
        else {
            codeDTO.setEditFlag("0");
        }
        int sum = 0;
        sum = codeService.updateCodeandCodevalue(typeId, codeDTO, list, delList);
        if (sum > 0) {
            baseVO.setMsgCode("comm_001");
            AuditLogUtil.addLog(request, "字典表管理", "4", "字典表编辑", "", "0");
        }
        else {
            baseVO.setMsgCode("comm_003");
            AuditLogUtil.addLog(request, "字典表管理", "4", "字典表编辑", "", "1");
        }
        // 删除缓存
        String key = Constants.CODETYPE_PREX + domainName + typeId;
        SylogicCacheUtil.removeElement(key);
        return baseVO;

    }

    /**
     * 字典表删除
     * 
     * @param typeId
     */
    @ResponseBody
    @RequestMapping(value = "del")
    public BaseVO del(String typeId, HttpSession session, HttpServletRequest request) {
        int sum = 0;
        BaseVO baseVO = new BaseVO();
        String[] ss = typeId.split(",");

        sum = codeService.delCodeandCodeValueByTypeId(ss);
        if (sum > 0) {
            baseVO.setMsgCode("comm_001");
            AuditLogUtil.addLog(request, "字典表管理", "5", "字典表删除", typeId, "0");
        }
        else {
            baseVO.setMsgCode("comm_001");
            AuditLogUtil.addLog(request, "字典表管理", "5", "字典表删除", typeId, "1");
        }
        // 删除缓存
        String domainName = "0";
        String key = Constants.CODETYPE_PREX + domainName + typeId;
        SylogicCacheUtil.removeElement(key);
        return baseVO;
    }

    /**
     * 要编辑的字典是否已删除校验
     * 
     * @param typeId
     */
    @ResponseBody
    @RequestMapping(value = "checkCode")
    public Code checkCode(String typeId) {
        Code code = codeService.getCode(typeId);

        return code;

    }

    /**
     * @param typeId 类型
     * @param domainName 系统域
     * @param userId 查询人员中已经绑定的警种
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCodeTreeList")
    public List<TreeNode> getCodeTreeList(String typeId, String domainName, String userId) {
        UserDTO user = userService.getById(userId);
        String checkeds = user.getPoliceType() + "";
        //在缓存中获得字典值
        List<CodeValueDTO> list = CodeTypeUtil.getCodeValueByTypeId(typeId, domainName);

        //把数据变换成ztreeNode格式
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        if (list != null) {
            String pk = null;
            boolean checked = false;
            for (CodeValueDTO cdto : list) {
                pk = cdto.getCodeValue();
                checked = checkeds.indexOf(pk) != -1 ? true : false;
                TreeNode t = new TreeNode();
                t.setId(pk);
                t.setText(cdto.getValueDesc());
                t.setName(cdto.getValueDesc());
                t.setParentid("0");
                t.setpId("0");
                t.setChecked(checked);
                treeNodes.add(t);
            }
            TreeNode t = new TreeNode();
            t.setId("0");
            t.setText("警种类型");
            t.setName("警种类型");
            t.setParentid("");
            t.setpId("");
            t.setOpen(true);
            t.setChecked(checkeds.split(",").length == list.size());
            treeNodes.add(t);
        }
        return treeNodes;
    }
}
