package org.algo.rmb.orders;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    enum Side {
        BUY,
        SELL;
    }


    private String id;

    private Side side;

    private BigDecimal price;

    private long quantity;

    private Date timestamp;

    private Order(Order order) {
        this.id = order.getId();
        this.side = order.getSide();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
        timestamp = new Date();
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setSide(Side side) {
        this.side = side;
    }
    public Side getSide() {
        return side;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public long getQuantity() {
        return quantity;
    }

    public void setTimestamp(Date date) {

        this.timestamp =  date;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
