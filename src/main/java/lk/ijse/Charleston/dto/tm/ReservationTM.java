package lk.ijse.Charleston.dto.tm;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationTM {
    String resId;
    String gusId;
    String roomId;
    Double roomPrice;
    Date checkInDate;
    Date checkOutDate;
}