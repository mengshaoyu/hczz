package com.shield.hczz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shield.hczz.base.domain.ExcelColumn;
import com.shield.hczz.caseinfo.anno.ExcelField;
import com.shield.hczz.utils.PinyinUtil.LinkType;

/**
 * @ClassName: ExcelUtil
 * @Description: Excel导入导出工具类
 * 
 */
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * excel文档默认的标题行
     */
    private static final int DEFAULT_TITLE_ROW = 0;
    /**
     * 不使用泛型时 默认的字段设置方式
     */
    private static final FieldSetting DEFAUTL_FIELD_SETTING = FieldSetting.pinyin;
    
    private static final String DEFAULT_CHECK_SHEET = "CHECK";

    /**
     * excel文档类型（以文件后缀名分类）
     */
    public enum ExcelType {
        /**
         * .xls后缀名的文件
         */
        HSSF,
        /**
         * .xlsx后缀名的文件
         */
        XSSF
    }

    /**
     * 不使用泛型时 字段的设置方式（默认使用拼音）
     */
    public enum FieldSetting {
        /**
         * 原始的字段（从excel读取后直接使用不处理，可能含有中文）
         */
        original,
        /**
         * 中文转为拼音
         */
        pinyin,
        /**
         * 拼音+【原始字段】
         */
        mixed
    }

    /**
     * 创建Excel文档
     * @param is 输入流
     * @param type excel文档类型
     * @return
     * @throws IOException
     */
    public static Workbook createWorkbook(InputStream is, ExcelType type) throws IOException {
        if (ExcelType.HSSF.equals(type)) {
            return new HSSFWorkbook(is);
        }
        else if (ExcelType.XSSF.equals(type)) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * 创建Excel文档（excel工作簿）
     * @param file 文档文件
     * @return
     * @throws IOException
     */
    public static Workbook createWorkbook(File file) throws IOException {
        if (null == file)
            return null;
        String fileName = file.getName();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return createWorkbook(is, getExcelType(fileName));
        }
        finally {
            if (null != is) {
                is.close();
            }
        }
    }

    /**
     * 创建Excel文档（excel工作簿）
     * @param filePath 文档文件路径
     * @return
     * @throws IOException
     */
    public static Workbook createWorkbook(String filePath) throws IOException {
        File file = new File(filePath);
        return createWorkbook(file);
    }

    /**
     * 根据文件名称 获取文档类型
     * @param excelFileName 文档文件名称
     * @return
     */
    public static ExcelType getExcelType(String excelFileName) {
        if (excelFileName.endsWith(".xls")) {
            return ExcelType.HSSF;
        }
        else if (excelFileName.endsWith(".xlsx")) {
            return ExcelType.XSSF;
        }
        return null;
    }

    /**
     * 读取文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @param vo 存放的实体类
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(InputStream is, ExcelType type, Class<T> vo,
        int titleRow) throws IOException {
        // 创建工作簿
        Workbook workbook = createWorkbook(is, type);
        return getDataFromExcel(workbook, vo, titleRow);
    }

    /**
     * 读取excel文档数据
     * @param workbook excel工作簿
     * @param vo 存放的实体类
     * @param titleRow 标题所在行索引
     * @return
     */
    public static <T> List<T> getDataFromExcel(Workbook workbook, Class<T> vo, int titleRow) {
        List<T> list = new ArrayList<T>();
        try {
            // 创建工作表sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            Row row0 = sheet.getRow(titleRow);
            // 获取表头单元格个数
            int cells = row0.getPhysicalNumberOfCells();
            Map<String, Integer> titleMap = new HashMap<String, Integer>();
            for (int i = 0; i < cells; i++) {
                Cell celli = row0.getCell(i);
                if (null == celli) {
                    continue;
                }
                celli.setCellType(Cell.CELL_TYPE_STRING);
                String title = celli.getStringCellValue();
                if (title != null)
                    title = title.trim();
                titleMap.put(title, i);
            }
            // 利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getDeclaredFields();
            for (int i = titleRow + 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                T bean = vo.getConstructor(new Class[] {}).newInstance(new Object[] {});
                for (Field field : fields) {// 第一行为标题栏，从第二行开始取数据
                    if (field.isAnnotationPresent(ExcelField.class)) {
                        ExcelField an = field.getAnnotation(ExcelField.class);
                        String desc = an.field();
                        Integer index = titleMap.get(desc);
                        if (index == null)
                            continue;
                        // if (index == null) {
                        // LOGGER.error("实体映射字段在excel模板中不存在！");
                        // return null;
                        // }
                        Cell cell = row.getCell(index);
                        if (null == cell) {
                            cell = row.createCell(index);
                        }
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = (null == cell.getStringCellValue()) ? ""
                            : cell.getStringCellValue();

                        String fieldName = field.getName();
                        Class<?> fieldType = field.getType();
                        String methodName = "";
                        if ("".equals(an.setMethod())) {
                            methodName = "set" + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        }
                        else {
                            methodName = an.setMethod();
                        }
                        Method setMethod = vo.getMethod(methodName, fieldType);
                        setMethod.invoke(bean, value);
                    }
                }
                if (isHasValues(bean)) {// 判断对象属性是否有值
                    list.add(bean);
                }
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    /**
     * 读取excel文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @param vo 存放的实体类
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(InputStream is, ExcelType type, Class<T> vo)
        throws IOException {
        return getDataFromExcel(is, type, vo, DEFAULT_TITLE_ROW);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @param vo 存放的实体类
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(File file, Class<T> vo, int titleRow)
        throws IOException {
        // 创建工作簿
        Workbook workbook = createWorkbook(file);
        return getDataFromExcel(workbook, vo, titleRow);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @param vo 存放的实体类
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(File file, Class<T> vo) throws IOException {
        return getDataFromExcel(file, vo, DEFAULT_TITLE_ROW);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @param vo 存放的实体类
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(String filePath, Class<T> vo, int titleRow)
        throws IOException {
        File file = new File(filePath);
        return getDataFromExcel(file, vo, titleRow);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @param vo 存放的实体类
     * @return
     * @throws IOException
     */
    public static <T> List<T> getDataFromExcel(String filePath, Class<T> vo) throws IOException {
        return getDataFromExcel(filePath, vo, DEFAULT_TITLE_ROW);
    }

    /**
     * 判断一个对象所有属性是否有值，如果一个属性有值(非空)，则返回true 
     */
    public static boolean isHasValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !("".equals(obj))) {
                    flag = true;
                    break;
                }
            }
            catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return flag;

    }

    /**
     * 读取excel文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @param titleRow 标题所在行索引
     * @param setting 字段的设置方式
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(InputStream is, ExcelType type,
        int titleRow, FieldSetting setting) throws IOException {
        // 创建工作簿
        Workbook workbook = createWorkbook(is, type);
        return getDataFromExcel(workbook, titleRow, setting);
    }

    /**
     * 读取excel文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(InputStream is, ExcelType type,
        int titleRow) throws IOException {
        return getDataFromExcel(is, type, titleRow, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @param setting 字段的设置方式
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(InputStream is, ExcelType type,
        FieldSetting setting) throws IOException {
        return getDataFromExcel(is, type, DEFAULT_TITLE_ROW, setting);
    }

    /**
     * 读取excel文档数据
     * @param is 输入流
     * @param type excel文档类型
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(InputStream is, ExcelType type)
        throws IOException {
        return getDataFromExcel(is, type, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @param titleRow 标题所在行索引
     * @param setting 字段的设置方式
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(File file, int titleRow,
        FieldSetting setting) throws IOException {
        // 创建工作簿
        Workbook workbook = createWorkbook(file);
        return getDataFromExcel(workbook, titleRow, setting);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(File file, int titleRow)
        throws IOException {
        return getDataFromExcel(file, titleRow, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @param setting 字段的设置方式
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(File file, FieldSetting setting)
        throws IOException {
        return getDataFromExcel(file, DEFAULT_TITLE_ROW, setting);
    }

    /**
     * 读取excel文档数据
     * @param file excel文档文件
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(File file) throws IOException {
        return getDataFromExcel(file, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @param titleRow 标题所在行索引
     * @param setting 字段的设置方式
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(String filePath, int titleRow,
        FieldSetting setting) throws IOException {
        // 创建工作簿
        Workbook workbook = createWorkbook(filePath);
        return getDataFromExcel(workbook, titleRow, setting);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @param titleRow 标题所在行索引
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(String filePath, int titleRow)
        throws IOException {
        return getDataFromExcel(filePath, titleRow, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(String filePath) throws IOException {
        return getDataFromExcel(filePath, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param filePath excel文档文件路径
     * @param setting 字段的设置方式
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> getDataFromExcel(String filePath, FieldSetting setting)
        throws IOException {
        return getDataFromExcel(filePath, DEFAULT_TITLE_ROW, setting);
    }

    /**
     * 读取excel文档数据
     * @param workbook excel工作簿
     * @param titleRow 标题所在行索引
     * @return
     */
    public static List<Map<String, Object>> getDataFromExcel(Workbook workbook, int titleRow) {
        return getDataFromExcel(workbook, titleRow, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param workbook excel工作簿
     * @param setting 字段的设置方式
     * @return
     */
    public static List<Map<String, Object>> getDataFromExcel(Workbook workbook,
        FieldSetting setting) {
        return getDataFromExcel(workbook, DEFAULT_TITLE_ROW, setting);
    }

    /**
     * 读取excel文档数据
     * @param workbook excel工作簿
     * @return
     */
    public static List<Map<String, Object>> getDataFromExcel(Workbook workbook) {
        return getDataFromExcel(workbook, DEFAULT_TITLE_ROW, DEFAUTL_FIELD_SETTING);
    }

    /**
     * 读取excel文档数据
     * @param workbook excel工作簿
     * @param titleRow 标题所在行索引
     * @param setting 字段的设置方式
     * @return
     */
    public static List<Map<String, Object>> getDataFromExcel(Workbook workbook, int titleRow,
        FieldSetting setting) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            // 创建工作表sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            Row row0 = sheet.getRow(titleRow);
            // 获取表头单元格个数
            int cells = row0.getPhysicalNumberOfCells();
            Map<Integer, ExcelColumn> titleMap = getTitleMap(row0);
            for (int i = titleRow + 1; i < rows; i++) {
                Map<String, Object> record = new HashMap<String, Object>();
                Row row = sheet.getRow(i);
                if (null == row)
                    continue;
                for (int j = 0; j < cells; j++) {
                    Cell cell = row.getCell(j);
                    String fieldName = null;
                    ExcelColumn ec = titleMap.get(j);
                    if (FieldSetting.original.equals(setting)) {
                        fieldName = ec.getTitle();
                    }
                    else if (FieldSetting.mixed.equals(setting)) {
                        fieldName = ec.getField() + "【" + ec.getTitle() + "】";
                    }
                    else {
                        fieldName = ec.getField();
                    }
                    if (null == cell) {
                        record.put(fieldName, "");
                        continue;
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = cell.getStringCellValue();
                    record.put(fieldName, value);
                }
                list.add(record);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取标题数据
     * @param workbook excel工作簿
     * @param titleRow 标题行索引
     * @return
     */
    public static Map<Integer, ExcelColumn> getTitleMap(Workbook workbook, int titleRow) {
        // 创建工作表sheet
        Sheet sheet = workbook.getSheetAt(0);
        // 获取sheet中数据的行数
        Row row0 = sheet.getRow(titleRow);
        // 获取表头单元格个数
        int cells = row0.getPhysicalNumberOfCells();
        Map<Integer, ExcelColumn> titleMap = new HashMap<Integer, ExcelColumn>();
        for (int i = 0; i < cells; i++) {
            Cell celli = row0.getCell(i);
            if (null == celli) {
                continue;
            }
            //防止读取数据异常
            celli.setCellType(Cell.CELL_TYPE_STRING);
            String title = celli.getStringCellValue();
            if (title != null)
                title = title.trim();
            String pyField = PinyinUtil.getStringPinYin(title, LinkType.hump);
            titleMap.put(i, new ExcelColumn(i, pyField, title));
        }
        return titleMap;
    }

    /**
     * 获取标题数据
     * @param workbook excel工作簿
     * @return
     */
    public static Map<Integer, ExcelColumn> getTitleMap(Workbook workbook) {
        return getTitleMap(workbook, DEFAULT_TITLE_ROW);
    }

    public static Map<Integer, ExcelColumn> getTitleMap(File file, int titleRow)
        throws IOException {
        Workbook workbook = createWorkbook(file);
        return getTitleMap(workbook, titleRow);
    }

    public static Map<Integer, ExcelColumn> getTitleMap(File file) throws IOException {
        Workbook workbook = createWorkbook(file);
        return getTitleMap(workbook);
    }

    public static Map<Integer, ExcelColumn> getTitleMap(String filePath, int titleRow)
        throws IOException {
        Workbook workbook = createWorkbook(filePath);
        return getTitleMap(workbook, titleRow);
    }

    public static Map<Integer, ExcelColumn> getTitleMap(String filePath) throws IOException {
        Workbook workbook = createWorkbook(filePath);
        return getTitleMap(workbook);
    }

    /**
     * 获取标题数据
     * @param row 标题行数据
     * @return
     */
    public static Map<Integer, ExcelColumn> getTitleMap(Row row) {
        int cells = row.getPhysicalNumberOfCells();
        Map<Integer, ExcelColumn> titleMap = new HashMap<Integer, ExcelColumn>();
        for (int i = 0; i < cells; i++) {
            Cell celli = row.getCell(i);
            if (null == celli) {
                continue;
            }
            //防止读取数据异常
            celli.setCellType(Cell.CELL_TYPE_STRING);
            String title = celli.getStringCellValue();
            if (title != null)
                title = title.trim();
            String pyField = PinyinUtil.getStringPinYin(title, LinkType.hump);
            titleMap.put(i, new ExcelColumn(i, pyField, title));
        }
        return titleMap;
    }

    /**
     * 获取标题数据
     * @param workbook excel工作簿
     * @param titleRow 标题行索引
     * @return
     */
    public static List<ExcelColumn> getTitles(Workbook workbook, int titleRow) {
        // 创建工作表sheet
        Sheet sheet = workbook.getSheetAt(0);
        // 获取sheet中数据的行数
        Row row0 = sheet.getRow(titleRow);
        // 获取表头单元格个数
        int cells = row0.getPhysicalNumberOfCells();
        List<ExcelColumn> list = new ArrayList<ExcelColumn>();
        for (int i = 0; i < cells; i++) {
            Cell celli = row0.getCell(i);
            if (null == celli) {
                continue;
            }
            //防止读取数据异常
            celli.setCellType(Cell.CELL_TYPE_STRING);
            String title = celli.getStringCellValue();
            if (title != null)
                title = title.trim();
            String pyField = PinyinUtil.getStringPinYin(title, LinkType.hump);
            list.add(new ExcelColumn(i, pyField, title));
        }
        return list;
    }

    /**
     * 获取标题数据
     * @param workbook excel工作簿
     * @return
     */
    public static List<ExcelColumn> getTitles(Workbook workbook) {
        return getTitles(workbook, DEFAULT_TITLE_ROW);
    }

    public static List<ExcelColumn> getTitles(File file, int titleRow) throws IOException {
        Workbook workbook = createWorkbook(file);
        return getTitles(workbook, titleRow);
    }

    public static List<ExcelColumn> getTitles(File file) throws IOException {
        Workbook workbook = createWorkbook(file);
        return getTitles(workbook);
    }

    public static List<ExcelColumn> getTitles(String filePath, int titleRow) throws IOException {
        Workbook workbook = createWorkbook(filePath);
        return getTitles(workbook, titleRow);
    }

    public static List<ExcelColumn> getTitles(String filePath) throws IOException {
        Workbook workbook = createWorkbook(filePath);
        return getTitles(workbook);
    }

    /**
     * 获取标题数据
     * @param row 标题行数据
     * @return
     */
    public static List<ExcelColumn> getTitles(Row row) {
        int cells = row.getPhysicalNumberOfCells();
        List<ExcelColumn> list = new ArrayList<ExcelColumn>();
        for (int i = 0; i < cells; i++) {
            Cell celli = row.getCell(i);
            if (null == celli) {
                continue;
            }
            //防止读取数据异常
            celli.setCellType(Cell.CELL_TYPE_STRING);
            String title = celli.getStringCellValue();
            if (title != null)
                title = title.trim();
            String pyField = PinyinUtil.getStringPinYin(title, LinkType.hump);
            list.add(new ExcelColumn(i, pyField, title));
        }
        return list;
    }

    /**
     * 生成建表SQL语句
     * @param tableName
     * @param columns
     * @return
     */
    public static String makeSQL(String tableName,List<ExcelColumn> columns){
        StringBuffer sb = new StringBuffer();
        
        return sb.toString();
    }
}
