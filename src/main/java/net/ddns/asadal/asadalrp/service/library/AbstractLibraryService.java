package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;
import net.ddns.asadal.asadalrp.repo.library.AbstractLibraryRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractLibraryService<T extends AbstractDomain, R extends AbstractLibraryRepo<T>>
        implements CommonService<T> {

    protected final R repo;

    public AbstractLibraryService(R repo) {
        this.repo = repo;
    }

    @Override
    public T get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean remove(Long id) {
        T libraryDomain = get(id);

        if (libraryDomain == null) {
            return false;
        }
        repo.delete(libraryDomain);
        return true;
    }

    @Override
    public List<Map<String, Object>> find(String name) {
        return repo.findAllByNameIgnoreCaseContains(name).stream().map(domain -> new HashMap<String, Object>(2) {{
            put("id", domain.getId());
            put("text", domain.getName());
        }}).collect(Collectors.toList());
    }
}
