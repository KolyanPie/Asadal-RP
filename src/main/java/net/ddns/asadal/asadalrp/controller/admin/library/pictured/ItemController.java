package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Item;
import net.ddns.asadal.asadalrp.service.library.pictured.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/item")
public class ItemController extends AbstractPicturedLibraryController<Item, ItemService> {

    public ItemController(ItemService service) {
        super(service);
    }
}
