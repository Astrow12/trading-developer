package org.algo.rmb.orders;

import java.util.*;
import java.util.stream.Collectors;

public class OrderBook {

    String id;

    Queue<Order> priorityQueue = new PriorityQueue<Order>(new OrderComparator());
    static class OrderComparator implements Comparator<Order> {
        public int compare(Order first, Order second) {
            if (first.getPrice().equals(second.getPrice())) {
                return first.getTimestamp().compareTo(second.getTimestamp());
            } else {
                return (-1) * first.getPrice().compareTo(second.getPrice()); //descending order
            }
        }
    }

    public OrderBook(Queue<Order> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }


    //Add an order into orderBook
    private String addOrder(Order order) {
        if (order == null)
            return "Order cannot be null";

        List<Order> orders = priorityQueue.stream().collect(Collectors.toList());
        if (order.getQuantity() > 0) {
            orders.add(order);
            Collections.sort(orders,new OrderComparator());
        }
        priorityQueue.clear();
        priorityQueue.addAll(orders);
        return "Added successfully";
    }

    //Deleted an order into orderBook
    private String deleteOrder(String orderId) {

        List<Order> orders = priorityQueue.stream().collect(Collectors.toList());
        Order orderToBeRemoved = orders.stream().filter(e -> e.getId().equals(orderId)).findFirst().get(); //Get the order to be deleteds
        orders.remove(orderToBeRemoved);
        Collections.sort(orders,new OrderComparator());
        priorityQueue.clear();
        priorityQueue.addAll(orders);
        return "Order: " + orderId + "deleted successfully";
    }

    //Modify an order into orderBook
    public String modifyOrder(String orderId, long quantity) {
        List<Order> orders = priorityQueue.stream().collect(Collectors.toList());
        Order orderToBeModified = orders.stream().filter(e -> e.getId().equals(orderId)).findFirst().get();
        orderToBeModified.setQuantity(quantity);
        //Find index in order to replace current order with modified order
        int currentIndex = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(orderToBeModified)) {
                currentIndex = i;
                break;
            }
        }
        orders.set(currentIndex,orderToBeModified);
        priorityQueue.clear();
        priorityQueue.addAll(orders);
        return "Order: " + orderId + "modfified successfully";
    }


}
