package org.example.stocks;

public class StockPrice implements Cloneable {

    //股票代号
    public String symbol;

    public long ts;

    public double price;

    public long volume;


    @Override
    public String toString() {
        return "StockPrice{" + "symbol='" + symbol + '\'' + ", ts=" + ts + ", price=" + price + ", volume=" + volume + '}';
    }

    @Override
    public StockPrice clone() {
        try {
            StockPrice clone = (StockPrice) super.clone();
            clone.symbol = clone.symbol + "-clone";
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
