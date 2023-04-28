// Answer q5(b) below. Do not remove this comment.

public static void main(String[] args) {
    City city = new City();
    Scanner scanner = new Scanner(System.in);
    Invoker<City> invoker = new Invoker(new ImList<Command>());

    while (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        Command<City> command = Log.of(parseCommand(userInput));
        invoker.addCommand(command);
    }

    invoker.executeCommand();
}

