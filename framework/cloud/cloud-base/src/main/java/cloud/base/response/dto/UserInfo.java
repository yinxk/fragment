package cloud.base.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 6705932871950551143L;

    private String name;

    private String sex;

    private Car car;

}
