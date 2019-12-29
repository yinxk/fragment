package fragment.sequence.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fragment.sequence.SequenceApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = SequenceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SequenceGeneratorUtilTest {

    @Test
    public void testGetValue() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(SequenceGeneratorUtil.nextVal("testSequence1"));
        }
    }
}