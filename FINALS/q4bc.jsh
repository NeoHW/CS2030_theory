// Answer q4(b) below. Do not remove this comment.

static Command<City> parseCommand(String userInput) {
    String commandString = getFirstWord(userInput);
    if (commandString.equalsIgnoreCase("updateroute")) {
        return new UpdateRoute(splitParameters(userInput));
    } else if (commandString.equalsIgnoreCase("getdistance")) {
        return new GetDistance(splitParameters(userInput));
    } else {
        return new Command<City> {
            public City execute(City city) {
                System.out.println(String.format(MESG_INVALID_FORMAT, userInput));
                return city;
            }
        };
    }
}




// Answer q4(c) below. Do not remove this comment.

public static void main(String[] args) {
    City city = new City();
    Scanner scanner = new Scanner(System.in);
    Invoker<City> invoker = new Invoker(new ImList<Command>());

    while (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        Command<City> command = parseCommand(userInput);
        invoker.addCommand(command);
    }

    invoker.executeCommand();
}



