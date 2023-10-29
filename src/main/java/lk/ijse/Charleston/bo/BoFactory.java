package lk.ijse.Charleston.bo;

import lk.ijse.Charleston.bo.custom.impl.*;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        FOOD,GUEST,PLACEORDER,PAYMENT,RESERVATION,ROOM,TOURDETAIL,TOURMODEL
    }

    public <T extends SuperBo> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case FOOD:
                return (T) new FoodBoImpl();
            case GUEST:
                return (T) new GuestBoImpl();
            case PLACEORDER:
                return (T) new PlaceOrderBoImpl();
            case PAYMENT:
                return (T) new PaymentBoImpl();
            case RESERVATION:
                return (T) new ReservationBoImpl();
            case ROOM:
                return (T) new RoomBoImpl();
            case TOURDETAIL:
                return (T) new TourDetailBoImpl();
            case TOURMODEL:
                return (T) new TourModelBoImpl();
            default:
                return null;
        }
    }

}
