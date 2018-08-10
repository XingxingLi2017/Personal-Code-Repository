package com.xing.dao;

import java.rmi.StubNotFoundException;

import javax.management.RuntimeErrorException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xing.entity.Student;
import com.xing.exception.StudentNotExistException;
import com.xing.utils.XmlUtils;

public class StudentDao {
	
	public void add(Student s) {
		try {
			Document document = XmlUtils.getDocument();
			// create student tags first
			Element student_tag = document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			
			// create name location grade tags
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(Double.toString(s.getGrade()));
			
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			// append student tag
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			// write the new document
			XmlUtils.write2Xml(document);			
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Student find(String examid) {
		try {
			Document document = XmlUtils.getDocument();
			NodeList list = document.getElementsByTagName("student");
			for (int i = 0 ; i < list.getLength() ; i++) {
				Element student_tag = (Element) list.item(i);
				if (student_tag.getAttribute("examid").equals(examid)) {
					// if find the student, encapsulate the information of the student
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public void delete(String name) throws StudentNotExistException {
		try {
			Document document = XmlUtils.getDocument();
			NodeList list = document.getElementsByTagName("name");
			for (int i = 0 ; i < list.getLength() ; i++) {
				 if (list.item(i).getTextContent().equals(name)) {
					 // get grandfather and delete the parent node
					 list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					 XmlUtils.write2Xml(document);
					 return;
				 }
			}
			throw new StudentNotExistException(name + " don't exist");
		} catch(StudentNotExistException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
