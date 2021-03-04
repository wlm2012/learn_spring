package com.test.study.util.WebClient;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClient {

  @RequestMapping("/client")
  public void invokeService() throws Exception {
    // 创建动态客户端
    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    Client client = dcf.createClient("http://localhost:8080/archiveServices/ws/api?wsdl");
    // 需要密码的情况需要加上用户名和密码
    // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));

    // invoke("方法名",参数1,参数2,参数3....);
    // 这里注意如果是复杂参数的话，要保证复杂参数可以序列化
    Object[] objects = client.invoke("getUsers");
    System.out.println("返回数据:" + objects[0]);
  }
}
