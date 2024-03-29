package ru.job4j.ood.isp.notisp;

/**
 * Сервис по работе с заказами на какой-нибудь товар.
 * Нарушение может быть таким: могут быть компании, которые только выдают заказы,
 * но не доставляют по адресу.
 * Или наоборот: только доставляют, но не выдают. В этих случаях у одной компании
 * будет лишним метод deliverOrder(), а у другой - issueOrder()
 */
public interface OrderService {

    /**
     * Создать заказ
     */
    void createOrder();

    /**
     * Выдать заказ
     */
    void issueOrder();

    /**
     * Доставить заказ
     */
    void deliverOrder();
}
