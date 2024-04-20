package com.hxy.OnlineDiningRoom.Interceptor;

import com.hxy.OnlineDiningRoom.pojo.Customer;
import com.hxy.OnlineDiningRoom.service.OrderItemService;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginInterceptorTest {

    @InjectMocks
    private LoginInterceptor loginInterceptor;

    @Mock
    private OrderItemService orderItemService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = request.getSession();
    }

    @Test
    public void testPreHandle() throws Exception {
        // Prepare the test data
        String[] noNeedAuthPage = new String[]{
                "Index", //首页
                "DetailUI", //商品详情页
                "RegisterUI",  //注册页
                "Register",  //注册
                "LoginUI",  //登陆页
                "Login",     //登陆
                "IsLogin",  //判断是否登陆
                "MtLogin", //模态登陆验证
                "CstLoginOut", //退出
                "DelOrderItem",//删除购物车项
                "CreateOrder", //提交订单
                "Payed", //支付成功
                "NameLike", //模糊搜索商品
                "FindCategory", //查看分类下的商品
                "Newsadd", //添加资讯
                "LoginMsg", //登陆返回信息
        };

        // Set the request URI
        request.setRequestURI("/fore/foreIndex");

        // 设置 the session attribute，表示登陆了用户
        session.setAttribute("cst", new Customer());

        // 测试，用户登陆情况下
        boolean result = loginInterceptor.preHandle(request, response, null);
        assertTrue(result);

        //移除用户
        session.removeAttribute("cst");

        // 测试 用户没有登陆情况下
        result = loginInterceptor.preHandle(request, response, null);
        assertTrue(result);

        // foreboungt不在数组中，测试需要登陆访问的页面，返回false。
        request.setRequestURI("/fore/forebought");
        result = loginInterceptor.preHandle(request, response, null);
        assertFalse(result);
    }

    // Add more tests if necessary
}
