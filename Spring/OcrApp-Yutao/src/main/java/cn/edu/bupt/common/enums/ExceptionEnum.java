package cn.edu.bupt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum{
    //private static final Exception ff= new Exception(400,"价格不能为空");
    UPLOAD_FILE_ERROR(500,"上传文件失败"),
    INVALID_FILE_TYPE(500,"无效的文件类型"),
    INVALID_FILE_CONTENT(500,"无效文件内容"),
    USER_ID_CANT_BE_NULL(500,"测试人员id不能为空"),
    JSON_PARSE_ERROR(500,"json解析错误"),
    REFLECT_ERROR(500,"反射异常"),
    USER_NOT_FOUND(500,"用户不存在"),
    DATA_UPDATE_FAIL(500,"数据更新失败"),
    USERNAME_EXIST(500,"该用户名已存在"),
    OLD_PASSWORD_ERROR(500,"旧密码错误"),
    FILE_TOO_LARGE(500,"文件超出规定大小"),
    QINIU_CONFIG_ERROR(500,"外链域名必须以http://或者https://开头"),
    OBSERVATION_ID_NOT_EXIST(500,"观测id不存在")



    ;
    private int code;
    private String message;

}