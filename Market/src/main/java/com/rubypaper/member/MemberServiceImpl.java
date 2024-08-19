package com.rubypaper.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao dao;
	
	MemberServiceImpl(){
		System.out.println("MemberServiceImpl 생성자");
	}
	@Override
	public void insert(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public List<MemberVO> select(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.select(vo);
	}
	@Override
	public MemberVO edit(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.edit(vo);
	}
	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}
	@Override
	public void deleteUser(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.deleteUser(vo);
	}
	@Override
	public void updateUser(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.updateUser(vo);
	}
	@Override
	public List<MemberVO> selectUser(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.selectUser(vo);
	}
	

}
