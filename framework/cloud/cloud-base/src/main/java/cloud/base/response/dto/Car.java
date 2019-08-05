package cloud.base.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Car implements Serializable {

    private static final long serialVersionUID = 7610844787665977354L;
    private String brand;

    private String type;

    private String price;

    private DrivingLicense drivingLicense;

}
