package com.xing.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.xing.dao.StudentDao;
import com.xing.entity.Student;
import com.xing.exception.StudentNotExistException;

public class Main {
	public static void main(String[] args) {

		try {
			while (true) {
				System.out.println("Add student(a)    Delete student(b)    Find student(c)  Exit(e) ");
				System.out.print("input operation code: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String type = br.readLine();
				if ("a".equals(type.trim())) {
					System.out.print("input student name: ");
					String name = br.readLine();
					System.out.print("input student exam id: ");
					String examid = br.readLine();
					System.out.print("input student card id: ");
					String idcard = br.readLine();
					System.out.print("input student location: ");
					String location = br.readLine();
					System.out.print("input student grade: ");
					String grade = br.readLine();
					
					Student s = new Student();
					s.setExamid(examid);
					s.setName(name);
					s.setIdcard(idcard);
					s.setGrade(Double.parseDouble(grade));
					s.setLocation(location);
					StudentDao dao = new StudentDao();
					dao.add(s);
					System.out.println("--------add student successfully----------");
				}
				else if ("b".equals(type.trim())) {
					System.out.print("Please input the name of student to be deleted: ");
					String name = br.readLine();
					try {
						StudentDao dao = new StudentDao();
						dao.delete(name);
						System.out.println("-----------------delete successfuly-----------------------");
					}catch(StudentNotExistException e) {
						System.out.println("The target user is not existing.");
					}
				}
				else if ("c".equals(type.trim())) {
					System.out.print("Please input the exam id of target student: ");
					String examid = br.readLine();
					StudentDao dao = new StudentDao();
					Student s = dao.find(examid);
					if (s == null) {
						System.out.println("The target student is not existing.");
					} else {
						System.out.println("-------------------------------------------");
						System.out.println("name = " + s.getName());
						System.out.println("exam id = " + s.getExamid());
						System.out.println("card id = " + s.getIdcard());
						System.out.println("location = " + s.getLocation());
						System.out.println("grade = " + s.getGrade());
					}
				} else if ("e".equals(type.trim())) {
					break;
				} else {
					System.out.println("can's support this operation.");
				}
			}
			
		} catch (RuntimeException e) {
			System.out.println("we come across some problems, please wail ...");
		} catch (IOException e) {
			System.out.println("The input is crashed, please reboot the system.");
			e.printStackTrace();
		}
		
	}
}
