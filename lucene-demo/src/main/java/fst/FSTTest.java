package fst;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FSTTest {
    public static void main(String[] args) throws IOException {
        //"abcde","abdde","abede","accde","adcde"
        //针对以上5个字符串进行FST 构建
        String[] values = new String[]{"abcde", "abdde", "abede", "accde", "adcde"};
        ExtraInfo[] outValues = new ExtraInfo[]{
                ExtraInfo.of("高危", 1),
                ExtraInfo.of("中威", 2),
                ExtraInfo.of("低危", 3),
                ExtraInfo.of("普通", 4),
                ExtraInfo.of("优质", 5)
        };

        Builder<Long> builder = new Builder<>(FST.INPUT_TYPE.BYTE1, PositiveIntOutputs.getSingleton());
        //针对 values 排序，得到最小的 fst
        Arrays.sort(values);
        IntsRefBuilder intsRefBuilder = new IntsRefBuilder();
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            BytesRef bytesRef = new BytesRef(value.getBytes(StandardCharsets.UTF_8));
            long outValue = outValues[i].getValue();
            intsRefBuilder.copyUTF8Bytes(bytesRef);
            builder.add(intsRefBuilder.toIntsRef(), outValue);
        }
        FST<Long> fst = builder.finish();

        Long outValue = Util.get(fst, new BytesRef("abcde".getBytes(StandardCharsets.UTF_8)));
        System.out.println(outValue);

    }
}
