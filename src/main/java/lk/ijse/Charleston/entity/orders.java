package lk.ijse.Charleston.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class orders {
    String orderId;
    Date date;
    String Res_ID;
}
