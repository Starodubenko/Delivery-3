package com.epam.star.dao.H2dao;

import com.epam.star.dao.GoodsDao;
import com.epam.star.entity.Goods;
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

public class H2GoodsDao extends AbstractH2Dao implements GoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String TABLE_NAME = "goods";
    private static final String ADD_GOODS = "INSERT INTO goods VALUES (?, ?, ?)";
    private static final String DELETE_GOODS = "DELETE FROM goods WHERE id = ?";
    private static final String UPDATE_GOODS = "UPDATE goods SET id = ?, goods_name = ?, price = ? WHERE id = ?";

    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM goods" +
                    " %s LIMIT ? OFFSET ?";

    static {
        fieldsQueryMap.put("goods-id", " goods.id = ?");
        fieldsQueryMap.put("goods-name", " goods.goods_name = ?");
        fieldsQueryMap.put("goods-price", " goods.price = ?");
    }

    protected H2GoodsDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    public List<Goods> getAllGoods() {
        List<Goods> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT * FROM goods");
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Goods findByGoodsName(String name) throws DaoException {
        String sql = "SELECT * FROM goods WHERE goods_name = " + "'" + name + "'";
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                goods = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return goods;
    }

    @Override
    public Goods findById(int ID) throws DaoException {
        String sql = "SELECT * FROM goods WHERE id = " + ID;
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                goods = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return goods;
    }

    @Override
    public String insert(Goods goods) throws DaoException {
        String status = "Goods do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_GOODS);
            prstm.setString(1, null);
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.execute();
            status = "Goods added successfully";
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
    public String updateEntity(Goods goods) throws DaoException {
        String status = "Contact do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_GOODS);
            prstm.setInt(1, goods.getId());
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.setInt(4, goods.getId());
            prstm.executeUpdate();
            status = "Contact updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public Map<String, String> getParametersMap() {
        return fieldsQueryMap;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Goods getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Goods goods = new Goods();
        try {
            goods.setId(resultSet.getInt("id"));
            goods.setGoodsName(resultSet.getString("goods_name"));
            goods.setPrice(resultSet.getBigDecimal("price"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return goods;
    }

//    @Override
//    public int getRecordsCount() {
//        int result = 0;
//
//        PreparedStatement prstm = null;
//        ResultSet resultSet = null;
//        try {
//            prstm = conn.prepareStatement("SELECT COUNT(*) FROM goods");
//            resultSet = prstm.executeQuery();
//            while (resultSet.next())
//                result = resultSet.getInt("count(*)");
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            closeStatement(prstm, resultSet);
//        }
//        return result;
//    }

    @Override
    protected String getFindByParameters() {
        return FIND_BY_PARAMETERS;
    }
}
