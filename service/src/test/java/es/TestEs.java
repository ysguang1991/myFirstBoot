package es;

import com.alibaba.fastjson.JSON;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import myboot.ServiceApplication;
import myboot.domain.TestDO;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class TestEs {

    @Autowired
    private JestClient jestClient;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void TestSearch() {

        try {

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            searchSourceBuilder.query(matchAllQueryBuilder);

            SearchResult execute = jestClient.execute(new Search.Builder(searchSourceBuilder.toString())
                    .addIndex("test_es")
                    .addType("test")
                    .build());
            execute.getJsonString();
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    @Test
    public void TestSave() {
        try {
            TestDO testDO = TestDO.builder()
                    .age(10)
                    .name("ysg12")
                    .id(1L)
                    .build();
            Index build = new Index.Builder(testDO).index("test_es").type("test").build();
            DocumentResult execute = jestClient.execute(build);
            execute.getJsonString();
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    @Test
    public void TestDelete() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "ysg");
            searchSourceBuilder.query(termQueryBuilder);
            DeleteByQuery build = new DeleteByQuery.Builder(searchSourceBuilder.toString()).build();
            JestResult execute = jestClient.execute(build);
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    @Test
    public void TestUpdate() {
        try {
            TestDO testDO = TestDO.builder()
                    .id(2L)
                    .age(103)
                    .name("ysg123")
                    .build();
            HashMap<String, TestDO> objectMap = new HashMap();
            objectMap.put("doc", testDO);
            JestResult execute = jestClient.execute(new Update.Builder(JSON.toJSONString(objectMap)).index("test_es").type("test").id("1").build());
            String jsonString = execute.getJsonString();

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    @Test
    public void TestRedis() {
//        redisTemplate.opsForValue().set("ysg", "123");
//        redisTemplate.opsForList().leftPush("ysg_list",0);
        redisTemplate.opsForList().set("ysg_list", 0, 1);
    }
}
