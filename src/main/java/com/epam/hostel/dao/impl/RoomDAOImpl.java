package com.epam.hostel.dao.impl;


import com.epam.hostel.dao.IRoomDAO;
import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with Room
 *
 * @author Vershal
 * @version 1.0
 */
public class RoomDAOImpl implements IRoomDAO {
    private static Logger logger = Logger.getLogger(RoomDAOImpl.class);

    private static final String SELECT_ALL_ROOMS = "SELECT * FROM room;";
    private static final String SELECT_ROOM_BY_ID = "SELECT * FROM room WHERE r_id=?;";
    private static final String INSERT_ROOM = "INSERT INTO room(room_number, room_places, price) VALUES(?, ?, ?);";
    private static final String DELETE_ROOM = "DELETE FROM room WHERE r_id=?;";
    private static final String UPDATE_ROOM_PRICE = "UPDATE room SET price=? WHERE r_id=?;";
    private static final String SHOW_FREE_ROOMS = "SELECT * FROM room WHERE NOT EXISTS(SELECT * FROM `Order` WHERE " +
            "arrival_date<=? AND depature_date>=? AND room_id=room.r_id) AND room_places>=?;";


    private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public RoomDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves all rooms
     *
     * @return List<Room>
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findAll() throws DAOException {
        return jdbcTemplate.execute(SELECT_ALL_ROOMS, new PreparedStatementCallback<List<Room>>() {
            @Override
            public List<Room> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                List<Room> rooms = new ArrayList<>();
                ResultSet rs = null;
                rs = ps.executeQuery();
                while (rs.next()) {
                    Room room = new Room();

                    room.setId(rs.getLong("r_id"));
                    room.setRoomNumber(rs.getByte("room_number"));
                    room.setRoomPlaces(rs.getByte("room_places"));
                    room.setPrice(rs.getBigDecimal("price"));

                    rooms.add(room);
                }
                return rooms;
            }
        });
    }

    /**
     * Retrieves room by definite room id
     *
     * @param id
     * @return Room
     */
    @Override
    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_ROOM_BY_ID, new RoomMapper(), id);
    }

    /**
     * Add new room to db
     *
     * @param room
     * @return long
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public long add(Room room) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);
            }
        };

        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {

            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setByte(1, room.getRoomNumber());
                ps.setByte(2, room.getRoomPlaces());
                ps.setBigDecimal(3, room.getPrice());
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
     * Delete room by definite room id
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
                return con.prepareStatement(DELETE_ROOM);
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
     * Edit room price by definite room id
     *
     * @param room
     * @return Long
     */
    @Override
    @Transactional(readOnly = false)
    public Long update(Room room) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(UPDATE_ROOM_PRICE);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {
            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setBigDecimal(1, room.getPrice());
                ps.setLong(2, room.getId());
                ps.executeUpdate();
                return room.getId();
            }
        });
    }


    @Override
    @Transactional(readOnly = true)
    public List<Room> showFreeRooms(Order order) throws DAOException {
        return jdbcTemplate.execute(SHOW_FREE_ROOMS, new PreparedStatementCallback<List<Room>>() {
            @Override
            public List<Room> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                ps.setDate(1, (java.sql.Date) order.getArrivalDate());
                ps.setDate(2, (java.sql.Date) order.getDepartureDate());
                ps.setInt(3, order.getPlacesAmount());

                List<Room> rooms = new ArrayList<>();
                ResultSet rs = null;
                rs = ps.executeQuery();
                while (rs.next()) {
                    Room room = new Room();

                    room.setId(rs.getLong("r_id"));
                    room.setRoomNumber(rs.getByte("room_number"));
                    room.setRoomPlaces(rs.getByte("room_places"));
                    room.setPrice(rs.getBigDecimal("price"));

                    rooms.add(room);
                }
                return rooms;
            }
        });
    }

    private final class RoomMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();

            room.setId(rs.getLong("r_id"));
            room.setRoomNumber(rs.getByte("room_number"));
            room.setRoomPlaces(rs.getByte("room_places"));
            room.setPrice(rs.getBigDecimal("price"));
            return room;
        }

    }


}
