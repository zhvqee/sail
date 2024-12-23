package org.example.stocks;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class StockSource implements SourceFunction<StockPrice> {

    private InputStream inputStreamSource;

    private final String path;

    public StockSource(String path) {
        this.path = path;
    }

    //这里是读取数据地方
    @Override
    public void run(SourceContext<StockPrice> sourceContext) throws Exception {
        inputStreamSource = this.getClass().getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamSource));
        String line = null;
        while ((line = reader.readLine()) != null) {
            StockPrice stockPrice = new StockPrice();
            String[] split = line.split("\\s");
            stockPrice.symbol = split[0];
            stockPrice.ts = Long.valueOf(split[1]);
            stockPrice.price = Double.valueOf(split[2]);
            stockPrice.volume = Long.valueOf(split[3]);
            Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
            sourceContext.collect(stockPrice);
        }
    }

    @Override
    public void cancel() {
        try {
            if (inputStreamSource != null) {
                inputStreamSource.close();
            }
        } catch (IOException e) {

        }
    }
}
