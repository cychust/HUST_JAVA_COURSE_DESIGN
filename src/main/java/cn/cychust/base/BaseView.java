package cn.cychust.base;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:24
 **/
public interface BaseView<T> {
    void setPresenter(T presenter);
}
