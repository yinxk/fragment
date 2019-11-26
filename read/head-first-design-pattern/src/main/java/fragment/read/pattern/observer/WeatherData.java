package fragment.read.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 气象站提供的获取天气信息的类
 */
public class WeatherData implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * 获取温度
     *
     * @return 温度
     */
    public float getTemperature() {
        return new Random().nextFloat();
    }

    /**
     * 获取湿度
     *
     * @return 湿度
     */
    public float getHumidity() {
        return new Random().nextFloat();
    }

    /**
     * 获取气压
     *
     * @return 气压
     */
    public float getPressure() {
        return new Random().nextFloat();
    }

    /**
     * 一旦气象测量更新, 此方法会被调用
     */
    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
