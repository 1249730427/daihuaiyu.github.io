package service;

import org.springframework.beans.factory.annotation.Value;

/**
 * StartService
 *
 * @author daihuaiyu
 * @create: 2021-02-23 17:12
 **/
public class StartService {

    @Value("itstack.door:123456789")
    private String userIds;

    public StartService(String userIds) {
        this.userIds = userIds;
    }

    public String[] splitUserIds(String characterSplit){

        return userIds.split(characterSplit);
    };
}

