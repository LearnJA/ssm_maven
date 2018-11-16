package com.baizhi.lucenetest;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexInput;

import java.io.File;
import java.io.IOException;

public class SearchLuceneIndex {
    public static void main(String[] args) throws IOException {
        Directory dir=FSDirectory.open(new File("f:/lucene/index"));
        IndexReader reader=DirectoryReader.open(dir);
        IndexSearcher indexSearcher=new IndexSearcher(reader);
        //参数1：索引查询的关键字   查询条件
        Query query=new TermQuery(new Term("author","大黄"));
        //参数2：查询条数
        TopDocs topDocs=indexSearcher.search(query,100);
        //相关排序后的结果
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        for (int i=0;i<scoreDocs.length;i++){
            ScoreDoc scoreDoc=scoreDocs[i];
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            String title = document.get("title");
            String author = document.get("author");
            String content = document.get("content");
            System.out.println(title);
            System.out.println(author);
            System.out.println(content);
            System.out.println("=============================");
        }
    }
}
