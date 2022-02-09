public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length >= 2) {
            String hello = "Hello " + args[0] + " and " + args[1];
            String goodbye = "Goodbye " + args[1] + " and " + args[0];
            System.out.println(hello);
            System.out.println(goodbye);
        }
    }
}
