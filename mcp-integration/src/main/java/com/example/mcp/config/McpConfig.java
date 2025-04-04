package com.example.mcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import com.alibaba.mcp.client.McpClient;

@Configuration
public class McpConfig {
    
    @Value("${mcp.server.url}")
    private String mcpServerUrl;
    
    @Value("${mcp.app.key}")
    private String appKey;
    
    @Value("${mcp.app.secret}")
    private String appSecret;

    @Bean
    public McpClient mcpClient() {
        return McpClient.builder()
                .serverUrl(mcpServerUrl)
                .appKey(appKey)
                .appSecret(appSecret)
                .build();
    }
} 