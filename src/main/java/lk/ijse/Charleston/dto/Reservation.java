package lk.ijse.Charleston.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {

  private String resId;
  private String gusId;
  private String roomId;
  private Double roomPrice;
  private Date checkInDate;
  private Date checkOutDate;


}
