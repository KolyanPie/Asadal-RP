package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.AbstractPicturedLibraryDomain;
import net.ddns.asadal.asadalrp.service.library.CommonService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonPicturedLibraryService<T extends AbstractPicturedLibraryDomain> extends CommonService<T> {

    String save(T libraryDomain, MultipartFile picture) throws IOException;

    String update(T libraryDomain, MultipartFile picture) throws IOException;
}
