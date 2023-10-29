package lk.ijse.Charleston.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class resavations {
    String Res_ID;
    String Guest_ID;
    String Room_ID;
    double Room_price;
    Date Check_in_Date;
    Date Check_out_Date;

}
