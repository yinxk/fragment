package cloud.base.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DrivingLicense implements Serializable {

    private static final long serialVersionUID = 4297668291069957937L;
    private String idNumber;

    private String name;
}
