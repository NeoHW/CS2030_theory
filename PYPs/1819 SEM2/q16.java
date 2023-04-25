import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

class Student {
    private int labID;
    private String tutID;

    Student(int labID, String tutID) {
        this.labID = labID;
        this.tutID = tutID;
    }

    public int getLabID() {
        return this.labID;
    }

    public String getTutID() {
        return this.tutID;
    }

    @Override
    public String toString() {
        return labID + " : " + tutID;
    }
}

// (a)
// compare by lab group
class Comp1 implements Comparator<Student> {
    // returns <0 if x comes first, >0 if y comes first
    public int compare(Student x, Student y) {
        return x.getLabID() - y.getLabID();
    }
}

// compare by tutorial group (String e.g. a2, b1)
class Comp2 implements Comparator<Student> {

    // returns <0 if x comes first, >0 if y comes first
    public int compare(Student x, Student y) {
        String xid = x.getTutID();
        String yid = y.getTutID();

        for (int i = 0; i < xid.length(); i++) {
            if(xid.indexOf(i) != yid.indexOf(i)) {
                return xid.indexOf(i) - yid.indexOf(i) < 0 ? -1 : 1;
            }
        }
        return 0;
    }
}

// (b)
List<Comparator<Student>> comparatorList = new ArrayList<Comparator<Student>>(List.of(new Comp1(), new Comp2()));

// (c)
class StudentComparator implements Comparator<Student> {
    
    private final List<Comparator<Student>> comparatorList;

    StudentComparator(List<Comparator<Student>> list) {
        this.comparatorList = list;
    }
    
    public int compare(Student x, Student y) {
        for (Comparator<Student> i : comparatorList) {
            int result = i.compare(x,y);
            if(result != 0) {
                return result;
            }
        }
        return 0;
    }
}

// (d)
List<Student> students = new ArrayList<Student>(List.of(
    new Student(1, "a2"),
    new Student(3, "b1"),
    new Student(3, "a2"),
    new Student(1, "b1")
));

Collections.sort(students, new StudentComparator(comparatorList));

for (Student student : students) {
    System.out.println(student);
}