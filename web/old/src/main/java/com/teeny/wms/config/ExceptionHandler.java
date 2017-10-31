package com.teeny.wms.config;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bao on 2017/8/25.
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelMap model = new ModelMap();
        response.setStatus(200);
        if (ex instanceof WmsException) {
            WmsException wmsException = (WmsException) ex;
            model.addAttribute("result", wmsException.getInfo().getResult());
            model.addAttribute("msg", wmsException.getInfo().getMsg());
            return new ModelAndView("",model);
        }else {
            model.addAttribute("msg", ex.getMessage());
            model.addAttribute("result",1);
            return new ModelAndView("",model);
        }
    }
}
