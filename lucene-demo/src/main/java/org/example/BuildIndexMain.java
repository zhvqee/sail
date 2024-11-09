package org.example;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.FSLockFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildIndexMain {
    public static File indexFile = new File("/Users/user/Dowloads/workspace/sail/lucene-demo/lucene/index");
    public static File dataFile = new File("/Users/user/Dowloads/workspace/sail/lucene-demo/lucene/data");

    public static void main(String[] args) throws IOException {
        File[] files = dataFile.listFiles();

        FSDirectory fsDirectory = FSDirectory.open(indexFile.toPath(), FSLockFactory.getDefault());
        IndexWriter indexWriter = new IndexWriter(fsDirectory, new IndexWriterConfig(new StandardAnalyzer()));
        for(int j=1;j<2;j++) {
            for (File file : files) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] split = line.split(" ");
                    Document document = new Document();
                    // document.add(new StringField("note_id", split[0], Field.Store.YES));
                    document.add(new TextField("content", "START" + split[1] + "end", Field.Store.YES));
                    indexWriter.addDocument(document);
                   // indexWriter.commit();
                    //DEFAULT_MAX_BLOCK_SIZE
                    // i 大于48时，才会产生一个新block ，才会有FST (至少2个block 才会有FST)
               /* i++;
                if (i > 48) {
                    break;
                }*/
                }
                bufferedReader.close();
            }
        }
        //indexWriter.flush();
        indexWriter.close();

    }
}
