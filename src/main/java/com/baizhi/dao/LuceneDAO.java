package com.baizhi.dao;

import com.baizhi.bean.Product;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LuceneDAO {
    /*添加索引*/
    public void addLucene(Product product){
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try{
            Document document=getDocument(product);
            indexWriter.addDocument(document);
            //页面对象索引写入索引库
            LuceneUtil.commit(indexWriter);
        }catch (Exception e){
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    //全文索引
    public List<Product> searchLucene(String key){
        //创建结果产品集合
        List<Product> pros=null;
        Product product=null;
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //检索字段
        Query query=new TermQuery(new Term("name",key));
        try {
            TopDocs topDocs=indexSearcher.search(query,100);
            ScoreDoc[] scoreDocs=topDocs.scoreDocs;
            for(int i=0;i<scoreDocs.length;i++){
                int doc = scoreDocs[i].doc;
                Document document=indexSearcher.doc(doc);
                product=getProduct(document);
                pros.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pros;
    }

    //转换document
    public Document getDocument(Product product){
        Document doc=null;
            doc.add(new StringField("id",product.getId(),Field.Store.YES));
            doc.add(new StringField("name",product.getName(),Field.Store.YES));
            doc.add(new StringField("price",String.valueOf(product.getPrice()),Field.Store.YES));
            doc.add(new StringField("desctory",product.getDesctory(),Field.Store.YES));
            doc.add(new StringField("date",product.getDesctory(),Field.Store.YES));
            doc.add(new StringField("addr",product.getAddr(),Field.Store.YES));
        return doc;
    }

    /*转换成product*/
    public Product getProduct(Document document){
        Product product=null;
            product.setId(document.get("id"));
            product.setName(document.get("name"));
            product.setPrice(Double.valueOf(document.get("price")));
            product.setDesctory(document.get("desctory"));
            product.setAddr(document.get("add"));
            product.setDate(document.get("date"));
            product.setStatus(Integer.valueOf(document.get("status")));
        return product;
    }
}
