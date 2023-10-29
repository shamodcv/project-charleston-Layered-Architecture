package lk.ijse.Charleston.dao;

import lk.ijse.Charleston.dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory(){
        return daoFactory==null ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType {
        FOOD,GUEST,QUERY,PLACEORDER,PAYMENT,RESERVATION,ROOM,TOURDETAIL,TOURMODEL
    }

    public <SuperDao>SuperDao getDao(DaoType type){
        switch (type){
            case FOOD:
                return (SuperDao) new FoodDaoImpl();
            case GUEST:
                return (SuperDao) new GuestDaoImpl();
            case QUERY:
                return (SuperDao) new QueryDaoImpl();
            case PLACEORDER:
                return (SuperDao) new PlaceOrderDaoImpl();
            case PAYMENT:
                return (SuperDao) new PaymentDaoImpl();
            case RESERVATION:
                return (SuperDao) new ReservationDaoImpl();
            case ROOM:
                return (SuperDao) new RoomDaoImpl();
            case TOURDETAIL:
                return (SuperDao) new TourDetailDaoImpl();
            case TOURMODEL:
                return (SuperDao) new TourModelDaoImpl();
            default:
                return null;
        }
    }

}
