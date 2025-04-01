package stack;

public class Main {
    public static void main(String[] args) {
        IntStack intStack = new IntStack(15);
        intStack.push(5);
        intStack.push(15);
        intStack.peek();
        intStack.indexOf(28);
        intStack.push(80);
        intStack.indexOf(80);
        intStack.push(500);
        intStack.pop();
        intStack.dump();
        intStack.push(70);
    }
}
