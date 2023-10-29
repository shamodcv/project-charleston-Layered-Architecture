package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.PaymentBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.PaymentDao;
import lk.ijse.Charleston.dto.Payment;
import lk.ijse.Charleston.entity.payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.PAYMENT);


    @Override
    public ArrayList<payment> getAll() throws SQLException, ClassNotFoundException {
        return paymentDao.getAll();
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return paymentDao.save(new payment(dto.getPaymentId(),dto.getGuestId(),dto.getGuestName(),dto.getResId(),dto.getRoomId(),dto.getCheckIn(),dto.getCheckOut(),dto.getOrderAm(),dto.getTotal()));
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return paymentDao.update(new payment(dto.getPaymentId(),dto.getGuestId(),dto.getGuestName(),dto.getResId(),dto.getRoomId(),dto.getCheckIn(),dto.getCheckOut(),dto.getOrderAm(),dto.getTotal()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDao.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return paymentDao.generateNewID();
    }
}
