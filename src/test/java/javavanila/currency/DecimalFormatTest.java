package javavanila.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by SYSTEM on 06.02.2019.
 */

public class DecimalFormatTest {

    @Test
    public void gsfdgsdfgsdfg()  {

        BigDecimal bigDecimal = new BigDecimal(0);
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        System.out.println(decimalFormat.format(bigDecimal));



    }
}
