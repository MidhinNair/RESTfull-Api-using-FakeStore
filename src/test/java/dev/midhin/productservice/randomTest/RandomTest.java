package dev.midhin.productservice.randomTest;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
public class RandomTest {
    @Test
    void testLessThan3() {

        //flaky test example
        Random random = new Random();
        int num = random.nextInt();
        assert (num < 100000000);
        System.out.println("Random number generated: " + num);
        assertTrue(1 + 1 < 3);
        System.out.println("1 + 1 is less than 3");
    }

}
