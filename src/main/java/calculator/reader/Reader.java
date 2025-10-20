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
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }

}
