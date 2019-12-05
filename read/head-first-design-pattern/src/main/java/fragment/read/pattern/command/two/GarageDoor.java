package fragment.read.pattern.command.two;

public class GarageDoor {
    private String name;

    public GarageDoor(String name) {
        this.name = name;
    }

    public void up() {
        System.out.println(name +" garage door up");
    }
    public void down() {
        System.out.println(name +"garage door down");
    }
    public void stop() {
        System.out.println(name +"garage door stop");
    }
    public void lightOn() {
        System.out.println(name +"garage door lightOn");
    }
    public void lightOff() {
        System.out.println(name +"garage door lightOff");
    }
}
