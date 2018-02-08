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
            Iterator iter = rootElt.elementIterator("datas"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                NewsEntity newsEntity = new NewsEntity();
                Element recordEle = (Element) iter.next();
                Iterator iters = recordEle.elementIterator("data"); // 获取子节点head下的子节点script
                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    newsEntity.setId(itemEle.elementTextTrim("id"));
                    newsEntity.setTitle(itemEle.elementTextTrim("title"));
                    newsEntity.setContent(itemEle.elementTextTrim("content"));
                    newsEntity.setKeyWords(itemEle.elementTextTrim("keyWords"));
                    newsEntity.setSource(itemEle.elementTextTrim("source"));
                    newsEntity.setSeq(itemEle.elementTextTrim("seq"));
                    newsEntity.setSecret(itemEle.elementTextTrim("secret"));
                    newsEntity.setFocusFlag(itemEle.elementTextTrim("focusFlag"));
                    newsEntity.setSubject(itemEle.elementTextTrim("subject"));
                    newsEntity.setPicPath(itemEle.elementTextTrim("picPath"));
                    newsEntity.setPubDate(itemEle.elementTextTrim("pubDate"));

                    newsEntityList.add(newsEntity);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsEntityList;
    }
}
