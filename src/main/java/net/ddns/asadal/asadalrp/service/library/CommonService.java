package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;

import java.util.List;
import java.util.Map;

public interface CommonService<T extends AbstractDomain> {

    T get(Long id);

    boolean remove(Long id);

    List<Map<String, Object>> find(String name);
}
