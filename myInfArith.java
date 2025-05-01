import arbitaryarithmetic.AInteger;
import arbitaryarithmetic.AFloat;


public class myInfArith {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java myInfArith <int|float> <add|sub|mul|div> <op1> <op2>");
            return;
        }

        String type = args[0].toLowerCase();
        String operation = args[1].toLowerCase();
        String op1 = args[2];
        String op2 = args[3];

        try {
            if (type.equals("int")) {
                AInteger a = new AInteger(op1);
                AInteger b = new AInteger(op2);
                AInteger result;

                switch (operation) {
                    case "add": result = a.add(b); break;
                    case "sub": result = a.sub(b); break;
                    case "mul": result = a.mul(b); break;
                    case "div": result = a.div(b); break;
                    default:
                        System.out.println("Invalid operation for int.");
                        return;
                }

                System.out.println(result.getValue());
            } else if (type.equals("float")) {
                AFloat a = new AFloat(op1);
                AFloat b = new AFloat(op2);
                AFloat result;

                switch (operation) {
                    case "add": result = a.add(b); break;
                    case "sub": result = a.sub(b); break;
                    case "mul": result = a.mul(b); break;
                    case "div": result = a.div(b); break;
                    default:
                        System.out.println("Invalid operation for float.");
                        return;
                }

                System.out.println(result.getvalue());
            } else {
                System.out.println("Invalid type. Use 'int' or 'float'.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
