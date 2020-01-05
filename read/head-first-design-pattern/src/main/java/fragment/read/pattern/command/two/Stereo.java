package fragment.read.pattern.command.two;

public class Stereo {
    private String name;

    public Stereo(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + "stereo is on");
    }

    public void setCD() {
        System.out.println(name + "stereo set CD");
    }

    public void setVolume(int volume) {
        System.out.println(name + "stereo set volume is " + volume);
    }

    public void off() {
        System.out.println(name + "stereo is off");
    }
}
