package lk.ijse.Charleston.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class resavations_detail {
    String Res_ID;
    String Room_ID;
    String Room_price;
    String Guest_ID;
    String Guest_Name;
    Date Check_in_Date;
    Date Check_out_Date;
}
