package person.andy;

import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by AndyCui on 2017/11/21.
 *
 * @Description 使用mockMvc对controller进行测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloServiceApplication.class)
public class HelloControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception{
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
     public void helloTest() throws Exception{
       MvcResult result= mockMvc.perform(
               //注意这里的路径直接是项目里的路径 而不是127.0.0.1：2221/hello-service/hello
               //spring boot项目单独启动 访问不到注册中心的情况下controller仍然能访问到
                MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print()).andReturn();
         System.err.println(result.getResponse().getContentAsString());
    }
}
