package cn.zfl.myshop.ViewObj;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: myshop
 * @description: 请求返回最外层对象
 * @author: zhangfl
 * @create: 2018-11-11 14:51
 **/
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -2810001954956149433L;
    /*错误码*/
    private Integer code;
    private String msg;
    /*具体内容*/
    private T data;
}
