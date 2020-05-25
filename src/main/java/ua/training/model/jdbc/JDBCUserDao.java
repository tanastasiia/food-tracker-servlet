package ua.training.model.jdbc;

import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.Mapper;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    private String FIND_ALL_BY_USERNAME = "select * from users where username = ?";
    private String FIND_BY_ID = "select * from food WHERE id=?";
    private String CREATE =
            "INSERT INTO users (username, first_name, last_name, password, role, height, weight,  activity_level, age, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private String COUNT_ALL = "SELECT COUNT(*) AS count FROM users";
    private String FIND_ALL_PAGINATION =
            "SELECT * " +
                    "FROM users " +
                    "LIMIT ? OFFSET ?";

    private String UPDATE_USER =
            "UPDATE users " +
                    "SET first_name=?, last_name=?, height=?, weight=?, age=?, activity_level=?, gender=? " +
                    "WHERE users.id = ?";

    private String UPDATE_USER_ROLE =
            "UPDATE users " +
                    "SET role=? " +
                    "WHERE users.id = ?";


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, user.getUsername());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPassword());
            query.setString(5, user.getRole());
            query.setInt(6, user.getHeight());
            query.setInt(7, user.getWeight());
            query.setString(8, user.getActivityLevel());
            query.setInt(9, user.getAge());
            query.setString(10, user.getGender());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(Long id) throws ServerException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(Mapper.userMap(resultSet));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void updateUser(User newUser) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_USER)) {
            query.setString(1, newUser.getFirstName());
            query.setString(2, newUser.getLastName());
            query.setInt(3, newUser.getHeight());
            query.setInt(4, newUser.getWeight());
            query.setInt(5, newUser.getAge());
            query.setString(6, newUser.getActivityLevel());
            query.setString(7, newUser.getGender());
            query.setLong(8, newUser.getId());
            query.executeUpdate();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public void updateUserRole(long userId, String role) throws ServerException {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_USER_ROLE)) {
            query.setString(1, role);
            query.setLong(2, userId);
            query.executeUpdate();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public void close() throws ServerException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll(int limit, int offset) throws ServerException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_PAGINATION)) {
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                users.add(Mapper.userMap(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return users;
    }

    @Override
    public int countAll() throws ServerException {
        int count = 0;
        try (PreparedStatement query = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
        return count;
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServerException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_BY_USERNAME)) {
            query.setString(1, username);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(Mapper.userMap(resultSet));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return user;
    }




/*    public Optional<User> findByField(String field, String value) throws ServerException {
        Optional<User> user = Optional.empty();
        String FIND_BY_FIELD = "select * from users where %s = ?";
        try (PreparedStatement query = connection.prepareStatement(String.format(FIND_BY_FIELD, field))) {
            query.setString(1, value);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(Mapper.userMap(resultSet));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return user;
    }*/
}
