package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.CommentInfo;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.OperateCommentInfoParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.reqParam.QueryCommentInfoParam;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.service.CommentInfoService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminCommentInfoController {

    @Resource CommentInfoService commentInfoService;


    /**
     * 编辑、保存评论信息
     * @return
     */
    @RequestMapping(value = "/operateCommentInfo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateCommentInfo(
            @RequestBody OperateCommentInfoParam param, HttpServletResponse response) {
        try {
            commentInfoService.operateCommentInfo(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("回复、修改评论信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 评论list
     * @return
     */
    @RequestMapping(value = "/commentInfoList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult commentInfoList(
            @RequestBody QueryCommentInfoParam param, HttpServletResponse response) {
        try {
            List<CommentInfo> commentInfoList = commentInfoService.getCommentInfoList(param);
            Integer allNum = commentInfoService.getCommentInfoAllNum(param);
            return JsonListResult.success(commentInfoList, allNum);
        } catch (Exception e) {
            log.error("获取评论list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
