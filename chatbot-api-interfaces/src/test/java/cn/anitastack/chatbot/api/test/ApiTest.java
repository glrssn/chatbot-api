package cn.anitastack.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 小米学JAVA
 * @description 单元测试
 * @version 1.0
 * @creats at 2024-09-11-21:30
 */
public class ApiTest {
    //查询知识星球里待回答的问题的接口并处理
    @Test
    public void query_unanswered_questions() throws IOException {
        //封装数据信息
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88888418528522/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","zsxq_access_token=E722D634-B439-7BD9-FDA2-0559FC9725FB_077CDF5746D20ED0; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585551421425544%22%2C%22first_id%22%3A%22191e6676e66604-0407a7c97fc1124-26001151-921600-191e6676e671ad9%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxZTY2NzZlNjY2MDQtMDQwN2E3Yzk3ZmMxMTI0LTI2MDAxMTUxLTkyMTYwMC0xOTFlNjY3NmU2NzFhZDkiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODU1NTE0MjE0MjU1NDQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585551421425544%22%7D%7D; abtest_env=product; zsxqsessionid=87b4e678245942e5dd4e404a1abe59fc");
        get.addHeader("Content-Type","application/json; charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/5121188425452554/answer");
        post.addHeader("cookie", "zsxq_access_token=E722D634-B439-7BD9-FDA2-0559FC9725FB_077CDF5746D20ED0; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585551421425544%22%2C%22first_id%22%3A%22191e6676e66604-0407a7c97fc1124-26001151-921600-191e6676e671ad9%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxZTY2NzZlNjY2MDQtMDQwN2E3Yzk3ZmMxMTI0LTI2MDAxMTUxLTkyMTYwMC0xOTFlNjY3NmU2NzFhZDkiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODU1NTE0MjE0MjU1NDQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585551421425544%22%7D%7D; abtest_env=product; zsxqsessionid=87b4e678245942e5dd4e404a1abe59fc");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        //组装数据信息
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"晚安，祝你假期愉快！\\n\",\n" +
                "    \"image_ids\": []\n" +
                "  }\n" +
                "}";

        //调用接口并回答
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
