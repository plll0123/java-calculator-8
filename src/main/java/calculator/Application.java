package calculator;

import calculator.reader.DelimiterParser;
import calculator.reader.Reader;
import calculator.writer.Writer;

public class Application {

    public static void main(String[] args) {
        Reader reader = new Reader(new DelimiterParser());
        int source = reader.read();
        new Writer().write(source);
    }

}
