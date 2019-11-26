package fragment.read.pattern.observer.two;

import java.util.Observable;

/***
 * 气象站提供的获取天气信息的类
 */
public class WeatherData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;


    /**
     * 获取温度
     *
     * @return 温度
     */
    public float getTemperature() {
        return this.temperature;
    }

    /**
     * 获取湿度
     *
     * @return 湿度
     */
    public float getHumidity() {
        return this.humidity;
    }

    /**
     * 获取气压
     *
     * @return 气压
     */
    public float getPressure() {
        return this.pressure;
    }

    /**
     * 一旦气象测量更新, 此方法会被调用
     */
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

}
