package com.ddubson.service.user.profile;

import java.util.List;

public interface Repository<T, ID_TYPE> {
    List<T> fetchAll();

    T findById(ID_TYPE id);
}
