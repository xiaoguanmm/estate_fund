package com.upjf.fund.utils.dom4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析xml
 * @author wufujing
 *
 */
public class XmlUtil {
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String, Object> doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map<String, Object> m = new HashMap<String, Object>();
		InputStream in = String2Inputstream(strxml);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		Element root = doc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			if ((e.elements()).size() == 0) {
				v = e.getStringValue();
			} else {
				v = getChildrenText(e.elements());
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
		
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		Map<String, Object> m =null;
		if (!children.isEmpty()) {
			 m = new HashMap<String, Object>();
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String k = e.getName();
				String v = "";
				if ((e.elements()).size() == 0) {
					v = e.getStringValue();
				} else {
					v = getChildrenText(e.elements());
				}

				m.put(k, v);
			}
		}

		return m.toString();
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
}
