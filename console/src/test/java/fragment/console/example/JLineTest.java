package fragment.console.example;

import java.io.IOException;
import java.util.HashMap;

import org.jline.reader.Completer;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.widget.TailTipWidgets;

public class JLineTest {
    public static void main(String[] args) throws IOException {
        Completer commandCompleter1 = new StringsCompleter("aa", "bb", "cc", "dd");
        Completer commandCompleter2 = new StringsCompleter("create", "open", "write", "close");
        Completer commandCompleter = new AggregateCompleter(commandCompleter1, commandCompleter2);
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(commandCompleter)
                .option(LineReader.Option.INSERT_BRACKET, true)
                .build();
        TailTipWidgets tailtipWidgets = new TailTipWidgets(reader, new HashMap<>(), 0, TailTipWidgets.TipType.COMBINED);
        tailtipWidgets.enable();
        String prompt = "prompt> ";
        while (true) {
            String line;
            try {
                line = reader.readLine(prompt);
                System.out.println(line);
            } catch (UserInterruptException | EndOfFileException e) {
                System.out.println("Bye.");
                return;
            }
        }
    }
}
