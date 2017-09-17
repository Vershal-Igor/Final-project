package com.epam.hostel.dao.impl;


import com.epam.hostel.dao.IUserDAO;
import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.user.Role;
import com.epam.hostel.model.user.UserStatus;
import com.epam.hostel.model.user.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * Class for working with User
 *
 * @author Vershal
 * @version 1.0
 */
public class UserDAOImpl implements IUserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String SELECT_ALL_USERS = "SELECT user.u_id, user.name, user.surname, " +
            "user.login, user.password, user.status FROM user WHERE user.role=1";
    private static final String INSERT_USER = "INSERT INTO user (name, surname, login, password) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_INF = "SELECT user.u_id, user.role, user.name, user.surname FROM user " +
            "WHERE login=? AND password=?;";
    private static final String SELECT_USER_BY_ID = "SELECT user.u_id, user.name, user.surname, user.role, " +
            "user.status FROM user WHERE u_id = ?;";
    private static final String CHECK_USER = "SELECT EXISTS(SELECT * FROM user WHERE login=? AND password=?);";
    private static final String DELETE_USER = "DELETE FROM user WHERE u_id=?;";
    private static final String UPDATE_USER = "UPDATE user SET user.name=?, user.surname=?, user.login=? WHERE u_id=?;";
    private static final String SET_BAN = "UPDATE user SET status = ? WHERE user.u_id = ?";
    private static final int IS_BANNED = 0;
    private static final int NOT_BANNED = 1;


    private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    protected UserDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves all users
     *
     * @return List<User>
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws DAOException {

        return jdbcTemplate.execute(SELECT_ALL_USERS, new PreparedStatementCallback<List<User>>() {
            @Override
            public List<User> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                List<User> users = new ArrayList<>();
                ResultSet resultSet = null;
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setSurname(resultSet.getString(3));
                    user.setLogin(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setUserStatus(UserStatus.valueOf(resultSet.getInt(6)));


                    users.add(user);
                }

                return users;
            }
        });
    }

    /**
     * Retrieves user by definite user id
     *
     * @param id
     * @return User
     */
    @Transactional
    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
    }

    /**
     * Add new user to db
     *
     * @param user
     * @return long
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public long add(User user) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {
            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
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
     * Delete user by definite user id
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
                return con.prepareStatement(DELETE_USER);
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
     * Edit user's name, surname and login by definite user id
     *
     * @param user
     * @return Long
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public Long update(User user) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(UPDATE_USER);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Long>() {
            @Override
            public Long doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getLogin());
                ps.setLong(4, user.getId());
                ps.executeUpdate();

                return user.getId();
            }
        });
    }

    /**
     * Get information about user by definite user id
     *
     * @param user
     * @return User
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public User getUserInf(User user) throws DAOException {
        return jdbcTemplate.execute(SELECT_USER_INF, new PreparedStatementCallback<User>() {
            @Override
            public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());

                ResultSet rs = ps.executeQuery();
                rs.next();

                User infUser = new User();

                infUser.setId(rs.getLong(1));
                infUser.setRole(Role.valueOf(rs.getInt(2)));
                infUser.setName(rs.getString(3));
                infUser.setSurname(rs.getString(4));

                return infUser;
            }
        });
    }

    /**
     * Check user by login and password
     *
     * @param user
     * @return
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = true)
    public int checkUser(User user) throws DAOException {
        return jdbcTemplate.execute(CHECK_USER, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());

                ResultSet rs = ps.executeQuery();
                if (!rs.isAfterLast()) {
                    rs.next();
                    return rs.getInt(1);
                }
                return null;
            }
        });
    }

    /**
     * Make user banned
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public boolean setBan(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_BAN);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            boolean isBanned = false;

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, IS_BANNED);
                ps.setLong(2, id);
                ps.executeUpdate();
                isBanned = true;
                return isBanned;
            }
        });
    }

    /**
     * Make user unbanned
     *
     * @param id
     * @return boolean
     * @throws DAOException
     */
    @Override
    @Transactional(readOnly = false)
    public boolean setUnban(Long id) throws DAOException {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(SET_BAN);
            }
        };
        return jdbcTemplate.execute(psc, new PreparedStatementCallback<Boolean>() {
            boolean isUnbanned = false;

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, NOT_BANNED);
                ps.setLong(2, id);
                ps.executeUpdate();

                isUnbanned = true;
                return isUnbanned;
            }
        });
    }


    private final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getLong("u_id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setRole(Role.valueOf(rs.getInt("role")));
            user.setUserStatus(UserStatus.valueOf(rs.getInt("status")));
            return user;
        }

    }

}
