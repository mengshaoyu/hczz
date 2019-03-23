package com.shield.hczz.apply.serviceimpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.impl.MenuMapperImpl;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.hczz.apply.qvo.PzApplyQO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.apply.service.PzApplyService;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.domain.ServiceManage;
import com.shield.hczz.base.persistence.impl.PzApplyMapperImpl;
import com.shield.hczz.base.persistence.impl.ServiceManageMapperImpl;
import com.shield.hczz.common.SopConstants;

@Service
public class PzApplyServiceImpl implements PzApplyService {

    private static final String MENU_ID = "100202";
    private static final String TOOLBAR_TYPE = "2";

    @Autowired
    private PzApplyMapperImpl pzApplyMapperImpl;
    @Autowired
    private MenuMapperImpl menuMapper;
    @Autowired
    private ServiceManageMapperImpl serviceManageMapperImpl;
    @Autowired
    protected TaskService taskService;
    @Autowired
    private RoleService roleService;

    @SuppressWarnings({ "unchecked" })
    @Override
    public ResultVO getlist(PzApplyQO qo, String page, String rows, User user) {
        DataGridVO<PzApplyVO> result = new DataGridVO<PzApplyVO>();
        try {
            String status = qo.getPzApplyStatus();
            if (status != null && !"".equals(status)) {
                String[] statusArr = status.split(",");
                StringBuffer sb = new StringBuffer();
                //sql in对非数字类型的处理
                for (String sta : statusArr) {
                    sb.append("'");
                    sb.append(sta);
                    sb.append("',");
                }
                sb.deleteCharAt(sb.length() - 1);
                qo.setPzApplyStatus(sb.toString());
            }
            Map<String, Object> param = BeanUtils.describe(qo);

            int total = this.getCount(param);
            result.setTotal(total);
            // 当前页
            int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);
            // 每页显示条数
            int intRows = Integer.parseInt((rows == null || rows.equals("0")) ? "10" : rows);
            List<PzApplyVO> list = pzApplyMapperImpl.getlist(param, intPage, intRows);
            //统一处理操作按钮
            List<ToolBarVO> toolbarList = null;
            BigDecimal userId = new BigDecimal(qo.getUserId());
            if ("1".equals(qo.getUserId())) {
                toolbarList = this.menuMapper.getToolBarByType(MENU_ID, null, TOOLBAR_TYPE);
            }
            else {
                // 判断是否具备操作权限
                toolbarList = this.menuMapper.getToolBarByType(MENU_ID, userId, TOOLBAR_TYPE);
            }
            for (PzApplyVO vo : list) {
                List<ToolBarVO> rs = this.getToolbarList(toolbarList, vo, user);
                vo.setToolbarList(rs);
            }
            result.setRows(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.ok(result);
    }

    private List<ToolBarVO> getToolbarList(List<ToolBarVO> list, PzApplyVO vo, User user) {
        List<ToolBarVO> toolbars = new ArrayList<ToolBarVO>();
        String key = null;
        List<String> us = new ArrayList<String>();
        List<Role> roles = user.getRoleList();
        if (null != vo) {
            String flowId = vo.getFlowId();
            List<Task> t = taskService.createTaskQuery().processInstanceId(flowId).list();
            List<HashMap<String, Object>> assignees = this.getAssignees(vo.getPzApplyId());
            for (HashMap<String, Object> p : assignees) {
                us.add(p.get("USER_ID") == null ? "" : p.get("USER_ID").toString());
            }
            if (t.size() > 0) {
                key = t.get(0).getTaskDefinitionKey();
            }
        }
        BigDecimal userId = user.getUserId();
        boolean isCurrent = us.contains(userId == null ? null : userId.toString());
        for (ToolBarVO v : list) {
            String functionId = v.getUuid();
            if ("1002020908".equals(functionId)) {//报告按钮
                if (SopConstants.FLOW_DHS.equals(key) || key == null || "".equals(key)) {
                    toolbars.add(v);
                }
            }
            if ("1002020909".equals(functionId)) {//查看评价按钮
                if (key == null || "".equals(key)) {
                    toolbars.add(v);
                }
            }
            if ("1002020910".equals(functionId)) {//评定按钮
                if ((key == null || "".equals(key))
                    && (roleService.has(roles, SopConstants.ROLE_HCZXLD1)
                        || roleService.has(roles, SopConstants.ROLE_HCZXLD2))) {
                    toolbars.add(v);
                }
            }
            if (isCurrent) {
                if ("1002020902".equals(functionId)) {
                    if (SopConstants.FLOW_FGLDSP.equals(key)
                        || SopConstants.FLOW_DZBZSP.equals(key)) {//分管领导和值班长审批
                        toolbars.add(v);
                    }
                }
                else if ("1002020904".equals(functionId)) {//分发
                    if (SopConstants.FLOW_DFP.equals(key)) {
                        toolbars.add(v);
                    }
                }
                else if ("1002020905".equals(functionId)) {//反馈
                    if (SopConstants.FLOW_DFK.equals(key)) {
                        toolbars.add(v);
                    }
                }
                else if ("1002020906".equals(functionId)) {//评价/审核
                    if (SopConstants.FLOW_DHS.equals(key)) {
                        toolbars.add(v);
                    }
                }
                else if ("1002020903".equals(functionId) || "1002020907".equals(functionId)) {//编辑、删除
                    if ((SopConstants.FLOW_TZBC.equals(key)
                        || SopConstants.FLOW_SQPZ.equals(key))) {
                        toolbars.add(v);
                    }
                }
            }
        }
        return toolbars;
    }

    @Override
    public CommonVO add(HttpServletRequest request, PzApply pzApply) {
        int flag = pzApplyMapperImpl.add(pzApply);
        boolean success = flag > 0 ? true : false;
        return new CommonVO(success);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return pzApplyMapperImpl.getCount(map);
    }

    @Override
    public int updateDeleted(String ids, String userId) throws Exception {
        return pzApplyMapperImpl.updateDeleted(ids, userId);
    }

    @Override
    public CommonVO updateStatus(HttpServletRequest request, PzApply pzApply) {
        String userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .toString();
        pzApply.setUpdateBy(userId);
        int count = pzApplyMapperImpl.updateFlow(pzApply);
        boolean success = count > 0 ? true : false;
        return new CommonVO(success);
    }

    @Override
    public PzApplyVO getInfoById(String pzid) {
        return this.pzApplyMapperImpl.getInfoById(pzid);
    }

    @Override
    public PzApplyVO getFlowTime(String pzid, String startFlow, String endFlow, String backTime) {
        PzApplyVO pzApply = this.pzApplyMapperImpl.getFlowTime(pzid, startFlow, endFlow);

        if (null != pzApply && null != pzApply.getFlowTime()) {
            String[] flowTime = pzApply.getFlowTime().split(",");
            String signTime = flowTime[0];
            String submitTime = flowTime[1];

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d1 = df.parse(signTime);
                Date d2 = df.parse(submitTime);
                double diff = d2.getTime() - d1.getTime();
                double days = diff / (1000 * 60 * 60);

                if (null != backTime && !"".equals(backTime)) {
                    double require = Long.valueOf(backTime).longValue();
                    DecimalFormat dft = new DecimalFormat("#.00");
                    String timeDiff = dft.format(days - require);

                    pzApply.setTimeDiff(Double.valueOf(timeDiff));//时差
                }

                pzApply.setFlowTime(days + "");//反馈所用时长

                //设置反馈日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String backDate = sdf.format(df.parse(submitTime));
                pzApply.setBackDate(backDate);//反馈日期

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            pzApply.setReceiveTime(signTime);
            pzApply.setFeedbackTime(submitTime);
        }

        return pzApply;
    }

    @Override
    public DataGridVO<Map<String, Object>> getToDoCount(String userId, String status) {
        DataGridVO<Map<String, Object>> result = new DataGridVO<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("userId", userId);
        map.put("status", status);
        List<Map<String, Object>> list = pzApplyMapperImpl.getToDoCount(map);
        if (list == null || list.isEmpty()) {
            result.setMsgInfo("代办事项为空");
            result.setTotal(0);
        }
        else {
            result.setTotal(list.size());
        }
        result.setRows(list);
        return result;
    }

    @Override
    public int saveEvaluate(PzApply pzApply) {
        return pzApplyMapperImpl.updateLanding(pzApply);
    }

    @Override
    public DataGridVO<Map<String, Object>> getToDoCount(String userId) {
        return this.getToDoCount(userId, SopConstants.DEFAULT_NOTICE_STATUS);
    }

    @Override
    public ResultVO read(String pzApplyId, String userId) {
        ApplyExp applyExp = new ApplyExp(pzApplyId, userId);
        int count = pzApplyMapperImpl.addExp(applyExp);
        if (count > 0) {
            return ResultVO.ok();
        }
        else {
            return ResultVO.error("更新记录失败");
        }
    }

    @Override
    public String getFlowNameById(String pzid) {

        // 根据配侦ID，获取当前
        PzApplyVO apply = this.pzApplyMapperImpl.getFlowById(pzid);
        if (null != apply) {
            return (null != apply.getFlowDesc() && !"".equals(apply.getFlowDesc()))
                ? "【" + apply.getFlowDesc() + "】" : "";
        }
        return "";
    }

    /**
     * 获取当前配侦界面需要显示的按钮信息
     * 
     * @param key
     *            当前配侦对应流程实例ID
     * @param userId
     *            用户主键
     * @param functionIds
     *            functionID
     */
    @Override
    public List<ToolBarVO> getMenuButton(PzApplyVO apply, String key, User user, String menuId,
        String toolbar, boolean isCurrent) {
        List<ToolBarVO> toolbarList = null;
        if ("admin".equals(user.getLoginAccount())) {
            toolbarList = this.menuMapper.getToolBarByType(menuId, null, toolbar);
        }
        else {
            // 判断是否具备操作权限
            toolbarList = this.menuMapper.getToolBarByType(menuId, user.getUserId(), toolbar);
        }
        List<ToolBarVO> result = new ArrayList<ToolBarVO>();
        if (key == null || "".equals(key)) {
            return result;
        }
        if (null == apply) {
            return toolbarList;
        }
        for (int i = 0; i < toolbarList.size(); i++) {
            ToolBarVO tool = toolbarList.get(i);
            String functionId = tool.getUuid();
            tool.setShow("0");
            if ("1002021001".equals(functionId)) {// 任务申请编辑按钮：待提报、退侦
                if (SopConstants.FLOW_TZBC.equals(key) || SopConstants.FLOW_SQPZ.equals(key)) {
                    if (apply.getUserId().equals(user.getUserId().toString())) {
                        tool.setShow("1");
                    }
                }
            }
            else if ("1002021002".equals(functionId)) {// 取回按钮：
                if (SopConstants.FLOW_TZBC.equals(key) || SopConstants.FLOW_SQPZ.equals(key)) {
                    if (1 == 1) {
                        tool.setShow("1");// 可显示
                    }
                }
            }
            else if ("1002021003".equals(functionId)) {// 分发按钮：待分发状态，且具备分发角色
                if (SopConstants.FLOW_DFP.equals(key)) {
                    if (isCurrent) {
                        tool.setShow("1");// 可显示
                    }
                }
            }
            else if ("1002021004".equals(functionId) || "1002020902".equals(functionId)) {// 审批按钮：分管领导审批、待值班长审批
                List<Role> roleList = user.getRoleList();
                if (SopConstants.FLOW_FGLDSP.equals(key)) {
                    for (int j = 0; j < roleList.size(); j++) {
                        Role r = roleList.get(j);
                        String roleId = r.getRoleId().toString();
                        if (null != roleId && SopConstants.ROLE_FGLD.equals(roleId)
                            && (apply.getDeptId().equals(user.getDeptId().toString()))
                            && isCurrent) {//当前用户拥有分管领导角色
                            tool.setShow("1");// 可显示
                            result.add(tool);
                        }
                    }
                }
                else if (SopConstants.FLOW_DZBZSP.equals(key)) {
                    if (isCurrent) {
                        tool.setShow("1");// 可显示
                    }
                }
            }
            else if ("1002021005".equals(functionId)) {// 落地评价按钮：业务申请人
                if ("".equals(key) || (null != key && key.equals(SopConstants.FLOW_DHS))) {
                    if (apply.getUserId().equals(user.getUserId().toString())) {
                        tool.setShow("1");// 可显示
                    }
                }
            }
            else if ("1002020904".equals(functionId)) {//分发
                if (SopConstants.FLOW_DFP.equals(key)) {
                    result.add(tool);
                }
            }
            else if ("1002020905".equals(functionId)) {//反馈
                if (SopConstants.FLOW_DFK.equals(key) && isCurrent) {
                    result.add(tool);
                }
            }
            else if ("1002020906".equals(functionId)) {//评价
                if (SopConstants.FLOW_DHS.equals(key) && isCurrent) {
                    result.add(tool);
                }
            }
            else if ("1002020903".equals(functionId) || "1002020907".equals(functionId)) {//编辑、删除
                if ((SopConstants.FLOW_TZBC.equals(key) || SopConstants.FLOW_SQPZ.equals(key))
                    && isCurrent) {
                    result.add(tool);
                }
            }

        }

        return "2".equals(toolbar) ? result : toolbarList;
    }

    @Override
    public String getBackTimeLength(String pzApplyId) {
        Map<String, Object> param = new HashMap<>();
        param.put("pzid", pzApplyId);

        List<ServiceManage> list = serviceManageMapperImpl.getMaxBackTime(pzApplyId);

        if (null != list && list.size() > 0) {

            return list.get(0).getFeedbackTime();// 获取查询请求对应的最长反馈时长

        }

        return null;
    }

    /**
     * 依据配侦主键，获取配侦申请信息,用于反馈表数据显示
     * @param	pzid	配侦主键
     * @return	返回配侦申请信息
     */
    @Override
    public PzApplyVO getApplyMsgById(String pzid) {
        return this.pzApplyMapperImpl.getApplyMsgById(pzid);
    }

    @Override
    public ResultVO unread(String pzApplyId) {
        ApplyExp applyExp = new ApplyExp(pzApplyId);
        int count = this.pzApplyMapperImpl.backUnread(applyExp);
        if (count > 0)
            return ResultVO.ok();
        else
            return ResultVO.error("操作失败");
    }

    @Override
    public List<HashMap<String, Object>> getAssignees(String pzid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pzid", pzid);
        return this.pzApplyMapperImpl.getAssignees(param);
    }

}
