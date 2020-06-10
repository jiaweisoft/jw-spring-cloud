package com.jw.cloud;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: jiawei
 * @Date: 2020/6/10
 * @Description:
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {      //表明为哪个微服务提供回退，return "*"代表为所有微服务提供回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;       //获取状态码(200,OK)
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;                 //返回数字状态码
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";            //返回字母状态码
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("连接异常".getBytes());     //返回的内容
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();//返回时的Header体的设置
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
