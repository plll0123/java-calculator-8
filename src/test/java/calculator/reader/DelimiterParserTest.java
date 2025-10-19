package calculator.reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import calculator.reader.DelimiterParser.Delimiter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DelimiterParserTest {

    private DelimiterParser delimiterParser = new DelimiterParser();

    static Stream<Arguments> cases() {
        return Stream.of(
                arguments("1,2:3", List.of(",", ":"), 6),
                arguments("1!2,3:4", List.of("!", ",", ":"), 10)
        );
    }

    @Test
    void 사용자_지정_구분자_조회() {
        String source1 = "//a\\n1a2";
        Delimiter delimiter = delimiterParser.parseCustomDelimiter(source1);
        assertThat(delimiter.value()).isEqualTo("a");
        assertThat(delimiter.endIndex()).isEqualTo(5);

        String source2 = "1,2";
        assertThat(delimiterParser.parseCustomDelimiter(source2)).isNull();
    }

    @MethodSource("cases")
    @ParameterizedTest
    void 문자열_파싱후_총합_반환(String source, List<String> delimiters, int expectResult) {
        assertThat(delimiterParser.doParse(source, delimiters)).isEqualTo(expectResult);
    }

    @Test
    void 구분를_기준으로_변환하여_합을_반환() {
        assertThat(delimiterParser.parse("1,2:3")).isSameAs(6);
        assertThat(delimiterParser.parse("//!\\n1!2")).isSameAs(3);
        assertThat(delimiterParser.parse("//!\\n1,2:3")).isSameAs(6);
    }

    @Test
    void 예외케이스_테스트() {
        assertThatThrownBy(() -> delimiterParser.parse("-11,2:3"));
        assertThatThrownBy(() -> delimiterParser.parse("0,2:3"));
        assertThatThrownBy(() -> delimiterParser.parse("1!2,3"))
                .hasRootCauseInstanceOf(NumberFormatException.class);
    }

}