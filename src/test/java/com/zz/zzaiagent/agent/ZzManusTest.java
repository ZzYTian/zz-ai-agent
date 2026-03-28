package com.zz.zzaiagent.agent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZzManusTest {

    @Autowired
    private ZzManus zzManus;

    @Test
    void run() {
        String userPrompt = """  
                我的朋友居住在临海市两水小区，请帮我找到 5 公里内合适的约会地点，
                并结合一些网络图片，制定一份详细的约会计划，
                并以 PDF 格式输出""";
        String answer = zzManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }


}