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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class H2OrderDao extends AbstractH2Dao implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderDao.class);
    private static final String INSERT_ORDER = "INSERT INTO  orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_ORDERS = "SELECT * FROM orders LIMIT ? OFFSET ?";
    private static final String CANCEL_ORDER = " UPDATE orders SET id = ?, user_id = ?, count = ?, period_id = ?," +
            " goods_id = ?, order_cost = ?, paid = ?, delivery_date = ?, additional_info = ?," +
            " status_id = ?, order_date = ? where id = ?";

    private static final String FIND_BY_PARAMETERS =
            " SELECT orders.id, orders.user_id, users.lastname ,users.firstname, users.middlename, users.address," +
                    " orders.order_date, orders.goods_id, goods.goods_name, orders.count, orders.order_cost, orders.paid," +
                    " orders.delivery_date, orders.period_id, period.period, orders.additional_info, orders.status_id, status.status_name" +
            " FROM orders" +
            " inner join users" +
            " on orders.user_id = users.id" +
            " inner join period" +
            " on orders.period_id = period.id" +
            " inner join goods" +
            " on orders.goods_id = goods.id" +
            " inner join status" +
            " on orders.status_id = status.id" +
                    " %s LIMIT ? OFFSET ? ";

    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    protected H2OrderDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Map<String, String> getParametersMap() {
        return fieldsQueryMap;
    }

    static {
        fieldsQueryMap.put("order-id", " orders.id = ?");
        fieldsQueryMap.put("user-id", " orders.user_id = ?");
        fieldsQueryMap.put("user-lastname", " users.lastname = ?");
        fieldsQueryMap.put("user-firstname", " users.firstname = ?");
        fieldsQueryMap.put("user-middlename", " users.middlename = ?");
        fieldsQueryMap.put("user-address", " users.address = ?");
        fieldsQueryMap.put("order-date", " orders.order_date = ?");
        fieldsQueryMap.put("order-goods-id", " orders.goods_id = ?");
        fieldsQueryMap.put("order-goods-name", " goods.goods_name = ?");
        fieldsQueryMap.put("order-goods-count", " orders.count = ?");
        fieldsQueryMap.put("order-cost", " orders.order_cost = ?");
        fieldsQueryMap.put("delivery-date", " orders.delivery_date = ?");
        fieldsQueryMap.put("delivery-time", " period.period = ?");
        fieldsQueryMap.put("order-addInfo", " orders.additional_info = ?");
        fieldsQueryMap.put("order-status", " status.status_name = ?");
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

    private void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int getRecordsCount() {
        int result = 0;

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM orders");
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
    protected String getFindByParameters() {
        return FIND_BY_PARAMETERS;
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
            order.setAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"));
            order.setStatus(statusDao.findById(resultSet.getInt("status_id")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return order;
    }
}
