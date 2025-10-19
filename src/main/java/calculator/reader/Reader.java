package calculator.reader;

import camp.nextstep.edu.missionutils.Console;

public class Reader {

    private final DelimiterParser delimiterParser;

    public Reader(DelimiterParser delimiterParser) {
        this.delimiterParser = delimiterParser;
    }

    public int read() {
        String source = doRead();
        if (source == null || source.isBlank()) {
            return 0;
        }
        return delimiterParser.parse(source);
    }

    String doRead() {
        return Console.readLine();
    }

}
