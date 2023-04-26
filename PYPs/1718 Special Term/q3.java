class QA {
    String question;
    char answer;

    public QA(String question) {
        this.question = question;
    }

    void getAnswer() {
        return;
    }
}

class MCQ extends QA {

    public MCQ(String question) {
        super(question);
    }

    @Override
    public void getAnswer() {
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer < 'A' || answer > 'E') {
            throw new InvalidMCQException("Invalid MCQ answer");
        }
    }
}

class TFQ extends QA {

    public TFQ(String question) {
        super(question);
    }

    @Override
    public void getAnswer() {
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer != 'T' && answer != 'F') {
            throw new InvalidTFQException("Invalid TFQ answer");
        }
    }
}

class InvalidMCQException extends IllegalArgumentException {
    public InvalidMCQException(String mesg) {
        super(mesg);
    }
}

class InvalidTFQException extends IllegalArgumentException {
    public InvalidTFQException(String mesg) {
        super(mesg);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            QA mcq = new MCQ("What is the answer to this MCQ?");
            QA tfq = new TFQ("What is the answer to this TFQ?");
            mcq.getAnswer();
            tfq.getAnswer();
        } catch (InvalidMCQException ex) {
            System.err.println(ex);
        } catch (InvalidTFQException ex) {
            System.err.println(ex);
        }
    }
}