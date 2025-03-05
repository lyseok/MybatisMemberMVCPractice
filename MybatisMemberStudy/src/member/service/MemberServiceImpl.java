package member.service;

import java.util.List;
import java.util.Map;

import member.dao.IMemberDao;
import member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
private static MemberServiceImpl instance;
private IMemberDao dao;
	
	private MemberServiceImpl(IMemberDao dao) {
		this.dao = dao;
	};
	
	public static MemberServiceImpl getInstance(IMemberDao dao) {
		if(instance == null) {
			instance = new MemberServiceImpl(dao);
		}
		return instance;
	}
	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public int selectUpdateMember(Map<String, String> data) {
		return dao.selectUpdateMember(data);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberIdCount(String memId) {
		return dao.getMemberIdCount(memId);
	}

}
