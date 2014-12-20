package com.epam.star.dao.H2dao;

import com.epam.star.dao.ContactDao;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2ContactDao extends AbstractH2Dao implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CONTACT = "INSERT INTO CONTACTS VALUES (?, ?, ?)";
    private static final String DELETE_CONTACT = "DELETE FROM CONTACTS WHERE ID = ?";
    private static final String UPDATE_CONTACT = "UPDATE CONTACTS SET ID = ?, TELEPHONE = ?, OWNER = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " CONTACTS.ID, CONTACTS.TELEPHONE, CONTACTS.OWNER";

    private static final String ADDITIONAL_COLUMNS =
            "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM CONTACTS";

    private static final String ID_FIELD = " CONTACTS.ID, ";


    protected H2ContactDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public List<Contact> getContacts() throws DaoException {
        List<Contact> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT * FROM CONTACTS");
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getContactFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Contact findById(int ID) throws DaoException {
        String sql = "select * from contacts where id = " + "'" + ID + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return getContactFromResultSet(resultSet);
    }

    @Override
    public String insert(Contact contact) throws DaoException {
        String status = "Contact do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_CONTACT);
            prstm.setString(1, null);
            prstm.setString(2, contact.getTelephone());
            prstm.setString(3, contact.getOwner());

            prstm.execute();
            status = "Contact added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Contact do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(DELETE_CONTACT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Contact deleted successfully ";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String updateEntity(Contact contact) throws DaoException {
        String status = "Contact do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_CONTACT);
            prstm.setInt(1, contact.getId());
            prstm.setString(2, contact.getTelephone());
            prstm.setString(3, contact.getOwner());
            prstm.setInt(4, contact.getId());
            prstm.executeUpdate();
            status = "Contact updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    public Contact getContactFromResultSet(ResultSet resultSet) throws DaoException {
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getInt("id"));
            contact.setTelephone(resultSet.getString("telephone"));
            contact.setOwner(resultSet.getString("owner"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contact;
    }

    @Override
    public AbstractEntity getEntityFromResultSet(ResultSet resultSet) throws DaoException {
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
}
