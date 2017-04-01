package track.container;

import track.container.beans.Car;
import track.container.beans.Engine;
import track.container.beans.Gear;
import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

import java.io.File;
import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        JsonConfigReader reader = new JsonConfigReader();
        try {
            List<Bean> beans = reader.parseBeans(new File("src\\main\\resources\\config.json"));
            for (Bean bean: beans) {
                System.out.println(bean.toString());
            }
            Container container = new Container(beans);
            Car car = (Car) container.getById("carBean");
            Gear gear = (Gear) container.getById("gearBean");
            Engine engine = (Engine) container.getById("engineBean");
            System.out.println(car.toString());
            System.out.println(gear.toString());
            System.out.println(engine.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        /*

        ПРИМЕР ИСПОЛЬЗОВАНИЯ

         */

//        // При чтении нужно обработать исключение
//        ConfigReader reader = new JsonReader();
//        List<Bean> beans = reader.parseBeans("config.json");
//        Container container = new Container(beans);
//
//        Car car = (Car) container.getByClass("track.container.beans.Car");
//        car = (Car) container.getById("carBean");


    }
}
