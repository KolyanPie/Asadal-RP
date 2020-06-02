package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;
import net.ddns.asadal.asadalrp.repo.library.AbstractLibraryRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public abstract class AbstractUnPicturedLibraryService<T extends AbstractDomain, R extends AbstractLibraryRepo<T>>
        extends AbstractLibraryService<T, R> implements CommonUnPicturedService<T> {

    @Value("${error.name.unavailable}")
    protected String nameUnavailable;

    public AbstractUnPicturedLibraryService(R repo) {
        super(repo);
    }

    public String save(T libraryDomain) {
        String name = libraryDomain.getName();
        if (StringUtils.isEmpty(name) || repo.findByName(name) != null) {
            return nameUnavailable;
        }

        repo.save(libraryDomain);
        return libraryDomain.getName();
    }

    @Override
    public String update(T libraryDomain) {
        T libraryDomainFromDb = get(libraryDomain.getId());
        String name = libraryDomain.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(libraryDomainFromDb.getName()) && repo.findByName(name) != null)) {
            return nameUnavailable;
        }

        repo.save(libraryDomain);
        return libraryDomain.getName();
    }
}
