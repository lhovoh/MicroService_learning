package top.lh.contentservice.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class SentineExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String msg = "哎哟，出问题了";
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        log.error("Sentinel 限流异常", e);

        if (e instanceof FlowException) {
            msg = "请求被限流了";
        } else if (e instanceof DegradeException) {
            msg = "服务降级了";
        } else if (e instanceof AuthorityException) {
            msg = "权限校验不通过";
        }

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(code);
        response.getWriter().println("{\"msg\": \"" + msg + "\", \"code\": " + code + "}");

    }
}
