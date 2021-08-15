package fragment.console.cmd;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@CommandLine.Command(name = "test1",aliases = {"te", "test", "tee"},mixinStandardHelpOptions = true, version = "1.0",
        description = {"测试测试1"},
        subcommands = {Nested.class, CommandLine.HelpCommand.class})
@CliCommand
public class Test1 implements Runnable, Callable<Integer> {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + this.getClass() + "runnable");
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + this.getClass() + "callable");
        return 0;
    }
}

@Command(name = "test2", mixinStandardHelpOptions = true, version = "1.0",
        description = {"测试测试22"},
        subcommands = {})
@CliCommand
class Test2 implements Runnable, Callable<Integer> {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + this.getClass() + " runnable");
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + this.getClass() + " callable");
        return 0;
    }
}

@Command(name = "test3")
@CliCommand
class Test3 extends Test2 {

    public Test3() {
        System.out.println(this);
    }

}