package com.ding.hddk.elasticsearch.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;

public class ElasticSearchClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchClientUtil.class);

    /**
     * 配置连接池
     *
     * @return
     */
    public static RestClient getRestClient() {
        return RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")).build();
    }

    /**
     * 发送post请求
     *
     * @param endpoint 地址
     * @param dsl      查询语句
     * @return 返回对象
     */
    public static Response sendPost(String endpoint, String dsl) {
        LOGGER.info("elasticsearch请求参数==> \n endpoint:{} \n dsl:{}", endpoint, dsl);
        HttpEntity entity = new NStringEntity(dsl, ContentType.APPLICATION_JSON);
        Response response = null;
        try (RestClient client = getRestClient()) {
            response = client.performRequest("POST", endpoint, Collections.emptyMap(), entity);
        } catch (IOException e) {
            LOGGER.error("访问服务器错误.");
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 将返回的结果转换成jsonNode
     *
     * @param endpoint
     * @param dsl
     * @return
     */
    public static JsonNode sendPostJsonNode(String endpoint, String dsl) {
        Response response = sendPost(endpoint, dsl);
        if (response != null) {
            return parseEntityToJsonNode(response);
        } else {
            LOGGER.warn("获取为空.");
            return null;
        }
    }

    /**
     * 解析entity,并以jsonNode形式返回
     *
     * @param response 请求完返回的对象
     * @return JsonNode根对象, 包含所有数据
     */
    public static JsonNode parseEntityToJsonNode(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(response.getEntity().getContent());
        } catch (JsonProcessingException | UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    /**
     * 获取hits数组
     *
     * @param jsonNode 根对象
     * @return hits字段包含的数组
     */
    public static JsonNode getHitsArray(JsonNode jsonNode) {
        return jsonNode.get("hits").get("hits");
    }
}
