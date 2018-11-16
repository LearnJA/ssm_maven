package com.baizhi.lucenetest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class CreateLuceneIndex {
    public static void main(String[] args) throws IOException {
        Directory dir=FSDirectory.open(new File("f:/lucene/index"));
        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_44,analyzer);
        //构建索引写入对象，参数1：索引库的路径  参数2：索引写入对象相关配置（分词器）
        IndexWriter indexWriter=new IndexWriter(dir,conf);
        //构建文档对象模型（Document）
        Document document=new Document();
        document.add(new StringField("title","和班长", Field.Store.YES));
        document.add(new StringField("author","大黄", Field.Store.YES));
        document.add(new StringField("content","刺激，不可描述的事。", Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.commit();
    }
}
