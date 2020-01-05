package fragment.read.pattern.command.one;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        remote.setSlot(lightOnCommand);
        remote.buttonWasPressed();
        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand openCommand = new GarageDoorOpenCommand(garageDoor);
        remote.setSlot(openCommand);
        remote.buttonWasPressed();
    }
}
