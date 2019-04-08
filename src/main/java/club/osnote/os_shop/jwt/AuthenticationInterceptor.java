package club.osnote.os_shop.jwt;

import club.osnote.os_shop.dao.UserDao;
import club.osnote.os_shop.utils.Constants;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = null;
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        if (!method.isAnnotationPresent(NeedLoginAnno.class)) {
            return true;
        }
        NeedLoginAnno needLoginAnno = method.getAnnotation(NeedLoginAnno.class);
        if (!needLoginAnno.required()) {
            return true;
        }

        Cookie[] cookies = httpServletRequest.getCookies();// 从 http 请求头中取出 token
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(Constants.User_Token)) {
                    token = cookie.getValue();
                }
            }
        }

        try {
            httpServletRequest.setAttribute(Constants.User_ID, JwtUtils.verifyToken(token));
        } catch (Exception e) {
            httpServletResponse.sendRedirect("/user/login");
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}