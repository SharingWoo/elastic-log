package com.wsn.elasticlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author sharing
 * @version 1.0.0
 * @create 2019-01-11 下午5:16
 * 日志类型
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogItem {
    private String logType;
    private Date createdAt;
    private String content;
}
