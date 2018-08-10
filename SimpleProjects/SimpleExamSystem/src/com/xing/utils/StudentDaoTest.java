package com.xing.utils;

import java.rmi.StubNotFoundException;

import org.junit.Test;

import com.xing.dao.StudentDao;
import com.xing.entity.Student;
import com.xing.exception.StudentNotExistException;

public class StudentDaoTest {
	
	@Test
	public void testAdd() {
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExamid("2333");
		s.setGrade(78);
		s.setIdcard("2333");
		s.setLocation("NewYork");
		s.setName("test2");
		dao.add(s);
	}
	
	@Test
	public void testFind() {
		StudentDao dao = new StudentDao();
		dao.find("2333");
	}
	
	@Test
	public void testDelete() throws StudentNotExistException {
		StudentDao dao = new StudentDao();
		dao.delete("test2");
	}
}
