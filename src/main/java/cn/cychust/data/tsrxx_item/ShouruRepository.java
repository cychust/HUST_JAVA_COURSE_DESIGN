package cn.cychust.data.tsrxx_item;

import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.data.tghxx_item.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 21:14
 **/
public interface ShouruRepository {
    void getAllBetween(Timestamp start, Timestamp end, LoadTsrxxItemCallback callback);

    void getAll(LoadTsrxxItemCallback callback);

    interface LoadTsrxxItemCallback {
        void onTasksLoaded(List<SRXX_Item> list);

        void onDataNotAvailable();
    }

    interface GetTsrxxItemCallback {
        void onTasksLoaded(SRXX_Item ghxx_item);

        void onDataNotAvailable();
    }
}
