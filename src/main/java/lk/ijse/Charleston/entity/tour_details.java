package lk.ijse.Charleston.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class tour_details {
    String Book_ID;
    String Guest_ID;
    String Guest_Name;
    String Tour_ID;
    String Tour_Name;
    double Price;
}
