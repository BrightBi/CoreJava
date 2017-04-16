package chapter3;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigNumber {

    public static void main(String[] args) {
        BigInteger bin = BigInteger.valueOf(10);
        BigDecimal bde = BigDecimal.valueOf(1.0);
        System.out.println(bin);
        System.out.println(bde);
    }
}