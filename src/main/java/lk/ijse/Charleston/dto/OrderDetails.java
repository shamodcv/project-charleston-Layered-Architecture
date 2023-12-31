package lk.ijse.Charleston.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {
    private String orderID;
    private String mealID;
    private Integer qty;
    private Double total;
}
