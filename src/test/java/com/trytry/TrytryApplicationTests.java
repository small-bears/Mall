package com.trytry;


import com.trytry.domain.Refund;
import com.trytry.mapper.RefundMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrytryApplicationTests {

    @Autowired
    RefundMapper refundMapper;

    @Test
    void contextLoads() {
        List<Refund> refunds = refundMapper.selectList(null);
        System.out.println(refunds);

    }

}
