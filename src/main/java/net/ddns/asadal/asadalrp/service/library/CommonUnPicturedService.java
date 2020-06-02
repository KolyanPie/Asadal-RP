package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.library.AbstractLibraryDomain;

public interface CommonUnPicturedService<T extends AbstractLibraryDomain> extends CommonService<T> {

    String save(T libraryDomain);

    String update(T libraryDomain);
}
