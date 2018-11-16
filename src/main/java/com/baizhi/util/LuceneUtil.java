package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * 封装Lucene全文检索
 */
public class LuceneUtil {

    private static Version version;
    //磁盘索引库路径
    private static Directory directory;
    //配置索引  分词器
    private static IndexWriterConfig writerConfig;
    private static Analyzer analyzer;
    private static IndexReader reader;
    private static IndexSearcher indexSearcher;
    //创建页面索引写入对象
    private static IndexWriter indexWriter;
    static{
        try {
            version=Version.LUCENE_44;
            directory = FSDirectory.open(new File("f:/lucene/test1"));
            analyzer=new StandardAnalyzer(version);
            writerConfig=new IndexWriterConfig(version,analyzer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //创建索引
    public static IndexWriter getIndexWriter(){
        try {
            indexWriter=new IndexWriter(directory,writerConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    //查询索引
    public static IndexSearcher getIndexSearcher(){
        try {
            reader=DirectoryReader.open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        indexSearcher=new IndexSearcher(reader);
        return indexSearcher;
    }

    //提交
    public static void commit(IndexWriter indexWriter){
        try {
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回
    public static void rollback(IndexWriter indexWriter){
        try {
            indexWriter.rollback();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
