package fragment.console.cmd;

import picocli.CommandLine;
import picocli.shell.jline3.PicocliCommands;

/**
 * Top-level command
 */
@CommandLine.Command(name = "", subcommands = {PicocliCommands.ClearScreen.class})
public class TopLevelCliCommand {

}
