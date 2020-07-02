package dtl.DtlApp.Services;


import dtl.DtlApp.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService<T extends User> extends UserDetailsService {

    List<T> findAll();

    Page<T> findAllWithPagination(Pageable pageable);

    void saveUser(T user);

    void saveUserWithPass(T user, String password);

    Optional<T> findById(Long id);

}
