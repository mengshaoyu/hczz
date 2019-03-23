package com.shield.frame.common.qvo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前后台公共交互类
 */
public class ResultVO extends HashMap<String, Object> {

    /**
     * 
     */
    private static final long serialVersionUID = -4693114584812125354L;

    private static Map<String, Object> head = new HashMap<String, Object>();

    public ResultVO() {
        this.put("head", _head());
        this.put("body", _body());
    }

    public ResultVO(boolean success) {
        this.put("head", _head(success));
        this.put("body", _body());
    }

    public ResultVO(boolean success, String msg) {
        this.put("head", _head(success, msg));
        this.put("body", _body());
    }

    /**
     * 操作成功结果
     * @return
     */
    public static ResultVO ok() {
        return new ResultVO(true, "操作成功");
    }

    /**
     * 操作成功的返回结果
     * @param obj 返回结果
     * @return
     */
    public static ResultVO ok(Object obj) {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(true));
        try {
            vo.put("body", _body(obj));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 操作成功的返回结果
     * @param obj 返回结果
     * @param msg 消息
     * @return
     */
    public static ResultVO ok(Object obj, String msg) {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(true, msg));
        try {
            vo.put("body", _body(obj));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 操作成功结果
     * @param rows 返回列表数据（当前页）
     * @param total 数据总数
     * @return
     */
    public static <T> ResultVO ok(List<T> rows, long total) {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(true));
        try {
            vo.put("body", _body(rows, total));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 操作失败结果
     * @return
     */
    public static ResultVO error() {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(false, "操作失败"));
        return vo;
    }

    /**
     * 操作失败结果
     * @param msg 错误信息
     * @return
     */
    public static ResultVO error(String msg) {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(false, msg));
        return vo;
    }

    /**
     * 操作失败结果
     * @param code 错误码
     * @param msg 错误信息
     * @return
     */
    public static ResultVO error(int code, String msg) {
        ResultVO vo = new ResultVO();
        vo.put("head", _head(false, code, msg));
        return vo;
    }

    private static Map<String, Object> _head() {
        return _head(true);
    }

    private static Map<String, Object> _head(boolean success) {
        int default_code = success == true ? 200 : 500;
        return _head(success, default_code);
    }

    private static Map<String, Object> _head(boolean success, int code) {
        return _head(success, code, "");
    }

    private static Map<String, Object> _head(boolean success, String msg) {
        int default_code = success == true ? 200 : 500;
        return _head(success, default_code, msg);
    }

    private static Map<String, Object> _head(boolean success, int code, String msg) {
        head.put("success", success);
        head.put("code", code);
        head.put("msg", msg);
        return head;
    }

    private static Object _body() {
        Map<String, Object> body = new HashMap<String, Object>();
        return body;
    }

    private static Object _body(Object obj) throws Exception {
        if(List.class.isInstance(obj)){
            Class<?> c = obj.getClass();
            Method m = c.getMethod("size");
            long total = Long.valueOf(m.invoke(obj).toString());
            return _body((List<?>)obj,total);
        }
        return obj;
    }

    private static <T> Object _body(List<T> rows, long total) throws Exception {
        DataGridVO<T> vo = new DataGridVO<T>();
        vo.setTotal(total);
        vo.setRows(rows);
        return _body(vo);
    }

}
