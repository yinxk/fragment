package fragment.console.runner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import org.fusesource.jansi.AnsiConsole;
import org.jline.console.SystemRegistry;
import org.jline.console.impl.Builtins;
import org.jline.console.impl.SystemRegistryImpl;
import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.Parser;
import org.jline.reader.Reference;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.widget.TailTipWidgets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fragment.console.cmd.CliCommand;
import fragment.console.cmd.TopLevelCliCommand;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliCommands;

@Service
@Order
public class TerminalRunner implements CommandLineRunner {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TerminalRunner.class);

    private final ApplicationContext applicationContext;

    public TerminalRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(CliCommand.class);
        run(beansWithAnnotation.values());
    }

    public void run(Collection<Object> subCommands) throws IOException {
        try {
            AnsiConsole.systemInstall();
        } catch (Exception e) {
            LOG.warn("ansi console install fail", e);
        }
        try {
            Supplier<Path> workDir = () -> Paths.get(System.getProperty("user.dir"));
            Builtins builtins = new Builtins(workDir, null, null);
            TopLevelCliCommand commands = new TopLevelCliCommand();

            PicocliCommands.PicocliCommandsFactory factory = new PicocliCommands.PicocliCommandsFactory();
            CommandLine cmd = new CommandLine(commands, factory);
            for (Object subCommand : subCommands) {
                cmd.addSubcommand(subCommand);
            }
            PicocliCommands picocliCommands = new PicocliCommands(cmd);
            Parser parser = new DefaultParser();
            AnsiConsole.systemInstall();
            try (Terminal terminal = TerminalBuilder.builder().build()) {
                SystemRegistry systemRegistry = new SystemRegistryImpl(parser, terminal, workDir, null);
                systemRegistry.setCommandRegistries(builtins, picocliCommands);

                LineReader reader = LineReaderBuilder.builder()
                        .terminal(terminal)
                        .completer(systemRegistry.completer())
                        .parser(parser)
                        .variable(LineReader.LIST_MAX, 50)   // max tab completion candidates
                        .build();
                builtins.setLineReader(reader);
                factory.setTerminal(terminal);
                TailTipWidgets widgets = new TailTipWidgets(reader, systemRegistry::commandDescription, 5, TailTipWidgets.TipType.COMPLETER);
                widgets.enable();
                KeyMap<Binding> keyMap = reader.getKeyMaps().get("main");
                keyMap.bind(new Reference("tailtip-toggle"), KeyMap.alt("s"));

                String prompt = "prompt> ";
                String line;
                while (true) {
                    try {
                        systemRegistry.cleanUp();
                        line = reader.readLine(prompt, null, (MaskingCallback) null, null);
                        systemRegistry.execute(line);
                    } catch (UserInterruptException e) {
                        //ignore
                    } catch (EndOfFileException e) {
                        return;
                    } catch (Exception e) {
                        systemRegistry.trace(e);
                    }
                }
            }
        } finally {
            AnsiConsole.systemUninstall();
        }
    }

}
