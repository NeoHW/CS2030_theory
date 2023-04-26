import java.util.List;
import java.util.ArrayList;

abstract class Person {
    private final String name;
    private final Person partner;

    Person(String name) {
        this.name = name;
        this.partner = null;
    }

    Person(String name, Person partner) {
        this.name = name;
        this.partner = partner;
    }

    boolean inRS() {
        return (partner == null) ? false : true;
    }

    String getName() {
        return this.name;
    }

    String getPartnerName() {
        return this.partner.getName();
    }

    abstract Person date(Person person);

    abstract Person breakup();

    abstract boolean isMen();
}

class Man extends Person {
    Man(String name) {
        super(name);
    }

    Man(String name, Person partner) {
        super(name , partner);
    }

    Person date(Person person) {
        return new Man(super.getName(), person);
    }

    Person breakup() {
        return new Man(super.getName());
    }

    boolean isMen() {
        return true;
    }

    public String toString() {
        return super.getName() + " (M)";
    }
}

class Woman extends Person {
    Woman(String name) {
        super(name);
    }

    Woman(String name, Person partner) {
        super(name , partner);
    }

    Person date(Person person) {
        return new Man(super.getName(), person);
    }

    Person breakup() {
        return new Man(super.getName());
    }

    boolean isMen() {
        return false;
    }

    public String toString() {
        return super.getName() + " (W)";
    }
}

class System {
    private final ArrayList<Person> list;

    System(ArrayList<Person> list) {
        this.list = list;
    }

    public void add(Person person) {
        this.list.add(person);
    }

    public void setRelationship(final String name1, final String name2) {
        Person p1 = getPersonByName(name1, list);
        Person p2 = getPersonByName(name2, list);
        p1.breakup();
        p2.breakup();
        Person newMan = p1.date(p2);
        Person newWoman = p2.date(newMan);
        this.list.set(this.list.indexOf(p1), newMan);
        this.list.set(this.list.indexOf(p2), newWoman);
    }

    private Person getPersonByName(String name, List<Person> people) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public int getNumRelationships() {
        int count = 0;
        for(Person i : list) {
            if(i.inRS()) {
                count++;
            }
        }
        return count;
    }

    public int getNumMen() {
        int count = 0;
        for(Person i : list) {
            if(i.isMen()) {
                count++;
            }
        }
        return count;
    }

    public int getNumWomen() {
        return this.list.size() - this.getNumMen();
    }

    @Override
    public String toString() {

        String output = String.format("Number of relationships: %d%n Number of men : %d%n Number of women : %d%n%n", 
            getNumRelationships(), getNumMen(), getNumWomen());

        // need to print out who and who in a rs in order!
        for (Person i : list) {
            if (i.inRS()) {
                output = output + String.format("%s and %s are in a relationship", i, i.getPartnerName());
            } else {
                output = output + String.format("%s is not in a relationship", i);
            }
        }
        
        return output;
    }

}

