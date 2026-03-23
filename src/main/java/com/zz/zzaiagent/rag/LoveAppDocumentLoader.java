package com.zz.zzaiagent.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 恋爱知识库文档加载器
 * 基于 Spring AI ETL 加载 document 目录下的 Markdown 文件
 */
@Slf4j
@Component
public class LoveAppDocumentLoader {
    private final ResourcePatternResolver resourcePatternResolver;

    LoveAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns() {
        List<Document> allDocuments = new ArrayList<>();
        try {
            // 这里可以修改为你要加载的多个 Markdown 文件的路径模式
            Resource[] resources = resourcePatternResolver.getResources("classpath:document/*.md");
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("filename", fileName)
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                allDocuments.addAll(reader.get());
            }
        } catch (IOException e) {
            log.error("Markdown 文档加载失败", e);
        }
        return allDocuments;
    }
}

/*
  claude生成
 */
//public class LoveAppDocumentLoader {
//
//    private static final String DOCUMENT_PATH = "classpath:document/*.md";
//
//    /**
//     * 加载所有 Markdown 文档
//     * @return 文档列表
//     */
//    public List<Document> loadMarkdownDocuments() {
//        List<Document> documents = new ArrayList<>();
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//
//        try {
//            Resource[] resources = resolver.getResources(DOCUMENT_PATH);
//            for (Resource resource : resources) {
//                log.info("Loading document: {}", resource.getFilename());
//                List<Document> docs = loadMarkdownDocument(resource);
//                documents.addAll(docs);
//            }
//            log.info("Total documents loaded: {}", documents.size());
//        } catch (IOException e) {
//            log.error("Failed to load markdown documents", e);
//        }
//
//        return documents;
//    }
//
//    /**
//     * 加载单个 Markdown 文档
//     * @param resource 文档资源
//     * @return 文档列表
//     */
//    public List<Document> loadMarkdownDocument(Resource resource) {
//        MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
//                .withHorizontalRuleCreateDocument(true)
//                .withIncludeCodeBlock(false)
//                .withIncludeBlockquote(false)
//                .withAdditionalMetadata("source", resource.getFilename())
//                .build();
//
//        MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
//        return reader.get();
//    }
//}
