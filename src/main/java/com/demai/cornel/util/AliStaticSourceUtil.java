/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadImageRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadImageResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadImageRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadImageResponse;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.vod.model.v20170321.GetImageInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetImageInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.demai.cornel.vo.uploadfile.UploadResp;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Create By tfzhu  2022/4/30  5:48 AM
 *
 * @author tfzhu
 */
@Slf4j public class AliStaticSourceUtil {

    /**
     * 只有RAM用户（子账号）才能调用 AssumeRole 接口
     * 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
     * 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
     */
    private static String accessKeyId = "LTAI5tGbPs4j2BUsrDYE7fcy";
    private static String accessKeySecret = "IU9oBw5AK3go3fYK1u2veHzKa6aFN1";
    /**
     * AssumeRole API 请求参数: RoleArn, RoleSessionName, Policy, and DurationSeconds
     * RoleArn 需要在 RAM 控制台上获取
     */
    private static String roleArn = "acs:ram::1022351026377928:role/voderole";
    /**
     * RoleSessionName 角色会话名称，自定义参数
     */
    private static String roleSessionName = "session-name";// 自定义即可
    /**
     * 点播服务所在的Region，接入服务中心为上海，则填cn-shanghai
     */
    private static String regionId = "cn-shenzhen";

    private static String localStorage = "outin-4bfcaac9c80e11ecbfcd00163e021072.oss-cn-shenzhen.aliyuncs.com";
    /**
     * 定制你的policy
     */
    private static String policy =
            "{\n" + "  \"Version\": \"1\",\n" + "  \"Statement\": [\n" + "    {\n" + "      \"Action\": \"vod:*\",\n"
                    + "      \"Resource\": \"*\",\n" + "      \"Effect\": \"Allow\"\n" + "    }\n" + "  ]\n" + "}";

//    public static void main(String[] args) {
//        log.info(getVideoUrl("3e8a569162c646d8bb5f399dfa73177b"));
//        log.info(getImageUrl("5e8236886e4845e3bcf61cb8f3a51f41"));
//    }

    public static UploadResp uploadVideo(String title,String path) {
        try {
//            AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy);
//            return createUploadVideo(response.getCredentials().getAccessKeyId(),
//                    response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken(),
//                    showName, path);

           return doUploadVideo(title,path);
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        return null;
    }

    public static String getVideoUrl(String videoId) {
        try {
            AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy);
            return queryUploadVideo(response.getCredentials().getAccessKeyId(),
                    response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken(),
                    videoId);

        } catch (ClientException e) {
            log.error("上传文件失败", e);
        }
        return null;
    }

    public static UploadResp uploadImg(String path) {
        try {
//            AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy);
//            return createUploadImg(response.getCredentials().getAccessKeyId(),
//                    response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken(),
//                    showName, path);

            return doUploadImage(path);
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        return null;
    }

    public static String getImageUrl(String imageId) {
        try {
            AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy);
            return queryImageUrl(response.getCredentials().getAccessKeyId(),
                    response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken(),
                    imageId);

        } catch (ClientException e) {
            log.error("上传文件失败", e);
        }
        return null;
    }

    static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
            String roleSessionName, String policy) throws ClientException {
        try {
            /**构造default profile（参数留空，无需添加Region ID）
             说明：当设置SysEndpoint为sts.aliyuncs.com时，regionId可填可不填；反之，regionId必填，根据使用的服务区域填写，例如：cn-shanghai
             详情参考STS各地域的Endpoint，请参见接入地址。
             */
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            //用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysEndpoint("sts.aliyuncs.com");
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    private static String createUploadVideo(String accessKeyId, String accessKeySecret, String token, String showName,
            String path) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setSecurityToken(token);
        request.setTitle(showName);
        request.setFileName(path);
        String result = null;
        try {
            CreateUploadVideoResponse response = client.getAcsResponse(request);
            log.info("CreateUploadVideoRequest, " + request.getUrl());
            log.info("CreateUploadVideoRequest, requestId:" + response.getRequestId());
            log.info("UploadAddress, " + response.getUploadAddress());
            log.info("UploadAuth, " + response.getUploadAuth());
            log.info("VideoId, " + response.getVideoId());
            result = response.getVideoId();
        } catch (Exception e) {
            log.error("upload video exception ", e);
        }
        return result;
    }

    private static String queryUploadVideo(String accessKeyId, String accessKeySecret, String token, String videoId) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setSecurityToken(token);
        request.setVideoId(videoId);
        String result = null;
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            result = response.getPlayInfoList().get(0).getPlayURL();
        } catch (ClientException e) {
            log.error("query video url exception ", e);
        }
        return result;
    }

    private static String createUploadImg(String accessKeyId, String accessKeySecret, String token, String showName,
            String path) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 点播服务所在的Region，接入服务中心为上海，则填cn-shanghai
        DefaultAcsClient client = new DefaultAcsClient(profile);

        CreateUploadImageRequest request = new CreateUploadImageRequest();
        request.setSecurityToken(token);
        request.setTitle(showName);
        request.setImageType("default");
        request.setUserData(path);
        String result = null;

        try {
            CreateUploadImageResponse response = client.getAcsResponse(request);
            log.info("CreateUploadVideoRequest, " + request.getUserData());
            log.info("CreateUploadVideoRequest, requestId:" + response.getRequestId());
            log.info("UploadAddress, " + response.getUploadAddress());
            log.info("UploadAuth, " + response.getUploadAuth());
            log.info("imageId, " + response.getImageId());
            log.info("imageURL, " + response.getImageURL());
            result = response.getImageId();
            log.info(new String(Base64Utils.decode(response.getUploadAddress())));
        } catch (Exception e) {
            log.error("upload video exception ", e);
        }
        return result;
    }

    private static String queryImageUrl(String accessKeyId, String accessKeySecret, String token, String imageId) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetImageInfoRequest request = new GetImageInfoRequest();
        request.setSecurityToken(token);
        request.setImageId(imageId);
        String result = null;
        try {
            GetImageInfoResponse response = client.getAcsResponse(request);
            result = response.getImageInfo().getURL();
        } catch (ClientException e) {
            log.error("query image url exception ", e);
        }
        return result;
    }

    public static UploadResp doUploadVideo(String title,String path) throws Exception {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, path);
        request.setPartSize(2 * 1024 * 1024L);
        request.setTaskNum(1);
        request.setStorageLocation(localStorage);
        request.setApiRegionId(regionId);
        /* ECS部署区域*/
        request.setEcsRegionId(regionId);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        log.info("RequestId=" + response.getRequestId());  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            UploadResp resp = UploadResp.builder()
                    .sourceId(response.getVideoId())
                    .url(response.getVideoId())
                    .build();
            return resp;
        }
        return null;
    }


    public static UploadResp doUploadImage(String path) throws Exception {
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
        String imageType = "default";
        UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
        request.setImageType("default");
        request.setStorageLocation(localStorage);
        request.setFileName(path);
        request.setApiRegionId(regionId);
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        log.info("RequestId=" + response.getRequestId());
        if (response.isSuccess()) {
            UploadResp resp = UploadResp.builder()
                    .sourceId(response.getImageId())
                    .url(response.getImageURL())
                    .build();
            return resp;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            doUploadImage("/Users/tfzhu/fh/1642509481706184.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


