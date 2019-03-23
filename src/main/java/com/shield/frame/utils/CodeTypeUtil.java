package com.shield.frame.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.shield.frame.common.Constants;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.service.CodeValueService;

public class CodeTypeUtil {

    /**
     * 从缓存中获取码表数据(CodeValue)
     * 
     * @param typeId
     * @param domainName
     * @return
     */
    public static List<CodeValueDTO> getCodeValueByTypeId(String typeId, String domainName) {

        String key = Constants.CODETYPE_PREX + domainName + typeId;
        @SuppressWarnings("unchecked")
        List<CodeValueDTO> list = (List<CodeValueDTO>) OsCacheUtil.getFromCache(key);
        if (null == list) {
            CodeValueService codeValueService = (CodeValueService) SpringConfigLoadHelper
                .getBean(CodeValueService.class);
            list = codeValueService.getCodeValueByTypeId(typeId);
            if (list != null) {
                OsCacheUtil.putInCache(key, list);
            }
        }
        return list;
    }

    public static void delCodeCache(String typeId, String domainName) {
        String key = Constants.CODETYPE_PREX + domainName + typeId;
        SylogicCacheUtil.removeElement(key);
    }

    /**
     * <b>功能：判断某字典值在数据库是否存在</b><br>
     * <br>
     * 存在 false
     * 不存在 true
     * @param code
     * @param typeId
     * @return boolean
     **/
    public static boolean isCodeExist(String code, String typeId, String domainName) {
        boolean result = true;
        List<CodeValueDTO> list = getCodeValueByTypeId(typeId, domainName); // 获取字典集
        if (null != list && !list.isEmpty()) { // 判断是否存在
            for (int i = 0; i < list.size(); i++) {
                String codeValue = list.get(i).getCodeValue();
                if (StringUtils.isNotBlank(code)) {
                    if (codeValue.equals(code)) {
                        result = false; // 该类型未在字典删除
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据字典名称，检查是否有字典值
     * @param caseStatus	案件状态
     * @return	
     */
	public static String getCodeByDesc(String caseStatus, String typeId, String domainName) {
		// TODO Auto-generated method stub
		 String result = "";
	        List<CodeValueDTO> list = getCodeValueByTypeId(typeId, domainName); // 获取字典集
	        if (null != list && !list.isEmpty()) { // 判断是否存在
	            for (int i = 0; i < list.size(); i++) {
	                String valueDesc = list.get(i).getValueDesc();
	                if (StringUtils.isNotBlank(caseStatus)) {
	                    if (valueDesc.equals(caseStatus)) {
	                        result = list.get(i).getCodeValue(); //获取到指定名称的字典值
	                        break;
	                    }
	                }
	            }
	        }
		return result;
	}
}