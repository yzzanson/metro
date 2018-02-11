package com.jy.metro.util;

import com.jy.metro.bean.NewsEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Saber on 2018/2/8.13:56
 */
public class XmlParseUtil {
    private static List<NewsEntity> newsEntityList = null;

    public static List<NewsEntity> readStringXml(String xml) {
        newsEntityList = new ArrayList<>();
        Document doc = null;
        try {
            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator iter = rootElt.element("datas").elementIterator("data"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                NewsEntity newsEntity = new NewsEntity();
                Element recordEle = (Element) iter.next();
                newsEntity.setId(recordEle.elementTextTrim("id"));
                newsEntity.setTitle(recordEle.elementTextTrim("title"));
                newsEntity.setContent(recordEle.elementTextTrim("content"));
                newsEntity.setKeyWords(recordEle.elementTextTrim("keyWords"));
                newsEntity.setSource(recordEle.elementTextTrim("source"));
                newsEntity.setSeq(recordEle.elementTextTrim("seq"));
                newsEntity.setSecret(recordEle.elementTextTrim("secret"));
                newsEntity.setFocusFlag(recordEle.elementTextTrim("focusFlag"));
                newsEntity.setSubject(recordEle.elementTextTrim("subject"));
                newsEntity.setPicPath(recordEle.elementTextTrim("picPath"));
                newsEntity.setPubDate(recordEle.elementTextTrim("pubDate"));

                newsEntityList.add(newsEntity);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsEntityList;
    }
}
