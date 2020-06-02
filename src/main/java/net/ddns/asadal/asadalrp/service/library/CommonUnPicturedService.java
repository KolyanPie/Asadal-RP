package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;

public interface CommonUnPicturedService<T extends AbstractDomain> extends CommonService<T> {

    String save(T libraryDomain);

    String update(T libraryDomain);
}
