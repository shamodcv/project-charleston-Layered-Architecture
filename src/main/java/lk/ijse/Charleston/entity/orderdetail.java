package lk.ijse.Charleston.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class orderdetail {
    String orderId;
    String Meal_No;
    int qty;
    double Ammount;
}
