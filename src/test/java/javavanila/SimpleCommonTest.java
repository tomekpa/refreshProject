package javavanila;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCommonTest {

    @Test
    public void shouldBeValudUuid() throws Exception {
        UUID uuid1 = UUID.fromString("abababab-ffff-eeee-aaaa-bbbbbbbbbbbb");
        System.out.println(uuid1.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRefuseToParseUuidIfNotAHexIsIncluded() throws Exception {
        UUID uuid = UUID.fromString("gggggggg-ffff-eeee-aaaa-bbbbbbbbbbbb");
    }
}
