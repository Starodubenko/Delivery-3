package com.epam.star.dao.H2dao;

import com.epam.star.dao.*;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2OrderDao extends AbstractH2Dao implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderDao.class);
    private static final String INSERT_ORDER = "INSERT INTO  orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_ORDERS = "SELECT * FROM ORDERS LIMIT ? OFFSET ?";
    private static final String CANCEL_ORDER = " UPDATE orders SET id = ?, user_id = ?, count = ?, period_id = ?," +
            " goods_id = ?, order_cost = ?, paid = ?, delivery_date = ?, additional_info = ?," +
            " status_id = ?, order_date = ? where id = ?";

    private static final String NECESSARY_COLUMNS =
            " ORDERS.ID, USERS.FIRSTNAME , USERS.LASTNAME, USERS.MIDDLENAME, USERS.ADDRESS," +
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

    private static final String ID_FIELD = " ORDERS.ID, ";


    protected H2OrderDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    public List<Order> findAllByClientIdToday(int id) throws DaoException {
        String sql = "SELECT *" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = " + id + " and order_date = CAST(GETDATE() AS DATE)";
        List<Order> orders = new ArrayList<>();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;

        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                orders.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> findAllByClientIdLastDays(int id) throws DaoException {
        String sql = "SELECT *" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = " + id + " and order_date != CAST(GETDATE() AS DATE)";
        List<Order> orders = new ArrayList<>();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                Order order = getEntityFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return orders;
    }

    @Override
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

    @Override
    public Order findById(int ID) throws DaoException {
        String sql = "SELECT * FROM Orders WHERE id = " + ID;
        Order order = null;
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

    @Override
    public String insert(Order order) throws DaoException {
        String status = "Order do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(INSERT_ORDER);
            prstm.setString(1, null);
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setDate(6, order.getDeliveryDate());
            prstm.setString(7, order.getAdditionalInfo());
            prstm.setInt(8, order.getStatus().getId());
            prstm.setDate(9, order.getOrderDate());
            prstm.setBigDecimal(10, order.getOrderCost());
            prstm.setBigDecimal(11, order.getPaid());
            prstm.execute();
            status = "Order added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }


    @Override
    public String deleteEntity(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateEntity(Order order) throws DaoException {

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(CANCEL_ORDER);
            prstm.setInt(1, order.getId());
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setBigDecimal(6, order.getOrderCost());
            prstm.setBigDecimal(7, order.getPaid());
            prstm.setDate(8, order.getDeliveryDate());
            prstm.setString(9, order.getAdditionalInfo());
            prstm.setInt(10, order.getStatus().getId());
            prstm.setDate(11, order.getOrderDate());
            prstm.setInt(12, order.getId());
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


    public Order getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        PeriodDao periodDao = daoManager.getPeriodDao();
        GoodsDao goodsDao = daoManager.getGoodsDao();
        StatusDao statusDao = daoManager.getStatusDao();
        ClientDao clientDao = daoManager.getClientDao();

        try {
            order.setId(resultSet.getInt("id"));
            order.setOrderDate(resultSet.getDate("order_date"));
            order.setUser(clientDao.findById(resultSet.getInt("user_id")));
            order.setGoods(goodsDao.findById(resultSet.getInt("goods_id")));
            order.setOrderCost(resultSet.getBigDecimal("order_cost"));
            order.setPaid(resultSet.getBigDecimal("paid"));
            order.setCount(resultSet.getInt("count"));
            order.setDeliveryDate(resultSet.getDate("delivery_date"));
            order.setPeriod(periodDao.findById(resultSet.getInt("period_id")));
            order.setAdditionalInfo(UTIL_DAO.getString(resultSet.getString("ADDITIONAL_INFO")));
//            order.setAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"));
            order.setStatus(statusDao.findById(resultSet.getInt("status_id")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return order;
    }
}
