package lk.ijse.Charleston.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class payment {
    String PayID;
    String Guest_ID;
    String Guest_Name;
    String Res_ID;
    String Room_ID;
    String Check_in_Date;
    String Check_out_Date;
    double Orders_Ammount;
    double Total_Price;

    public payment(String paymentId, String guestId, String guestName, String resId, String roomId, String checkIn, String checkOut, Double orderAm, Double total) {
        PayID = paymentId;
        Guest_ID = guestId;
        Guest_Name = guestName;
        Res_ID = resId;
        Room_ID = roomId;
        Check_in_Date = checkIn;
        Check_out_Date = checkOut;
        Orders_Ammount = orderAm;
        Total_Price = total;
    }

}
