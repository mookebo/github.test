package cn.study.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 返回一个字符串代表过滤器的类型，在Zuul中定义了四种不同生命周期的过滤器类型
     * pre：路由之前
     * routing：路由之时
     * post：路由之后
     * error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤的具体业务代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if(token == null){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
                try {
                HttpServletResponse response = currentContext.getResponse();
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("非法 请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
