class CalculatorConundrum {

    public String calculate(int operand1, int operand2, String operation) throws IllegalOperationException {
        if (operation == null)
            throw new IllegalArgumentException("Operation cannot be null");

        String instruction = operand1 + " " + operation + " " + operand2 + " = ";

        switch (operation) {
            case "+":
                return instruction + (operand1 + operand2);
            case "*":
                return instruction + (operand1 * operand2);
            case "/":
                try {
                    return instruction + (operand1 / operand2);
                } catch (ArithmeticException e) {
                    throw new IllegalOperationException("Division by zero is not allowed", e);
                }
            case "":
                throw new IllegalArgumentException("Operation cannot be empty");
            default:
                throw new IllegalOperationException(String.format("Operation '%s' does not exist", operation));
        }
    }
}
