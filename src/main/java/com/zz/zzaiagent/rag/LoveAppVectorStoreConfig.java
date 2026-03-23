package com.zz.zzaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 向量数据库配置（初始化给予向量数据库Bean）
 */
@Configuration
public class LoveAppVectorStoreConfig {

    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    // 通过 @Bean 初始化 VectorStore，并在 Bean 创建阶段完成文档向量化，实现系统启动即完成知识库加载。
    @Bean
    VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        simpleVectorStore.add(loveAppDocumentLoader.loadMarkdowns());
        return simpleVectorStore;
    }


}
