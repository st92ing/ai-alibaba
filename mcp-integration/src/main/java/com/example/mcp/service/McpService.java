package com.example.mcp.service;

import com.alibaba.mcp.client.McpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class McpService {

    @Autowired
    private McpClient mcpClient;

    /**
     * 调用MCP服务示例方法
     */
    public String callMcpService(String serviceName, String methodName, Object... params) {
        try {
            return mcpClient.invoke(serviceName, methodName, params);
        } catch (Exception e) {
            log.error("调用MCP服务失败: serviceName={}, methodName={}", serviceName, methodName, e);
            throw new RuntimeException("MCP服务调用失败", e);
        }
    }

    /**
     * 异步调用MCP服务示例方法
     */
    public void callMcpServiceAsync(String serviceName, String methodName, Object... params) {
        mcpClient.invokeAsync(serviceName, methodName, params)
                .thenAccept(result -> log.info("异步调用MCP服务成功: result={}", result))
                .exceptionally(throwable -> {
                    log.error("异步调用MCP服务失败: serviceName={}, methodName={}", serviceName, methodName, throwable);
                    return null;
                });
    }
} 