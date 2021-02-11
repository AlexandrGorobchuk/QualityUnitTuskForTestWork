package gorobchuk.alexandr.enums;

public enum InitialConstant {
    MAXSERVICE(10),
    MAXVARIATION(3),
    MAXQUESTIONS(10),
    MAXQUESTIONSCATEGORY(20),
    MAXQUESTIONSSUBCATEGORY(5),
    ;
    private int value;

    InitialConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
