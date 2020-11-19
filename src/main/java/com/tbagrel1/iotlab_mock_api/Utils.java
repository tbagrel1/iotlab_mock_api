package com.tbagrel1.iotlab_mock_api;

import com.tbagrel1.iotlab_mock_api.exceptions.BadRequest;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> void ensureNotNull(String fieldName, T value) {
        if (value == null) {
            throw new BadRequest(String.format("field '%s' should be specified", fieldName));
        }
    }

    public static <I> List<I> toList(Iterable<I> iterable) {
        List<I> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
