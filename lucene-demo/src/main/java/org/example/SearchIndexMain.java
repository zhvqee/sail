package org.example;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class SearchIndexMain {
    public static void main(String[] args) throws IOException {
        FSDirectory fsDirectory = FSDirectory.open(BuildIndexMain.indexFile.toPath());
        IndexReader indexReader = StandardDirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new TermQuery(new Term("content", "laudantium"));
        TopDocs topDocs = indexSearcher.search(query, 1000);
        long totalHits = topDocs.totalHits.value;
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        System.out.println(totalHits);
        for (ScoreDoc sc : scoreDocs) {
            System.out.println(sc);

        }

        WildcardQuery wildcardQuery = new WildcardQuery(new Term("content", "lau*um"));
        TopDocs topDocs1 = indexSearcher.search(wildcardQuery, 1000);
        long totalHits1 = topDocs.totalHits.value;
        ScoreDoc[] scoreDocs1 = topDocs.scoreDocs;
        System.out.println(totalHits1);
        for (ScoreDoc sc : scoreDocs1) {
            System.out.println(sc);

        }
    }
}
