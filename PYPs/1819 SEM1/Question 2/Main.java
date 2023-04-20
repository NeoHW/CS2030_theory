import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Console {
    private String id;
    private Logic logic;
    static List<Console> consoles;

    Console(String id, Logic logic) {
        this.id = id;
        this.logic = logic;
        consoles.add(this);
    }

    void start() {
        Scanner sc = new Scanner(System.in);
        String command;
        String feedbackMesg;
        do {
            System.out.print("Enter a command: ");
            command = sc.next();
            feedbackMesg = logic.invoke(command);
            for (int i = 0; i < consoles.size(); i ++) {
                Console theConsole = consoles.get(i);
                theConsole.feedback(feedbackMesg);
            }
        } while (!command.equals("exit"));
    }

    void feedback(String mesg) {
        System.out.println(id + ": " + mesg);
    }
}

class Logic {
    String invoke(String command) {
        // wishful thinking
        return (command + " executed");
    }
}

class Main {
    public static void main(String[] args) {
        Console.consoles = new ArrayList<Console>(); 
        Logic logic = new Logic();
        Console console = new Console("Primary", logic);
        Console console2 = new Console("Secondary", logic);
        console.start();
    }
}
