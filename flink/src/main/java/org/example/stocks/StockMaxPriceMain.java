package org.example.stocks;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StockMaxPriceMain {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        DataStreamSource<StockPrice> stockSource = env.addSource(new StockSource("stock.txt"));

        stockSource.keyBy("symbol").max("price").print();

        stockSource.map(e -> {
            StockPrice clone = e.clone();
            clone.price /= 2;
            return clone;
        }).print();

        env.execute("stock-max-price");
    }
}
