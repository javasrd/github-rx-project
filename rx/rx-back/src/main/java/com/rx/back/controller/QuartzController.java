package com.rx.back.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.back.commons.StaticConstants;
import com.rx.back.quartz.ScheduleJobBean;
import com.rx.bean.PageBean;
import com.rx.bean.ScheduleJobStatus;
import com.rx.bean.UserBean;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.JobCronExpression;
import com.rx.entity.ScheduleJob;
import com.rx.service.back.IJobCronExpressionService;
import com.rx.service.back.IScheduleJobService;
import com.rx.service.commons.QuartzJobStaticConstants;

/**
 * Class: QuartzController 计划任务信息维护Controller类
 * 
 * @author srd
 * @version 1.0 $Date: 2018年2月28日 上午10:16:02
 */
@Controller
@RequestMapping("/back/quartz")
public class QuartzController {

	private final Logger log = Logger.getLogger(getClass());

	@Resource(name = "scheduleJobServiceBean")
	private IScheduleJobService scheduleJobService;
	
	@Autowired
	private ScheduleJobBean scheduleJobBean;

	@Resource(name="jobCronExpressionServiceBean")
	private IJobCronExpressionService jobCronExpressionService;
	
	/**
	 * 方法功能：查询任务计划列表
	 * 
	 * @param request
	 * @param response
	 * @param clickPageBtn
	 * @param pageBean
	 * @param pagehelperFun
	 * @return
	 */
	@RequestMapping("/selectItems")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn,
			PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean) subject.getPrincipal();

		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("type", 1);//type=1:默认（后台管理用户）type=2:前端访问用户
		List<ScheduleJob> scheduleJobList = scheduleJobService.selectAll();// .getList(map);
		PageInfo<ScheduleJob> pagehelper = new PageInfo<ScheduleJob>(scheduleJobList);

		mav.addObject("pagehelper", pagehelper);

		if (clickPageBtn != null && clickPageBtn) {
			mav.setViewName(StaticConstants.SCHEDULE_JOB_MANAGE_TABLE_PAGE);
		} else {
			mav.setViewName(StaticConstants.SCHEDULE_JOB_MANAGE_PAGE);
		}

		List<JobCronExpression> jobCronExpressionList = jobCronExpressionService.selectAll();
		mav.addObject("jobCronExpressionList", jobCronExpressionList);
		
		mav.addObject("pagehelperFun", pagehelperFun);
		return mav;
	}

	/**
	 * 方法功能：查询要编辑的信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			ScheduleJob scheduleJob = scheduleJobService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("scheduleJob", scheduleJob);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}

	/**
	 * 创建任务（创建完成自动启动）
	 * 
	 * @param request
	 * @param response
	 * @param job
	 * @return
	 */
	@RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> insertQuartz(HttpServletRequest request, HttpServletResponse response, ScheduleJob job) {
		try {

			if (job!=null) {
				
				job.setJobGroup(QuartzJobStaticConstants.QUARTZ_JOB_GROUP_NAME);//默认作业调度任务的分组名称

				Date resDate = scheduleJobBean.updateJob(job);
				System.out.println("创建或更新任务时间：" + resDate);
				if (resDate != null) {
					// 把任务插入数据库
					int result = scheduleJobService.insertSelective(job);
					if (result != 0) {
						return RequestResultUtil.getResultSuccess("您的任务创建成功！");
					} else {
						return RequestResultUtil.getResultWarn("您的任务创建失败！");
					}
				} else {
					System.out.println("创建或更新任务失败");
				}

			} else {
				return RequestResultUtil.getResultWarn("您的任务信息为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RequestResultUtil.getResultSaveWarn();
	}

	/**
	 * 编辑任务（更新时间）
	 * 
	 * @param request
	 * @param response
	 * @param job
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> updateQuartz(HttpServletRequest request, HttpServletResponse response, ScheduleJob job) {
		try {

			if (job!=null) {

				Date resDate = scheduleJobBean.updateJob(job);
				System.out.println("创建或更新任务时间：" + resDate);
				if (resDate != null) {
					// 更新数据库中的任务
					int result = scheduleJobService.updateByPrimaryKeySelective(job);
					if (result == 1) {
						return RequestResultUtil.getResultSuccess("您的任务更新成功！");
					} else {
						return RequestResultUtil.getResultWarn("您的任务更新失败！");
					}
				} else {
					System.out.println("创建或更新任务失败");
				}

			} else {
				return RequestResultUtil.getResultWarn("您的任务信息为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RequestResultUtil.getResultSaveWarn();
	}

	/**
	 * 启动任务
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/start", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> startQuartz(HttpServletRequest request, HttpServletResponse response, Long id) {

		ScheduleJob job = scheduleJobService.selectByPrimaryKey(id);

		if (job != null) {
			try {
				//启动任务
				scheduleJobBean.updateJob(job);
				//修改任务状态
				job.setJobStatus((byte)ScheduleJobStatus.JOB_ING.getIndex().intValue());
				scheduleJobService.updateByPrimaryKeySelective(job);
				return RequestResultUtil.getResultSuccess("您的任务已启动！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return RequestResultUtil.getResultWarn("您的任务启动启动！");
	}
	
	/**
	 * 暂停任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pause", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> pauseQuartz(HttpServletRequest request, HttpServletResponse response, Long id) {

		ScheduleJob job = scheduleJobService.selectByPrimaryKey(id);

		if (job != null) {
			try {
				scheduleJobBean.pauseJob(job);
				//修改任务状态
				job.setJobStatus((byte)ScheduleJobStatus.JOB_PAUSE.getIndex().intValue());
				scheduleJobService.updateByPrimaryKeySelective(job);
				return RequestResultUtil.getResultSuccess("您的任务已暂停！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return RequestResultUtil.getResultWarn("您的任务暂停失败！");
	}

	/**
	 * 恢复任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/resume", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> resumeQuartz(HttpServletRequest request, HttpServletResponse response, Long id) {

		ScheduleJob job = scheduleJobService.selectByPrimaryKey(id);

		if (job != null) {
			try {
				scheduleJobBean.resumeJob(job);
				//修改任务状态
				job.setJobStatus((byte)ScheduleJobStatus.JOB_ING.getIndex().intValue());
				scheduleJobService.updateByPrimaryKeySelective(job);
				return RequestResultUtil.getResultSuccess("您的任务已恢复！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return RequestResultUtil.getResultWarn("您的任务恢复失败！");
	}

	/**
	 * 删除任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> deleteQuartz(HttpServletRequest request, HttpServletResponse response, Long id) {

		ScheduleJob job = scheduleJobService.selectByPrimaryKey(id);

		if (job != null) {
			try {
				boolean flag = scheduleJobBean.deleteJob(job);
				if (flag) {
					//修改任务状态
					job.setJobStatus((byte)ScheduleJobStatus.JOB_DELETE.getIndex().intValue());
					scheduleJobService.updateByPrimaryKeySelective(job);
					return RequestResultUtil.getResultSuccess("您的任务已删除！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return RequestResultUtil.getResultWarn("您的任务删除失败！");
	}
	
	/**
	 * 删除任务及记录
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteRecord", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> deleteQuartzAndRecord(HttpServletRequest request, HttpServletResponse response, Long id) {

		ScheduleJob job = scheduleJobService.selectByPrimaryKey(id);

		if (job != null) {
			try {
				boolean flag = scheduleJobBean.deleteJob(job);
				if (flag) {
					int rows = scheduleJobService.deleteByPrimaryKey(id);
					if (rows > 0) {
						return RequestResultUtil.getResultSuccess("您的任务已删除！");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return RequestResultUtil.getResultWarn("您的任务删除失败！");
	}
}
