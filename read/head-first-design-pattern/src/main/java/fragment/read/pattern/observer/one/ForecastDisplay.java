package fragment.read.pattern.observer.one;

public class ForecastDisplay implements Observer, DisplayElement {
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {

    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

    }
}
