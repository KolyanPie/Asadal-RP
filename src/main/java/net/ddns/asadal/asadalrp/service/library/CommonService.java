package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.library.AbstractLibraryDomain;

import java.util.List;
import java.util.Map;

public interface CommonService<T extends AbstractLibraryDomain> {

    T get(Long id);

    boolean remove(Long id);

    List<Map<String, Object>> find(String name);
}
