/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.oss.cloud;

import com.njwpdi.common.utils.ConfigConstant;
import com.njwpdi.common.utils.Constant;
import com.njwpdi.common.utils.SpringContextUtils;
import com.njwpdi.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author Mark sunlightcs@gmail.com
 */
public final class OssFactory {
    private static SysConfigService sysConfigService;

    static {
        OssFactory.sysConfigService = (SysConfigService)SpringContextUtils.getBean("sysConfigService");
    }

    public static AbstractCloudStorageService build() {
        // 获取云存储配置信息
        CloudStorageConfig config =
            sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
