// package fragment.console.cmd;
//
// import org.springframework.shell.standard.ShellComponent;
// import org.springframework.shell.standard.ShellMethod;
// import org.springframework.shell.standard.ShellOption;
//
// @ShellComponent
// public class MyCommands {
//
//     @ShellMethod(value = "Add two integers together.", key = {"add", "a", "test"})
//     public int add(int a, int b) {
//         return a + b;
//     }
//
//     @ShellMethod("Display stuff.")
//     public String echo(int a, int b, int c) {
//         return String.format("You said a=%d, b=%d, c=%d", a, b, c);
//     }
//
//     @ShellMethod(value = "Display stuff.", prefix = "-")
//     public String echo1(int a, int b, @ShellOption(value = "--third", help = "djdjsjkkjsd") int c) {
//         return String.format("You said a=%d, b=%d, c=%d", a, b, c);
//     }
//
//     @ShellMethod("Describe a command.")
//     public String help2(@ShellOption({"-C", "--command"}) String command) {
//         System.out.println(command);
//         return command;
//     }
//
// }