
package com.company.handler;

import com.company.domain.ErrorMessage;
import com.company.exceptions.PermissionDeniedException;
import com.company.exceptions.TooManyRequestsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.company")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Model model, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.getMessage());
        mav.setViewName("errorPages/error");;
        return mav;
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ModelAndView exception5() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Internal Server Error");
        mav.setViewName("errorPages/error");
        return mav;
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ModelAndView exception1() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error",new ErrorMessage("403","Permission Denied","You don't have permission to access this page."));
        mav.setViewName("errorPages/errorAll");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView exception6() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error",new ErrorMessage("403","Access Denied","You don't have permission to access this page."));
        mav.setViewName("errorPages/errorAll");
        return mav;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView exception2() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/401");
        return mav;
    }
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ModelAndView exception3() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/403");
        return mav;
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView exception4() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("errors/404");
        return mav;
    }

//    @ExceptionHandler(TooMan)
}
