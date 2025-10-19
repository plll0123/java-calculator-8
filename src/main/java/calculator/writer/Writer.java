package calculator.writer;

public class Writer {

    static final String OUTPUT_FORMAT = "결과 : %d";

    public void write(int number) {
        doWrite(number);
    }

    void doWrite(int number) {
        System.out.printf(OUTPUT_FORMAT, number);
    }

}
