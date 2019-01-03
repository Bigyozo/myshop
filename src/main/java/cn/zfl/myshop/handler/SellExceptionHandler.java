package cn.zfl.myshop.handler;

import cn.zfl.myshop.ViewObj.ResultVO;
import cn.zfl.myshop.config.ProjectUrlConfig;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.exception.SellerAuthorizeException;
import cn.zfl.myshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/27 22:18<br/>
 */
@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:".concat(projectUrlConfig.getSell())
                .concat("/sell/wechat/qrAuthorize?returnUrl=").concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
