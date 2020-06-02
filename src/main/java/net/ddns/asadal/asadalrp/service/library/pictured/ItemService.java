package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Item;
import net.ddns.asadal.asadalrp.repo.library.ItemRepo;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractPicturedLibraryService<Item, ItemRepo> {

    public ItemService(ItemRepo repo) {
        super(repo);
    }
}
