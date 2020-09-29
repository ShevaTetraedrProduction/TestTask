package com.inmonst.testtask.bulletin_board.Repositories;

import com.inmonst.testtask.bulletin_board.Model.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
    Page<Advert> findAll(Pageable pageable);
}
