package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 토큰 : cos 이걸 만들어줘야함. id, pw 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답을 해준다.
        // 요청할 때마다 header에 Authorization에 value값으로 토큰을 가져옮.
        // 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지 검증해주면 됨.(RSA, HS256)
        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("필터 3");

            if(headerAuth.equals("cos")){
                chain.doFilter(req, resp);
            }else{
                PrintWriter out = resp.getWriter();
                out.println("인증 안됨");
            }
        }

    }
}
