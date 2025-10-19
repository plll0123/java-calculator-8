package calculator.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DelimiterParser {

    static final Pattern CUSTOM_DELIMITER = Pattern.compile("^//([\\s\\S]+)\\\\n");
    static final List<String> DEFAULT_TOKENS = List.of(",", ":");

    public int parse(String source) {
        List<String> tokens = new ArrayList<>(DEFAULT_TOKENS);
        Delimiter customDelimiter = parseCustomDelimiter(source);
        if (customDelimiter != null) {
            tokens.add(customDelimiter.value);
            source = source.substring(customDelimiter.endIndex);
        }
        return doParse(source, tokens);
    }

    Delimiter parseCustomDelimiter(String source) {
        Matcher matcher = CUSTOM_DELIMITER.matcher(source);
        if (!matcher.find()) {
            return null;
        }
        return new Delimiter(matcher.group(1), matcher.end());
    }

    int doParse(String source, List<String> tokens) {
        String regex = tokens.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
        String[] numbers = source.split("[" + regex + "]");
        int result = 0;
        for (String number : numbers) {
            try {
                int parse = Integer.parseInt(number);
                if (parse <= 0) {
                    throw new IllegalArgumentException();
                }
                result += parse;
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        return result;
    }

    record Delimiter(String value, int endIndex) {
    }

}
