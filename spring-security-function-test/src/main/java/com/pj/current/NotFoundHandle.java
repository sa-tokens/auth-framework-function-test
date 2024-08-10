package com.pj.current;

import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 处理 404
 * @author click33
 */
@RestController
public class NotFoundHandle implements ErrorController {

	@RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(200);
        return AjaxJson.get(404, "not found");
    }

}
