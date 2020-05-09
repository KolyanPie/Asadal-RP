package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Action;
import net.ddns.asadal.asadalrp.domain.dto.ActionDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionService {
    @Value("${error.name.unavailable}")
    private String nameUnavailable;

    private final ActionRepo actionRepo;

    public ActionService(ActionRepo actionRepo) {
        this.actionRepo = actionRepo;
    }

    public String saveAction(ActionDto actionDto) {
        String name = actionDto.getName();
        if (StringUtils.isEmpty(name) || actionRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Action action = createAction(actionDto);

        actionRepo.save(action);
        return action.getName();
    }

    public List<Map<String, Object>> findActions(String name) {
        return actionRepo.findAllByNameIgnoreCaseContains(name).stream().map(action -> new HashMap<String, Object>(2) {{
            put("id", action.getId());
            put("text", action.getName());
        }}).collect(Collectors.toList());
    }

    private Action createAction(ActionDto actionDto) {
        Action action = new Action();

        action.setName(actionDto.getName());
        action.setAdminHint(actionDto.getAdminHint());
        return action;
    }

    public Action getAction(Long id) {
        return actionRepo.findById(id).orElse(null);
    }

    public boolean removeAction(Long id) {
        Action action = getAction(id);

        if (action == null) {
            return false;
        }
        actionRepo.delete(action);
        return true;
    }
}
