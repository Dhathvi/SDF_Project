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

        String val = intPart.getValue() + (scale > 0 ? "." + fracStr : "");
        return (isNegative && !val.equals("0")) ? "-" + val : val;
    }

    private static AFloat assignScale(AFloat a, AFloat b){
        AFloat result = new AFloat(a);
        int diff = a.scale - b.scale;
        if (diff > 0) {
            result.fracPart = new AInteger(a.fracPart.getValue() + "0".repeat(diff));
            result.scale = b.scale + diff;
        } else if (diff < 0) {
            result.fracPart = new AInteger(a.fracPart.getValue() + "0".repeat(-diff));
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

    

}