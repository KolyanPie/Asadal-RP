package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractLibraryRepo<T extends AbstractDomain> extends JpaRepository<T, Long> {
    T findByName(String name);

    List<T> findAllByNameIgnoreCaseContains(String name);
}
