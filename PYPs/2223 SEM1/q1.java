// Write your entire solution in this file only.

import java.util.Random;
import java.util.List;

// use an interface to avoid cyclic dependencies
interface Notifiable {
    void notify(String s);
}

class User implements Notifiable {
    private final String username;

    User(String username) {
        this.username = username;
    }

    Bot join(Bot bot) {
        return bot.join(this);
    }

    // methods which override interface methods needs to be PUBLIC
    public void notify(String s) {
        System.out.println(this.username + ": " + s);
    }

    @Override
    public String toString() {
        return username;
    }
}

class Bot {
    private final String id;
    private final ImList<Notifiable> users;

    Bot() {
        this.id = new Random().nextInt(1000) + ""; // to make it string instead of int
        this.users = new ImList<Notifiable>();
    }

    Bot(String id, ImList<Notifiable> users) {
        this.id = id;
        this.users = users;
    }

    Bot(List<Notifiable> users) {
        this.id = new Random().nextInt(1000) + ""; // to make it string instead of int
        this.users = new ImList<Notifiable>(users); // note that ImList<>(list) is a constructor
    }

    Bot join(Notifiable user) {
        String s = this.toString() + " says hi to " + user.toString();
        
        Bot newBot = new Bot(id, this.users.add(user));
        
        for (Notifiable u : newBot.users) {
            u.notify(s);
        }
        return newBot;
    }

    @Override
    public String toString() {
        return String.format("bot@%s", id);
    }
}

