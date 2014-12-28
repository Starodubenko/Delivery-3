package com.epam.star.dao.H2dao;

import com.epam.star.dao.*;
import com.epam.star.entity.Order;
import com.epam.star.entity.Order2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2OrderDao2 extends AbstractH2Dao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderDao2.class);
    private static final String INSERT_ORDER = "INSERT INTO  USER_ORDER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_ORDERS = "SELECT * FROM ORDERS LIMIT ? OFFSET ?";
    private static final String UPDATE_ORDER = " UPDATE USER_ORDER SET ID = ?, USER_ID = ?, NUMBER = ?, PERIOD_ID = ?," +
            " DELIVERY_DATE = ?, ORDER_DATE = ?, PAID = ?, DISCONT_ID = ?, ADDITIONAL_INFO = ?, STATUS_ID = ? where ID = ?";

    private static final String NECESSARY_COLUMNS =
            " USER_ORDER.ID, USERS.FIRSTNAME , USERS.LASTNAME, USERS.MIDDLENAME, USERS.ADDRESS," +
                    " ORDERS.ORDER_DATE, GOODS.GOODS_NAME, ORDERS.COUNT, ORDERS.ORDER_COST, ORDERS.PAID," +
                    " ORDERS.DELIVERY_DATE, PERIOD.PERIOD, ORDERS.ADDITIONAL_INFO, STATUS.STATUS_NAME";

    private static final String ADDITIONAL_COLUMNS = "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s" +
                    " FROM ORDERS" +
                    " INNER JOIN USERS" +
                    " ON ORDERS.USER_ID = USERS.ID" +
                    " INNER JOIN PERIOD" +
                    " ON ORDERS.PERIOD_ID = PERIOD.ID" +
                    " INNER JOIN GOODS" +
                    " ON ORDERS.GOODS_ID = GOODS.ID" +
                    " INNER JOIN STATUS" +
                    " ON ORDERS.STATUS_ID = STATUS.ID  ";

    private static final String ID_FIELD = " USER_ORDER.ID, ";


    protected H2OrderDao2(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

//    public List<Order2> findAllByClientIdToday(int id) throws DaoException {
//        String sql = "SELECT * FROM USER_ORDER " +
//                "INNER JOIN USERS ON USER_ORDER.USER_ID = USERS.ID " +
//                "INNER JOIN PERIOD ON USER_ORDER.PERIOD_ID = PERIOD.ID " +
//                "INNER JOIN ORDERED_GOODS ON USER_ORDER.NUMBER = ORDERED_GOODS.ORDER_NUMBER" +
//                " where USER_ID = " + id + " and ORDER_DATE = CAST(GETDATE() AS DATE)";
//        List<Order2> orders = new ArrayList<>();
//        PreparedStatement prstm = null;
//        ResultSet resultSet = null;
//
//        try {
//            prstm = conn.prepareStatement(sql);
//            resultSet = prstm.executeQuery();
//
//            while (resultSet.next()) {
//                orders.add(getEntityFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            closeStatement(prstm, resultSet);
//        }
//        return orders;
//    }
//
//    @Override
//    public List<Order2> findAllByClientIdLastDays(int id) throws DaoException {
//        String sql = "SELECT *" +
//                " FROM orders" +
//                " inner join users" +
//                " on orders.user_id = users.id" +
//                " inner join period" +
//                " on orders.period_id = period.id" +
//                " inner join goods" +
//                " on orders.goods_id = goods.id" +
//                " inner join status" +
//                " on orders.status_id = status.id" +
//                " where user_id = " + id + " and order_date != CAST(GETDATE() AS DATE)";
//        List<Order2> orders = new ArrayList<>();
//        PreparedStatement prstm = null;
//        ResultSet resultSet = null;
//        try {
//            prstm = conn.prepareStatement(sql);
//            resultSet = prstm.executeQuery();
//
//            while (resultSet.next()) {
//                Order2 order = getEntityFromResultSet(resultSet);
//                orders.add(order);
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            closeStatement(prstm, resultSet);
//        }
//        return orders;
//    }


    public List<Order> findAllByClientIdToday(int id) {
        return null;
    }


    public List<Order> findAllByClientIdLastDays(int id) {
        return null;
    }


    public int getClientOrdersCount(int id) {

        int result = 0;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM orders where user_id = " + id);
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }


    public Order2 findById(int ID) throws DaoException {
        String sql = "SELECT * FROM Orders WHERE id = " + ID;
        Order2 order = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                order = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return order;
    }


    public String insert(Order2 order) throws DaoException {
        String status = "Order do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(INSERT_ORDER);
            prstm.setString(1, null);
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getNumber());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setDate(5, order.getDeliveryDate());
            prstm.setDate(6, order.getOrderDate());
            prstm.setBoolean(7, order.isPaid());
            prstm.setInt(8, order.getDiscount().getId());
            prstm.setString(9, order.getAdditionalInfo());
            prstm.setInt(10, order.getStatus().getId());
            prstm.setInt(11, order.getId());
            prstm.execute();
            status = "Order added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    public String deleteEntity(int ID) throws DaoException {
        return null;
    }

    public String updateEntity(Order2 order) throws DaoException {

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_ORDER);
            prstm.setInt(1, order.getId());
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getNumber());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setDate(5, order.getDeliveryDate());
            prstm.setDate(6, order.getOrderDate());
            prstm.setBoolean(7, order.isPaid());
            prstm.setInt(8, order.getDiscount().getId());
            prstm.setString(9, order.getAdditionalInfo());
            prstm.setInt(10, order.getStatus().getId());
            prstm.setInt(11, order.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public String getFindByParameters(Boolean needAditionalColumns) {

        String columns = NECESSARY_COLUMNS;

        if (needAditionalColumns == true){
            columns = columns + ADDITIONAL_COLUMNS;
        }

        String result = String.format(FIND_BY_PARAMETERS_WITHOUT_COLUMNS,columns);

        result = String.format(result+"%s", LIMIT_OFFSET);

        return result;
    }

    @Override
    public String getFindByParametersWithoutColumns() {
        return FIND_BY_PARAMETERS_WITHOUT_COLUMNS;
    }

    @Override
    public String getNecessaryColumns() {
        return NECESSARY_COLUMNS;
    }

    @Override
    public String getAdditionalColumns() {
        return ADDITIONAL_COLUMNS;
    }

    @Override
    public String getIdField() {
        return ID_FIELD;
    }


    public Order2 getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Order2 order = new Order2();
        PeriodDao periodDao = daoManager.getPeriodDao();
        GoodsDao goodsDao = daoManager.getGoodsDao();
        StatusDao statusDao = daoManager.getStatusDao();
        ClientDao clientDao = daoManager.getClientDao();
        DiscountDao discountDao = daoManager.getDiscountDao();

        try {
            order.setId(resultSet.getInt("id"));
            order.setUser(clientDao.findById(resultSet.getInt("user_id")));
            order.setNumber(resultSet.getInt("number"));
            order.setPeriod(periodDao.findById(resultSet.getInt("period_id")));
            order.setDeliveryDate(resultSet.getDate("delivery_date"));
            order.setOrderDate(resultSet.getDate("order_date"));
            order.setPaid(resultSet.getBoolean("paid"));
            order.setDiscount(discountDao.findById(resultSet.getInt("discount_id")));
            order.setAdditionalInfo(UTIL_DAO.getString(resultSet.getString("additional_info")));
            order.setStatus(statusDao.findById(resultSet.getInt("status_id")));


        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return order;
    }
}
