package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.util.MyBatisUtil;
import member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl instance;
	
	private MemberDaoImpl() {};
	
	public static MemberDaoImpl getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("member.insertMember", memVo);
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("insert 실패");
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.delete("member.deleteMember", memId);
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("delete 실패");
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("member.updateMember", memVo);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("update 실패");
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int selectUpdateMember(Map<String, String> data) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("member.selectUpdateMember", data);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("member.getAllMember");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return list;
	}

	@Override
	public int getMemberIdCount(String memId) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.selectOne("member.getMemberIdCount", memId);
			
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

}
