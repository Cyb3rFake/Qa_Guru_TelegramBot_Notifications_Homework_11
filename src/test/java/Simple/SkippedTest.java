package Simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Tag("simple")
public class SkippedTest {


    @Disabled
    @Test
    void test00(){
        assertTrue(true);
    }
    @Disabled
    @Test
    void test01(){
        assertTrue(false);
    }
    @Disabled
    @Test
    void test02(){
        assertTrue(false);
    }
}
