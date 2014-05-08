package de.deutschepost.secreq.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecReqRepository {

    private static int secReqSequence = 4;

    private static final Map<Integer, SecReq> NO_SQL_DATA_STORE = new HashMap<Integer,SecReq>(){{
        put(1, new SecReq(1, "Du sollst keine anderen Götter haben neben mir"));
        put(2, new SecReq(2, "Du sollst nicht töten"));
        put(3, new SecReq(3, "Gedenke, dass du den Sabbat heiligst."));
    }};

    public List<SecReq> list() {
        return  new ArrayList<SecReq>(NO_SQL_DATA_STORE.values());
    }
    public void update(int id, String description) {
        checkExistence(id);
        NO_SQL_DATA_STORE.get(id).setDescription(description);
    }

    public SecReq findById(int id) {
        checkExistence(id);
        return NO_SQL_DATA_STORE.get(id);
    }

    public void store(SecReq secReq) {
        secReq.setId(secReqSequence++);
        NO_SQL_DATA_STORE.put(secReq.getId(), secReq);
    }

    public void delete(int id) {
        NO_SQL_DATA_STORE.remove(id);
    }

    private void checkExistence(int id) {
        if(!NO_SQL_DATA_STORE.containsKey(id)) {
            throw new SecReqNotExistsException();
        }
    }


}
