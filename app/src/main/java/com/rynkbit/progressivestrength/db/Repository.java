package com.rynkbit.progressivestrength.db;

import java.util.List;

/**
 * Created by michael on 22.03.18.
 */

public interface Repository<T> {
    List<T> getAll();
}
