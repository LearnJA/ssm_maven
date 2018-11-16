package com.baizhi.lucene;

import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.junit.Test;
import java.io.IOException;

public class Testlucene {

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void testCreatIndex() throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        //创建页面对象
        for(int i=0;i<10;i++){
            Document doc=new Document();
            doc.add(new StringField("id",String.valueOf(i),Field.Store.YES));
            doc.add(new StringField("title","英雄联盟"+i,Field.Store.YES));
            doc.add(new StringField("author","大黄"+i,Field.Store.YES));
            doc.add(new TextField("content","和班长大黄同居的日子，无比刺激",Field.Store.YES));
            //页面对象写入索引库
            indexWriter.addDocument(doc);
        }
        indexWriter.commit();
    }

    /**
     * 查询索引
     * @throws IOException
     */
    @Test
    public void testSeachIndex() throws IOException {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //检索字段和关键字
        Query query=new TermQuery(new Term("content","黄"));
        //检索次数100条
        TopDocs topDocs=indexSearcher.search(query,100);
        //相关度排序（数组）
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        //创建结果产品集合
        //List<Product> pros=null;
        for(int i=0;i<scoreDocs.length;i++){
            //获取页面对象相关编号
            int doc=scoreDocs[i].doc;
            //根据编号获取document页面对象
            Document document=indexSearcher.doc(doc);
            //检索对象相关度
            float score = scoreDocs[i].score;
            //打印检索结果
            System.out.println("***********//"+score);
            System.out.println("***********//"+document.get("id"));
            System.out.println("***********//"+document.get("title"));
            System.out.println("***********//"+document.get("author"));
            System.out.println("***********//"+document.get("content"));
            System.out.println("==============================");
        }
    }

    /*删除索引*/
    @Test
    public void deleteLucene() throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        //删除所有索引
        //indexWriter.deleteAll();
        //根据id删除索引
        indexWriter.deleteDocuments(new Term("id","1"));
        indexWriter.commit();
    }

    //修改索引
    @Test
    public void updateLucene() throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        //创建索引对象
        Term term=new Term("id","0");
        //创建页面对象
        Document document=new Document();
        document.add(new StringField("id","1",Field.Store.YES));
        document.add(new StringField("title","复仇者联盟",Field.Store.YES));
        document.add(new StringField("author","小黄",Field.Store.YES));
        document.add(new TextField("content","和班长大黄同居的日子，无比刺激",Field.Store.YES));
        /*写入索引库*/
        indexWriter.updateDocument(term,document);
        indexWriter.commit();
    }
}
