package com.inmonst.testtask.bulletin_board.Repositories;

import com.inmonst.testtask.bulletin_board.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
