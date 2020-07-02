package dtl.DtlApp.Dao;

import dtl.DtlApp.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao<T extends User> extends JpaRepository<T, Long> {

    T findByUsername(String username);

    Optional<T> findById(Long id);

    Page<T> findAll(Pageable pageable);

}
