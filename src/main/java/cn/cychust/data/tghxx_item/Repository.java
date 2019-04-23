package cn.cychust.data.tghxx_item;

import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 20:09
 **/
public interface Repository {

    void getAllByYS(String id,LoadTghxxItemCallback callback);

    interface LoadTghxxItemCallback {
        void onTasksLoaded(List<GHXX_Item> list);

        void onDataNotAvailable();
    }

    interface GetTghxxItemCallback {
        void onTasksLoaded(GHXX_Item ghxx_item);

        void onDataNotAvailable();
    }
}
