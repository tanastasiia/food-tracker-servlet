package ua.training.model.jdbc;

import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.Mapper;
import ua.training.model.constants.UserConst;

import java.rmi.ServerException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) throws ServerException {
        //"username, first_name, last_name, password, role, height, weight,  activity_level, age, gender"
        try (PreparedStatement query = connection.prepareStatement(UserConst.CREATE, Statement.RETURN_GENERATED_KEYS)) {

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
        try (PreparedStatement query = connection.prepareStatement(UserConst.FIND_BY_ID)) {
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
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<User> findByUsername(String username) throws ServerException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(UserConst.FIND_ALL_BY_USERNAME)) {
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




    public Optional<User> findByField(String field, String value) throws ServerException {
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
    }
}
