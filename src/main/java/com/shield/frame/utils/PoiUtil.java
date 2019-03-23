package com.shield.frame.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.contrib.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;

import com.shield.frame.common.BaseTO;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.utils.Column.ENUM_TYPE;

public class PoiUtil {

    /**
     * 填充对象
     * 
     * @param object
     * @param cell
     * @param colum
     */
    public static void fillObject(BaseTO object, HSSFCell cell, Column colum) throws Exception {
        if (colum.getRelCol() != null) {// 有关联的字段,比如投资评级
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) {
                String cellValue = cell.getStringCellValue();

                boolean mappingFlg = false;
                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        mappingFlg = true;
                        break;
                    }
                }

                if (mappingFlg) {
                    BeanUtils.setProperty(object, colum.getCol(), cellValue);
                }
                else {
                    BeanUtils.setProperty(object, colum.getCol(), colum.getRelValueCode());
                    BeanUtils.setProperty(object, colum.getRelCol(), cellValue);
                }
            }
        }
        else {
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) { // 假设肯定为字符串
                String cellValue = cell.getStringCellValue();

                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        break;
                    }
                }
                BeanUtils.setProperty(object, colum.getCol(), cellValue);
            }
            else {
                if (colum.getType() == Column.ENUM_TYPE.DATE) {
                    BeanUtils.setProperty(object, colum.getCol(), cell.getDateCellValue());
                }
                else if (colum.getType() == Column.ENUM_TYPE.NUMBER) {
                    if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getNumericCellValue());
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
                else {
                    if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        double numericCellValue = cell.getNumericCellValue();
                        BeanUtils.setProperty(object, colum.getCol(), (int) numericCellValue);
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
            }
        }

    }

    /**
     * 填充对象
     * 
     * @param object
     * @param cell
     * @param colum
     *            注释：这里兼容xssf和hssf两种样式
     */
    public static void fillObject(BaseTO object, Cell cell, Column colum) throws Exception {
        if (colum.getRelCol() != null) {// 有关联的字段,比如投资评级
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) {
                String cellValue = cell.getStringCellValue();

                boolean mappingFlg = false;
                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        mappingFlg = true;
                        break;
                    }
                }

                if (mappingFlg) {
                    BeanUtils.setProperty(object, colum.getCol(), cellValue);
                }
                else {
                    BeanUtils.setProperty(object, colum.getCol(), colum.getRelValueCode());
                    BeanUtils.setProperty(object, colum.getRelCol(), cellValue);
                }
            }
        }
        else {
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) { // 假设肯定为字符串
                String cellValue = cell.getStringCellValue();

                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        break;
                    }
                }
                BeanUtils.setProperty(object, colum.getCol(), cellValue);
            }
            else {
                if (colum.getType() == Column.ENUM_TYPE.DATE) {
                    BeanUtils.setProperty(object, colum.getCol(), cell.getDateCellValue());
                }
                else if (colum.getType() == Column.ENUM_TYPE.NUMBER) {
                    if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getNumericCellValue());
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
                else {
                    if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        double numericCellValue = cell.getNumericCellValue();
                        BeanUtils.setProperty(object, colum.getCol(), (long) numericCellValue);
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
            }
        }

    }

    /**
     * 填充对象 当列对象中有date类型时，把时间格式转化为'yyyy-mm-dd'封装到实体中
     * 
     * @param object
     * @param cell
     * @param colum
     */
    public static void fillObjectFormateDate(BaseTO object, HSSFCell cell, Column colum)
        throws Exception {
        if (colum.getRelCol() != null) {// 有关联的字段,比如投资评级
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) {
                String cellValue = cell.getStringCellValue();

                boolean mappingFlg = false;
                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        mappingFlg = true;
                        break;
                    }
                }

                if (mappingFlg) {
                    BeanUtils.setProperty(object, colum.getCol(), cellValue);
                }
                else {
                    BeanUtils.setProperty(object, colum.getCol(), colum.getRelValueCode());
                    BeanUtils.setProperty(object, colum.getRelCol(), cellValue);
                }
            }
        }
        else {
            Map<String, String> possibleValues = colum.getPossibleValues();
            if (possibleValues.size() > 0) { // 假设肯定为字符串
                String cellValue = cell.getStringCellValue();

                for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
                    String value = entry.getValue();
                    if (value.equals(cellValue)) {
                        cellValue = entry.getKey();
                        break;
                    }
                }
                BeanUtils.setProperty(object, colum.getCol(), cellValue);
            }
            else {
                if (colum.getType() == Column.ENUM_TYPE.DATE) {
                    Date d = cell.getDateCellValue();
                    DateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
                    BeanUtils.setProperty(object, colum.getCol(), formater.format(d));
                }
                else if (colum.getType() == Column.ENUM_TYPE.NUMBER) {
                    if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getNumericCellValue());
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
                else {
                    if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        double numericCellValue = cell.getNumericCellValue();
                        BeanUtils.setProperty(object, colum.getCol(), (int) numericCellValue);
                    }
                    else {
                        BeanUtils.setProperty(object, colum.getCol(), cell.getStringCellValue());
                    }
                }
            }
        }

    }

    /**
     * 因为重载的问题，不能传入为NULL的Date对象
     * 
     * @param cell
     * @param date
     */
    public static void setDateCellValue(HSSFCell cell, Date date) {
        if (date != null) {
            cell.setCellValue(date);
        }
        else {
            cell.setCellValue("");
        }
    }

    /**
     * 设定Date类型属性的格式
     * 
     * @param wb
     * @param cell
     * @param enumType
     */
    public static void modifyCellType(HSSFWorkbook wb, HSSFCell cell, Column.ENUM_TYPE enumType) {
        if (Column.ENUM_TYPE.DATE == enumType) {
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.cloneStyleFrom(cell.getCellStyle());
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 合并单元格后加边框
     * 
     * @param sheet
     * @param region
     * @param cs
     */
    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress region, HSSFCellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 返回Cell的字符串内容 日期格式为'yyyy-mm-dd'
     * 
     * @param cell
     * @return
     */
    public static String getCellStr(HSSFCell cell) {
        String cellStr = null;

        if (cell == null) {
            return cellStr;
        }

        int cellType = cell.getCellType();
        if (HSSFCell.CELL_TYPE_STRING == cellType) {
            cellStr = cell.getStringCellValue();
        }
        else if (HSSFCell.CELL_TYPE_NUMERIC == cellType) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                cellStr = formater.format(d);

            }
            else
                cellStr = String.valueOf(cell.getNumericCellValue());
        }

        return cellStr;
    }

    /**
     * 返回Cell的字符串内容 注释：这里兼容xssf，hssf两种样式 日期格式为'yyyy-mm-dd'
     * 
     * @param cell
     * @return
     */
    public static String getCellStr(Cell cell) {
        String cellStr = null;

        if (cell == null) {
            return cellStr;
        }

        int cellType = cell.getCellType();
        if (Cell.CELL_TYPE_STRING == cellType) {
            cellStr = cell.getStringCellValue();
        }
        else if (Cell.CELL_TYPE_NUMERIC == cellType) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                cellStr = formater.format(d);

            }
            else
                cellStr = String.valueOf(cell.getNumericCellValue());
        }

        return cellStr;
    }

    public static HSSFFont getHSSFFont(HSSFWorkbook wb) {
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体 ");
        font.setBoldweight((short) 700);
        return font;
    }

    public static HSSFFont getHSSFFontBySize(HSSFWorkbook wb, int size) {
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) size);
        font.setFontName("宋体 ");
        font.setBoldweight((short) 700);
        return font;
    }

    /**
     * 遍历实体类属性 组成Map：key = 属性名，value=属性值
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getObjPropertyMap(Object model) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        List<Field> fieldList = new ArrayList<Field>();
        Collections.addAll(fieldList, field);
        if (null != model.getClass().getSuperclass()) {
            getParentClassFields(fieldList, model.getClass().getSuperclass());
        }

        for (int j = 0; j < fieldList.size(); j++) {
            String name = fieldList.get(j).getName();
            if (name.equals("serialVersionUID")) {
                continue;
            }
            String funName = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = fieldList.get(j).getGenericType().toString();
            if (type.indexOf("class com.shield") != -1) { // 如果type是类类型，则前面包含"class
                // "，后面跟类名
                Method m = model.getClass().getMethod("get" + funName);
                Object value = m.invoke(model); // 调用getter方法获取属性值
                if (value != null) {
                    map.putAll(getObjPropertyMap(value));
                }
            }
            else if (type.equals("class java.lang.String")) { // 如果type是类String型，则前面包含"class
                // "，后面跟类名
                Method m = model.getClass().getMethod("get" + funName);
                String value = (String) m.invoke(model); // 调用getter方法获取属性值
                if (value != null) {
                    map.put(name, value);
                }
            }
            else if (type.equals("class java.lang.Integer")) {
                Method m = model.getClass().getMethod("get" + funName);
                Integer value = (Integer) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            }
            else if (type.equals("class java.lang.Short")) {
                Method m = model.getClass().getMethod("get" + funName);
                Short value = (Short) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            }
            else if (type.equals("class java.lang.Double")) {
                Method m = model.getClass().getMethod("get" + funName);
                Double value = (Double) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            }
            else if (type.equals("class java.lang.Boolean")) {
                Method m = model.getClass().getMethod("get" + funName);
                Boolean value = (Boolean) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            }
            else if (type.equals("class java.util.Date")) {
                Method m = model.getClass().getMethod("get" + funName);
                Date value = (Date) m.invoke(model);
                if (value != null) {
                    String valStr = com.shield.frame.utils.DateUtil.getDateToStr(value,
                        "yyyy-MM-dd");
                    // String valStr =
                    // DateFormat.getDateInstance(DateFormat.DEFAULT).format(value);
                    map.put(name, valStr);
                }
            }
            else if (type.equals("class java.math.BigDecimal")) {
                Method m = model.getClass().getMethod("get" + funName);
                BigDecimal value = (BigDecimal) m.invoke(model);
                if (value != null) {
                    map.put(name, value.toString());
                }
            }
            else if (type.equals("int")) {
                Method m = model.getClass().getMethod("get" + funName);
                int value = (int) m.invoke(model);
                map.put(name, value);
            }
            else if (type.equals("short")) {
                Method m = model.getClass().getMethod("get" + funName);
                int value = (int) m.invoke(model);
                map.put(name, value);
            }
            else if (type.equals("byte")) {
                Method m = model.getClass().getMethod("get" + funName);
                int value = (int) m.invoke(model);
                map.put(name, value);
            }
            else if (type.equals("long")) {
                Method m = model.getClass().getMethod("get" + funName);
                int value = (int) m.invoke(model);
                map.put(name, value);
            }

        }
        return map;
    }

    private static List<Field> getParentClassFields(List<Field> lists, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Collections.addAll(lists, fields);
        if (clazz.getSuperclass() == null) {
            return lists;
        }
        getParentClassFields(lists, clazz.getSuperclass());
        return lists;
    }

    /**
     * 导出数据生成Excel
     * 
     * @param List,要导出的数据LIST集合对象
     * @param Column[]
     *            导出生成的Excel的列定义
     * @param sheetTitle
     *            导出生成的Excel sheet名称
     * @param print_layout
     *            文件打印格式 true 横向 false 纵向
     * @return HSSFWorkbook
     */
    @SuppressWarnings("unchecked")
    public static HSSFWorkbook getExcel(List items, Column[] user_exp, String sheetTitle,
        boolean print_layout) {
        HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象
        HSSFSheet sheet = wb.createSheet(sheetTitle);// 工作表对象
        sheet = modifyHSSFSheet(sheet, user_exp);
        sheet.getPrintSetup().setLandscape(print_layout);
        wb = createFundPoolArea(wb, sheet, items, user_exp, sheetTitle);
        return wb;
    }

    /**
     * <b>功能：分Sheet页导出</b><br>
     * 先根据类型将所有的数据分组存放，然后根据每个组生成一个sheet页 <br>
     * 
     * @param items
     * @param user_exp
     * @param sheetTitle
     * @param print_layout
     * @param typeProp
     *            类别属性
     * @param allProp
     *            所有类别属性
     * @return HSSFWorkbook
     **/
    public static HSSFWorkbook getSplitSheetExcel(List items, String typeProp,
        List<CodeValueDTO> allProp, Column[] user_exp, String sheetTitle, boolean print_layout) {
        List list = new ArrayList<>(); // 最外面的容器
        Map<String, List> resultMap = new HashMap<String, List>(); // 最终要的结果
        // 根据类别分组
        for (int i = 0; i < items.size(); i++) {
            Object obj = items.get(i);
            if (null != obj) {
                String val = getValByType(obj, typeProp);
                if (StringUtils.isNotBlank(val)) {
                    if (resultMap.containsKey(val)) {
                        resultMap.get(val).add(obj);
                    }
                    else {
                        List itemList = new ArrayList<>();
                        itemList.add(obj);
                        resultMap.put(val, itemList);
                    }
                }
            }
        }

        HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象

        for (int i = 0; i < allProp.size(); i++) {
            CodeValueDTO code = allProp.get(i);
            HSSFSheet sheet = wb.createSheet(code.getValueDesc());// 工作表对象
            sheet = modifyHSSFSheet(sheet, user_exp);
            sheet.getPrintSetup().setLandscape(print_layout);
            wb = createFundPoolArea(wb, sheet, resultMap.get(code.getCodeValue()), user_exp,
                code.getValueDesc());
        }

        return wb;
    }

    public static String getValByType(Object obj, String field) {
        String value = "";
        String funName = field.substring(0, 1).toUpperCase() + field.substring(1);
        try {
            Method m = obj.getClass().getMethod("get" + funName);
            value = m.invoke(obj).toString(); // 调用getter方法获取属性值
        }
        catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
            | SecurityException | NoSuchMethodException e) {
            value = "";
            e.printStackTrace();
        }
        return value;
    }

    /**
     * sheet页面属性设定
     */
    private static HSSFSheet modifyHSSFSheet(HSSFSheet sheet, Column[] user_exp) {
        // 列宽调整
        sheet.setDefaultColumnWidth((short) 15);
        sheet.setColumnWidth((short) 0, (short) 3000);
        for (int i = 0; i < user_exp.length; i++) {
            sheet.setColumnWidth((short) (i + 1), Short.parseShort((user_exp[i].getWidth() + "")));
        }
        return sheet;
    }

    /**
     * 根据列定义类型,自动处理样式 Column.ENUM_TYPE.VARCHAR2 CELL-STYL对应 getStyleContent文件居左
     * Column.ENUM_TYPE.NUMBER CELL-STYL对应 getStyleFunds 数字， 千分位以,显示 居右
     * Column.ENUM_TYPE.DATE CELL-STYL对应 getMiddle 日期居中显示
     */
    public static HSSFCellStyle getHSSFCellStyleByType(ENUM_TYPE type,
        ReportCellStyle reportCellStyle) {

        if (type.equals(Column.ENUM_TYPE.NUMBER)) {
            return reportCellStyle.getStyleFunds();
        }

        if (type.equals(Column.ENUM_TYPE.DATE)) {
            return reportCellStyle.getMiddle();
        }

        return reportCellStyle.getStyleContent();
    }

    private static HSSFWorkbook createFundPoolArea(HSSFWorkbook wb, HSSFSheet sheet, List items,
        Column[] user_exp, String title) {
        try {
            // 生成数据
            ReportCellStyle reportCellStyle = new ReportCellStyle(wb);
            // 设定 列宽
            // sheet.setColumnWidth(14, 6000);// "最近服务日期"列宽

            int fundStartRowNo = 0;
            // 设置头部总标题-title
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (user_exp.length)));
            HSSFRow fundStartRow = sheet.createRow(fundStartRowNo);
            fundStartRow.setHeight((short) 400);
            HSSFCell cellt = fundStartRow.createCell((short) 0);
            cellt.setCellStyle(reportCellStyle.getStyleTop());
            cellt.setCellValue(title);

            int recordLength = (items == null) ? 20 : items.size();
            // 设置头部标题列-column
            fundStartRowNo++;// 定位到第二行
            fundStartRow = sheet.createRow(fundStartRowNo);
            HSSFCell cell0 = fundStartRow.createCell((short) 0);
            cell0.setCellValue(new HSSFRichTextString("序号"));
            cell0.setCellStyle(reportCellStyle.getStyleTop2());
            for (int i = 1; i <= user_exp.length; i++) {
                Column colum = user_exp[i - 1];

                HSSFCell cellI = fundStartRow.createCell((short) i);
                cellI.setCellValue(new HSSFRichTextString(colum.getName()));
                cellI.setCellStyle(reportCellStyle.getStyleTop2());
            }
            // 写入数据
            for (int i = 1; i <= recordLength; i++) {
                HSSFRow fundRow = sheet.createRow(fundStartRowNo + i);
                Object userTo = null;

                if (items != null) {
                    userTo = items.get(i - 1);
                }

                if (null != userTo) {
                    HSSFCell stkCell0 = fundRow.createCell((short) 0);
                    stkCell0.setCellValue(i);
                    stkCell0.setCellStyle(reportCellStyle.getStyleLeft());

                    Map<String, Object> map = PoiUtil.getObjPropertyMap(userTo);
                    for (int j = 1; j <= user_exp.length; j++) {
                        Column colum = user_exp[j - 1];

                        HSSFCell cell = fundRow.createCell((short) j);
                        cell.setCellStyle(getHSSFCellStyleByType(colum.getType(), reportCellStyle));
                        if (map.get(colum.getCol()) != null) {
                            if (colum.getPossibleValues().size() > 0) {
                                cell.setCellValue(new HSSFRichTextString((String) (colum
                                    .getPossibleValues().get((String) map.get(colum.getCol())))));
                            }
                            else {
                                String value = String.valueOf(map.get(colum.getCol()));
                                cell.setCellValue(new HSSFRichTextString(
                                    CommonUtil.isEmpty(value) ? "-" : value));
                            }
                        }
                        else {
                            cell.setCellValue(new HSSFRichTextString("-"));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    // 创建空的excel文件
    public static HSSFWorkbook getExcelNew(Column[] user_exp, String sheetTitle) {
        HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象
        HSSFSheet sheet = wb.createSheet(sheetTitle);// 工作表对象
        sheet = modifyHSSFSheet(sheet, user_exp);
        wb = createFundPoolAreaNew(wb, sheet, user_exp);
        return wb;
    }

    private static HSSFWorkbook createFundPoolAreaNew(HSSFWorkbook wb, HSSFSheet sheet,
        Column[] user_exp) {
        try {
            // 生成数据
            ReportCellStyle reportCellStyle = new ReportCellStyle(wb);
            // 设定 列宽
            // sheet.setColumnWidth(14, 6000);// "最近服务日期"列宽

            int fundStartRowNo = 0;

            HSSFRow fundStartRow = sheet.createRow(fundStartRowNo);

            HSSFCell cell0 = fundStartRow.createCell((short) 0);
            cell0.setCellValue(new HSSFRichTextString("序号"));
            cell0.setCellStyle(reportCellStyle.getStyleTop2());
            for (int i = 1; i <= user_exp.length; i++) {
                Column colum = user_exp[i - 1];
                HSSFCell cellI = fundStartRow.createCell((short) i);
                cellI.setCellValue(new HSSFRichTextString(colum.getName()));
                cellI.setCellStyle(reportCellStyle.getStyleTop2());
                colum = null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    @SuppressWarnings("unchecked")
    public static HSSFWorkbook getExcelForPage(HSSFWorkbook wb, List items, Column[] user_exp,
        int pageNeeded, int pageSize) {
        try {
            // 生成数据
            ReportCellStyle reportCellStyle = new ReportCellStyle(wb);
            // 设定 列宽
            // sheet.setColumnWidth(14, 6000);// "最近服务日期"列宽

            int fundStartRowNo = (pageNeeded - 1) * pageSize;
            HSSFSheet sheet = wb.getSheetAt(0);// 工作表对象
            int recordLength = (items == null) ? 20 : items.size();

            for (int i = 1; i <= recordLength; i++) {
                HSSFRow fundRow = sheet.createRow(fundStartRowNo + i);
                HSSFCell stkCell0 = fundRow.createCell((short) 0);
                stkCell0.setCellValue(fundStartRowNo + i);
                stkCell0.setCellStyle(reportCellStyle.getStyleLeft());

                Object userTo = null;

                if (items != null) {
                    userTo = items.get(i - 1);
                }

                Map<String, Object> map = PoiUtil.getObjPropertyMap(userTo);
                for (int j = 1; j <= user_exp.length; j++) {
                    Column colum = user_exp[j - 1];
                    HSSFCell cell = fundRow.createCell((short) j);
                    cell.setCellStyle(getHSSFCellStyleByType(colum.getType(), reportCellStyle));
                    if (map.get(colum.getCol()) != null) {
                        if (colum.getPossibleValues().size() > 0) {
                            cell.setCellValue(new HSSFRichTextString((String) (colum
                                .getPossibleValues().get((String) map.get(colum.getCol())))));
                        }
                        else {
                            cell.setCellValue(new HSSFRichTextString((String) map.get(colum
                                .getCol())));
                        }

                    }
                    cell = null;
                }
                fundRow = null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * excel数据导入解析程序
     * @param InputStream
     *            导入Execel文件流
     * @return Map<String,List<Map<String,String>>>
     */
    public static Map<String, List<Map<String, String>>> imp(InputStream is) throws Exception {
        Map<String, List<Map<String, String>>> resultMap = new HashMap<String, List<Map<String, String>>>();
        List resultList = new ArrayList();// 正确数据集合
        List errorList = new ArrayList();// 异常数据集合
        List messageList = new ArrayList();// 错误信息列表

        HSSFWorkbook wb = new HSSFWorkbook(is);

        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow titleRow = sheet.getRow(1);// 获取标题行

        int length = sheet.getPhysicalNumberOfRows();
        Map<String, String> dataMap = null;// 封装数据
        for (int i = 2; i < length; i++) {// 从第三行开始读取数据
            HSSFRow row = sheet.getRow(i);// 获取每一行
            if (null != row) {
                dataMap = getRowDataMap(titleRow, row);// 将行数据封装成map对象与标题一一对应
                if (checkExpert(dataMap)) {
                    resultList.add(dataMap);
                }
            }
        }
        resultMap.put("resultList", resultList);
        resultMap.put("errorList", errorList);
        resultMap.put("messageList", messageList);
        return resultMap;
    }

    /**
     * 将数据和一一对应标题封装到map中
     * 
     * @param cell
     * @return
     */
    public static Map<String, String> getRowDataMap(HSSFRow titleRow, HSSFRow row) {
        Map<String, String> dataMap = null;
        if (null != row) {
            dataMap = new HashMap<String, String>();
            for (short i = 1; i < row.getLastCellNum(); i++) {
                if (!CommonUtil.isEmpty(row.getCell(i) + "")) {
                    dataMap.put(titleRow.getCell(i) + "", getCellVal(row.getCell(i)) + "");// 将标题和值封装到map中
                }
            }
        }
        return dataMap;
    }

    /**
     * 读取cell方法
     * 
     * @param cell
     * @return
     */
    public static String getCellVal(HSSFCell cell) {
        String result = null;
        switch (cell.getCellType()) {
        // 读取boolean
        case HSSFCell.CELL_TYPE_BOOLEAN:
            result = cell.getBooleanCellValue() + "";
            break;
        // 读取数字
        case HSSFCell.CELL_TYPE_NUMERIC:
            result = new BigDecimal(cell.getNumericCellValue()).toPlainString() + "";
            break;
        // 读取公式
        case HSSFCell.CELL_TYPE_FORMULA:
            result = cell.getCellFormula() + "";
            break;
        // 读取String
        case HSSFCell.CELL_TYPE_STRING:
            result = cell.getRichStringCellValue().toString() + "";
            break;
        default:
            result = null;
        }
        return result;
    }

    /**
     * 检查专家有效性
     * 
     * @param expert
     * @return
     */
    public static boolean checkExpert(Map<String, String> expert) {
        if (null == expert || expert.size() <= 0) {
            return false;
        }
        return true;
    }

    public static void sendExcel(HSSFWorkbook wb, HttpServletResponse rs, String filename)
        throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        rs.setHeader("Content-Length", baos.size() + "");
        OutputStream op = rs.getOutputStream();
        rs.setHeader("Content-disposition", "attachment; filename=" + filename);
        rs.setContentType("application/msexcel;charset=UTF-8");
        wb.write(op);
        op.flush();
        op.close();
    }
}
