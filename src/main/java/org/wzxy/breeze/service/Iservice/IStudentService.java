package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.StudentDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IStudentService {


	  // public  String deleteStudentById(int id);

	  // public StudentDto queryStudentById(int id) ;


	   public HandleResult SignInByStudent() ;

	   //////////////////////��ʦ�Ǳ߰������
	   public Page<StudentDto> getStudentsByIds(List<String> AllStudentId, int stundentNum, int courseId, int nowpage, int pagesize);
	   public List<StudentDto> getStudents(int coureId);
	   public Page<StudentDto> getRandomStudents(int coureId, int stundentNum) ;

	   ////////////////////////��ʦ�Ǳ߰������


	   /////ѧ��
		public HandleResult addStudent(StudentDto studto) ;

		public HandleResult deleteStudentById(int studentId) ;

		public StudentDto queryStudentById(int studentId) ;

		public List<StudentDto> queryStudentByClassId(int classId) ;

		public HandleResult updateStudent(StudentDto studto);

		public Page<StudentDto> StudentPaging(List<StudentDto> studentDtos, int coureId, int nowPage, int pageSize) ;



}
