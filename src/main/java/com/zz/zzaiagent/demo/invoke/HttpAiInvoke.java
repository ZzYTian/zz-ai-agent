package com.zz.zzaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpAiInvoke {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1. 准备请求地址和 API Key
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String apiKey = TestApiKey.API_KEY; // 替换为真实的 key

        // 2. 构建请求体 (Payload)
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "qwen-plus");

        // 构建 input 层
        Map<String, Object> input = new HashMap<>();
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(createMessage("system", "You are a helpful assistant."));
        messages.add(createMessage("user", "你是谁？"));
        input.put("messages", messages);
        bodyMap.put("input", input);

        // 构建 parameters 层
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("result_format", "message");
        bodyMap.put("parameters", parameters);

        // 3. 发送请求
        String jsonBody = JSONUtil.toJsonStr(bodyMap);
        
        HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .execute();

        // 4. 处理结果
        if (response.isOk()) {
            // Hutool 默认使用 UTF-8 打印，能解决你之前的乱码困扰
            System.out.println("请求成功：");
            System.out.println(JSONUtil.formatJsonStr(response.body()));
        } else {
            System.err.println("请求失败，状态码：" + response.getStatus());
            System.err.println("错误详情：" + response.body());
        }
    }

    private static Map<String, String> createMessage(String role, String content) {
        Map<String, String> msg = new HashMap<>();
        msg.put("role", role);
        msg.put("content", content);
        return msg;
    }
}