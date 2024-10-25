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
        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(" ");
                Document document = new Document();
                document.add(new StringField("note_id", split[0], Field.Store.YES));
                document.add(new TextField("content", split[1], Field.Store.YES));
                indexWriter.addDocument(document);
            }
            bufferedReader.close();
        }
        indexWriter.flush();
        indexWriter.close();

    }
}
