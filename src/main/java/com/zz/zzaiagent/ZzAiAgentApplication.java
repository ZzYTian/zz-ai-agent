package com.zz.zzaiagent;

import com.zz.zzaiagent.rag.PgVectorVectorStoreConfig;
import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PgVectorStoreAutoConfiguration.class)
public class ZzAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzAiAgentApplication.class, args);
    }

}
