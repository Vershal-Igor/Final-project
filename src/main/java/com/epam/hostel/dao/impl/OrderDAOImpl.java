package com.epam.hostel.dao.impl;


import com.epam.hostel.dao.IOrderDAO;
import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.order.PayType;
import com.epam.hostel.model.order.OrderStatus;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with order
 *
 * @author Vershal
 * @version 1.0
 */
public class OrderDAOImpl implements IOrderDAO {
    private static Logger logger = Logger.getLogger(OrderDAOImpl.class);

    private static final String SELECT_ALL_ORDERS = "SELECT `Order`.o_id, `Order`.arrival_date,`Order`.depature_date," +
            "User.name, User.surname, Room.room_number,`Order`.pay_type, `Order`.discount, " +
            "PERIOD_DIFF(`depature_date`, `arrival_date`)*room.price*((100-`Order`.discount)/100) AS final_pice," +
            "`Order`.status FROM `Order` INNER JOIN `User` ON `User_id` = `u_id` INNER JOIN `Room` ON `Room_id` = `r_id`;";
    private static final String SELECT_ORDER_BY_USER_ID = "SELECT `Order`.o_id,user.u_id,`Order`.arrival_date," +
            "`Order`.depature_date,`Order`.status,`Order`.discount,room.price*((100-`Order`.discount)/100) " +
            "AS final_pice,`Order`.places_amount,`Room`.room_number,`Order`.pay_type FROM `Order` INNER JOIN " +
            "`User` ON `User_id` = `u_id` INNER JOIN `Room` ON `Room_id` = `r_id` WHERE user.u_id=?;";
    private static final String SELECT_ORDER_BY_ORDER_ID = "SELECT `Order`.o_id,user.u_id,`Order`.arrival_date," +
            "`Order`.depature_date,`Order`.status,`Order`.discount,room.price*((100-`Order`.discount)/100) " +
            "AS final_pice,`Order`.pay_type, `Order`.places_amount FROM `Order` INNER JOIN `User` " +
            "ON `User_id` = `u_id` INNER JOIN `Room` ON `Room_id` = `r_id` WHERE `Order`.o_id=?;";
    private static final String SET_DISCOUNT = "UPDATE `Order` SET discount=? WHERE o_id=?;";
    private static final String INSERT_ORDER = "INSERT INTO `Order` (User_id, Room_id, arrival_date," +
            " depature_date, places_amount, pay_type) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_ORDER = "DELETE FROM `Order` WHERE o_id=?;";
    private static final String SET_ORDER_STATUS = "UPDATE `Order` SET status =? WHERE `Order`.o_id =?;";
    private static final int CONFIRM = 1;
    private static final int REJECT = 2;
    private static final int ARCHIVE = 3;


    private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves all orders
     *
     * @return List<Order>
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() throws DAOException {
        return jdbcTemplate.execute(SELECT_ALL_ORDERS, new PreparedStatementCallback<List<Order>>() {
            @Override
            public List<Order> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                List<Order> orders = new ArrayList<>();
                ResultSet rs = null;
                rs = ps.executeQuery();
                while (rs.next()) {
                    Order order = new Order();

                    order.setId(rs.getLong(1));
                    order.setArrivalDate(rs.getDate(2));
                    order.setDepartureDate(rs.getDate(3));
                    order.setName(rs.getString(4));
                    order.setSurname(rs.getString(5));
                    order.setRoomNumber(rs.getByte(6));
                    order.setPayType(PayType.valueOf(rs.getInt(7)));
                    order.setDiscount(rs.getBigDecimal(8));
                    order.setFinalPrice(rs.getBigDecimal(9));
                    order.setOrderStatus(OrderStatus.valueOf(rs.getInt(10)));

                    orders.add(order);

                }

                return orders;
            }
        });
    }

    /**
     * Retrieves order by definite order id
     *
     * @param id
     * @return Order
     */
    @Override
    @Transactional
    public Order findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_ORDER_BY_ORDER_ID, new OrderMapper(), id);
    }

    /**
     * Add new order to db
     *
     * @param order
     * @return long
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public long add(Order order) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {
            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, order.getUserId());
                ps.setLong(2, order.getRoomId());
                ps.setDate(3, (Date) order.getArrivalDate());
                ps.setDate(4, (Date) order.getDepartureDate());
                ps.setInt(5, order.getPlacesAmount());
                ps.setInt(6, order.getPayType().getId());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
                return null;
            }
        });
    }

    /**
     * Delete order by definite order id
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */

    @Override
    @Transactional(readOnly = false)
    public boolean delete(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(DELETE_ORDER);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, id);
                ps.executeUpdate();
                return true;
            }
        });
    }

    /**
     * Set order discount
     *
     * @param order
     * @return Long
     */
    @Override
    @Transactional(readOnly = false)
    public Long update(Order order) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_DISCOUNT);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {
            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setBigDecimal(1, order.getDiscount());
                ps.setLong(2, order.getId());
                ps.executeUpdate();
                return order.getId();
            }
        });
    }

    /**
     * Retrieves all orders of a particular user by definite user id
     *
     * @param userId
     * @return List<Order>
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public List<Order> historyUser(long userId) throws DAOException {

        return jdbcTemplate.execute(SELECT_ORDER_BY_USER_ID, new PreparedStatementCallback<List<Order>>() {
            @Override
            public List<Order> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                List<Order> orders = new ArrayList<>();
                ps.setLong(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Order order = new Order();

                    order.setId(rs.getLong(1));
                    order.setUserId(rs.getLong(2));
                    order.setArrivalDate(rs.getDate(3));
                    order.setDepartureDate(rs.getDate(4));
                    order.setOrderStatus(OrderStatus.valueOf(rs.getInt(5)));
                    order.setDiscount(rs.getBigDecimal(6));
                    order.setFinalPrice(rs.getBigDecimal(7));
                    order.setPlacesAmount(rs.getInt(8));
                    order.setRoomNumber(rs.getByte(9));
                    order.setPayType(PayType.valueOf(rs.getInt(10)));

                    orders.add(order);
                }
                return orders;
            }
        });
    }

    /**
     * Make order confirmed
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public boolean setConfirm(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_ORDER_STATUS);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            boolean confirm = false;

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, CONFIRM);
                ps.setLong(2, id);
                ps.executeUpdate();
                confirm = true;
                return confirm;
            }
        });
    }

    /**
     * Make order rejected
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public boolean setReject(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_ORDER_STATUS);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            boolean reject = false;

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, REJECT);
                ps.setLong(2, id);
                ps.executeUpdate();
                reject = true;
                return reject;
            }
        });
    }

    /**
     * Make order archived
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public boolean setArchive(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_ORDER_STATUS);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            boolean archive = false;

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, ARCHIVE);
                ps.setLong(2, id);
                ps.executeUpdate();
                archive = true;
                return archive;
            }
        });
    }


    private final class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();

            order.setId(rs.getLong("o_id"));
            order.setUserId(rs.getLong("u_id"));
            order.setArrivalDate(rs.getDate("arrival_date"));
            order.setDepartureDate(rs.getDate("depature_date"));
            order.setOrderStatus(OrderStatus.valueOf(rs.getInt("status")));
            order.setDiscount(rs.getBigDecimal("discount"));
            order.setFinalPrice(rs.getBigDecimal("final_pice"));
            order.setPlacesAmount(rs.getInt("places_amount"));
            order.setPayType(PayType.valueOf(rs.getInt("pay_type")));
            return order;
        }

    }

}
