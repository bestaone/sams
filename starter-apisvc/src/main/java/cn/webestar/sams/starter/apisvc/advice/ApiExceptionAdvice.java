package cn.webestar.sams.starter.apisvc.advice;

import cn.webestar.sams.basic.common.BizCode;
import cn.webestar.sams.basic.common.BizException;
import cn.webestar.sams.basic.common.CommonException;
import cn.webestar.sams.basic.common.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "cn.webestar.sams.apisvc")
public class ApiExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public R handle(HttpServletRequest request, BindException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return R.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handle(HttpServletRequest request, MethodArgumentNotValidException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return R.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public R handle(HttpServletRequest request, Exception e) {
        logger(request, e);
        CommonException ce = null;
        if (e instanceof CommonException) {
            ce = (CommonException) e;
        } else {
            ce = new CommonException(BizCode.UN_ERROR, e.getMessage());
        }
        return R.fail(ce);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public R handle(HttpServletRequest request, BizException e) {
        logger(request, e);
        return R.fail(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public R handle(HttpServletRequest request, AccessDeniedException e) {
        logger(request, e);
        return R.fail(new CommonException(40006, "no permission."));
    }

    private void logger(HttpServletRequest request, Exception e) {
        String contentType = request.getHeader("Content-Type");
        log.error("ERROR URI: {} content-type: {} exception: {}", request.getRequestURI(), contentType, e.toString());
    }

}
