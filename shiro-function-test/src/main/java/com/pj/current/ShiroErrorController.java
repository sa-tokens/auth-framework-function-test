package com.pj.current;

import com.pj.util.AjaxJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro 异常处理
 */
@RestController
public class ShiroErrorController {

    @RequestMapping("/401")
    public Object error401(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        return AjaxJson.get(401, "not login");
    }

    @RequestMapping("/403")
    public Object error403(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        return AjaxJson.get(403, "鉴权未通过");
    }

}
