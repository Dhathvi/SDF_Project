package arbitaryarithmetic;

import java.math.BigInteger;

public class AInteger {
    
    private BigInteger value;

    // Default Constructor
    public AInteger() {
        this.value = BigInteger.ZERO;
    }

    // Constructor from String
    public AInteger(String s) {
        try {
            this.value = new BigInteger(s.trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid BigInteger format: " + s);
            this.value = BigInteger.ZERO;
        }
    }

    // Copy Constructor
    public AInteger(AInteger other) {
        this.value = other.value;
    }

    // Static parse method
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    public AInteger add (AInteger val) {
        BigInteger result = this.value.add(val.value);
        return new AInteger(result.toString());
    }

    public AInteger sub (AInteger val) {
        BigInteger result = this.value.subtract(val.value);
        return new AInteger(result.toString());
    }

    public AInteger mul (AInteger val) {
        BigInteger result = this.value.multiply(val.value);
        return new AInteger(result.toString());
    }

    public AInteger div(AInteger val) {
        if (val.value.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Division by zero");
        }
        BigInteger result = this.value.divide(val.value);
        return new AInteger(result.toString());
    }
    
}
