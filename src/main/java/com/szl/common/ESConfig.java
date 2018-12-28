package com.szl.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunzl
 * @date 2018年12月26日
 *
 */
@Configuration
public class ESConfig {

	@Bean
	public TransportClient client() throws UnknownHostException {
		
        // 9300是es的tcp服务端口
        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("localhost"),
                9300);

        // 设置es节点的配置信息
        Settings settings = Settings.builder()
                .put("cluster.name", "es")
                .build();

        // 实例化es的客户端对象
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);

        return client;
    }
		
}
