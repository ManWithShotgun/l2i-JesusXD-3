package ru.catssoftware.gameserver.datatables.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public abstract class XMLDocument {
	public void load(File documentFile) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		parseDocument(factory.newDocumentBuilder().parse(documentFile));
	}
	
	abstract protected void parseDocument(Document doc);
}
