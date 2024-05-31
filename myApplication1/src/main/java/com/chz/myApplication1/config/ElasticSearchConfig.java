package com.chz.myApplication1.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.chz.myApplication1.entity.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

@Slf4j
@Data
public class ElasticSearchConfig {

    public static void main(String[] args) throws IOException {
        // URL and API key
        String serverUrl = "http://192.168.44.228:9200";
        String apiKey = "VnVhQ2ZHY0JDZGJrU...";

        // Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);

//        CreateIndexResponse createIndexResponse = esClient.indices().create(builder -> builder.index("products"));
//        System.out.println(createIndexResponse);

        Product product = new Product("bk-1", "City bike", 123.0);

        IndexResponse response = esClient.index(builder -> builder
                .index("products")
                .id(product.getSku())
                .document(product)
        );

        log.info("Indexed with version " + response.version());


    }
}