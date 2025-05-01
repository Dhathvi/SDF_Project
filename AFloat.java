package arbitaryarithmetic;

public class AFloat {
    
    private AInteger intPart;
    private AInteger fracPart;
    private boolean isNegative;
    private int scale;

    // Default Constructor
    public AFloat() {
        this.intPart = new AInteger("0");
        this.fracPart = new AInteger("0");
        this.isNegative = false;
        this.scale = 0;
    }

    // Constructor from String
    public AFloat(String s) {

        if (s == null || s.isEmpty()) {
            this.intPart = new AInteger("0");
            this.fracPart = new AInteger("0");
            this.scale = 0;
            this.isNegative = false;
            return;
        }

        s = s.trim();
        if (s.charAt(0) == '-') {
            isNegative = true;
            s = s.substring(1);
        } else {
            isNegative = false;
            if (s.charAt(0) == '+') s = s.substring(1);
        }

        String[] parts = s.split("\\.");
        this.intPart = new AInteger(parts[0]);
        
        if (parts.length > 1) {
            String frac = parts[1].replaceFirst("0+$", ""); // Remove trailing zeros
            this.fracPart = new AInteger(frac.isEmpty() ? "0" : frac);
            this.scale = frac.length();
        } else {
            this.fracPart = new AInteger("0");
            this.scale = 0;
        }

        if (this.intPart.getValue().equals("0") && this.fracPart.getValue().equals("0")) {
            this.isNegative = false;
        }
    }

    // Copy Constructor
    public AFloat(AFloat other) {
        this.intPart = new AInteger(other.intPart);
        this.fracPart = new AInteger(other.fracPart);
        this.isNegative = other.isNegative;
        this.scale = other.scale;
    }

    // Static parse method
    public static AFloat parse(String s) {
        return new AFloat(s);
    }

    public String getvalue(){
        String fracStr = fracPart.getValue();
        while (fracStr.length() < scale) {
            fracStr = "0" + fracStr;
        }

        String val = intPart.getValue() + (scale > 0 ? "." + fracStr : ".0");
        return (isNegative && !val.equals("0.0")) ? "-" + val : val;
    }

    private static AFloat assignScale(AFloat a, AFloat b){
        AFloat result = new AFloat(a);
        int diff = b.scale - a.scale;
        if (diff > 0) {
            String frac = a.fracPart.getValue();
            frac += "0".repeat(diff);
            result.fracPart = new AInteger(frac);
            result.scale = b.scale;
        }
        return result;
    }

    public AFloat add(AFloat val) {
        AFloat a = assignScale(this, val);
        AFloat b = assignScale(val, this);

        AInteger left = new AInteger(a.intPart.getValue() + a.fracPart.getValue());
        AInteger right = new AInteger(b.intPart.getValue() + b.fracPart.getValue());

        if (a.isNegative) left = left.mul(new AInteger("-1"));
        if (b.isNegative) right = right.mul(new AInteger("-1"));

        AInteger result = left.add(right);
        String resStr = result.getValue();
        boolean resNeg = resStr.startsWith("-");
        if (resNeg) resStr = resStr.substring(1);

        while (resStr.length() < a.scale + 1) resStr = "0" + resStr;

        String intPartStr = resStr.substring(0, resStr.length() - a.scale);
        String fracPartStr = resStr.substring(resStr.length() - a.scale);

        while (fracPartStr.length() > 1 && fracPartStr.endsWith("0")) {
            fracPartStr = fracPartStr.substring(0, fracPartStr.length() - 1);
        }
        
        AFloat finalRes = new AFloat();
        finalRes.intPart = new AInteger(intPartStr);
        finalRes.fracPart = new AInteger(fracPartStr);
        finalRes.scale = a.scale;
        finalRes.isNegative = resNeg && !(intPartStr.equals("0") && fracPartStr.equals("0"));
        return finalRes;
    }

    public AFloat sub(AFloat val) {
        AFloat neg = new AFloat(val);
        neg.isNegative = !val.isNegative;
        return this.add(neg);
    }

    public AFloat mul(AFloat val){
        String fracStr = this.fracPart.getValue();
        while (fracStr.length()<this.scale) {
            fracStr = "0" + fracStr;
        }

        String a = this.intPart.getValue() + fracStr;

        String fracStr2 = val.fracPart.getValue();
        while (fracStr2.length()<val.scale) {
            fracStr2 = "0" + fracStr2;
        }

        String b = val.intPart.getValue() + fracStr2;

        AInteger aInt = new AInteger(a);
        AInteger bInt = new AInteger(b);

        AInteger product = aInt.mul(bInt);
        int totalScale = this.scale + val.scale;

        String producStr = product.getValue();

        while (producStr.length()< totalScale + 1) {
            producStr = "0" + producStr;
        }

        String intPartStr = producStr.substring(0, producStr.length() - totalScale);
        String fracPartStr = producStr.substring(producStr.length() - totalScale);

        while (fracPartStr.length() > 1 && fracPartStr.endsWith("0")) {
            fracPartStr = fracPartStr.substring(0, fracPartStr.length() - 1);
        }

        AFloat result = new AFloat();
        result.intPart = new AInteger(intPartStr);
        result.fracPart = new AInteger(fracPartStr);
        result.scale = fracPartStr.length();
        result.isNegative = this.isNegative != val.isNegative;

        return result;
    }

    public AFloat div(AFloat val){
        if (val.intPart.getValue().equals("0") && val.fracPart.getValue().equals("0")){
            throw new ArithmeticException("Division by zero");
        }

        String fracStr = this.fracPart.getValue();
        while (fracStr.length()<this.scale) {
            fracStr = "0" + fracStr;
        }

        String a = this.intPart.getValue() + fracStr;

        String fracStr2 = val.fracPart.getValue();
        while (fracStr2.length()<val.scale) {
            fracStr2 = "0" + fracStr2;
        }

        String b = val.intPart.getValue() + fracStr2;

        int changedScale = this.scale - val.scale + 30;

        AInteger dividend = new AInteger(a + "0".repeat(changedScale));
        AInteger divisor = new AInteger(b);

        AInteger quot = dividend.div(divisor);
        String qStr = quot.getValue();

        while (qStr.length() < 31) qStr = "0" + qStr;

        String intPartStr = qStr.substring(0, qStr.length() - 30);
        String fracPartStr = qStr.substring(qStr.length() - 30);

        // Trim trailing zeros but preserve at least one digit
        while (fracPartStr.length() > 1 && fracPartStr.endsWith("0")) {
            fracPartStr = fracPartStr.substring(0, fracPartStr.length() - 1);
        }

        AFloat result = new AFloat();
        result.intPart = new AInteger(intPartStr);
        result.fracPart = new AInteger(fracPartStr);
        result.scale = fracPartStr.length();
        result.isNegative = this.isNegative != val.isNegative;

        return result;

    }

}