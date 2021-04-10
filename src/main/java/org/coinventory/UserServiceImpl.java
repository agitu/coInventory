package org.coinventory;

import org.coinventory.db.Users;
import org.coinventory.db.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository ur;

    @Override
    public Users insertUser(Users u) {
        return ur.save(u);
    }

    @Override
    public Users updateUser(Users u) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public Users getUser(long id) {
        return ur.findById(id).get();
    }

    @Override
    public List<Users> getUsersBySurname(String surname, Pageable p) {
        return ur.findBySurnameContainingIgnoreCase(surname, p);
    }

    public List<Users> getUsersAll() {
        return ur.findAll();
    }
}
