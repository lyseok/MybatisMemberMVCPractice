package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import member.dao.MemberDaoImpl;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVO;

public class MemberController {
	private Scanner sc;
	private IMemberService service;
	
	public MemberController() {
		sc = new Scanner(System.in);
		service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}
	
	public void startMember() {
		while(true) {
			switch(displayMenu()) {
				case 1: insertMember(); break; // 추가
				case 2: deleteMember(); break; // 삭제
				case 3: updateMember(); break; // 수정
				case 4: selectUpdateMember(); break;
				case 5: printAllMember(); break; // 전체출력
				case 0: System.out.println("작업을 마칩니다");  return; // 종료
				default :
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	

	private int displayMenu() {
		System.out.println();
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 선택 자료 수정");
		System.out.println("5. 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.print("작업을 선택하세요 >> ");
		
		return sc.nextInt();
	}
	
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		int count = 0;			// 회원 ID의 개수가 저장될 변수
		String memId = null;	// 회원 ID가 저장될 변수
		
		do {
			System.out.print("회원ID >> ");
			memId = sc.next();
			count = service.getMemberIdCount(memId);
			
			if(count > 0) {
				System.out.println("입력한" + memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요");
				System.out.println();
			}
		} while(count > 0);
		System.out.print("비밀번호 >>");
		String pass = sc.next();
		System.out.print("이  름 >> ");
		String name = sc.next();
		System.out.print("전화번호 >> ");
		String tel = sc.next();
		System.out.print("주  소 >> ");
		sc.nextLine();
		String addr = sc.nextLine();
		
		// 입력 받은 자료를 VO객체에 저장한다.
		MemberVO memberVo = new MemberVO();
		memberVo.setMem_id(memId);
		memberVo.setMem_pass(pass);
		memberVo.setMem_name(name);
		memberVo.setMem_tel(tel);
		memberVo.setMem_addr(addr);
		
		int cnt = service.insertMember(memberVo);
		
		if(cnt > 0) {
			System.out.println("insert 작업 성공");
		} else {
			System.out.println("insert 작업 실패");
		}
	}
	
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = sc.next();
		int count = service.getMemberIdCount(memId);
		
		if(count == 0) {
			System.out.println("삭제할 ID가 존재하지 않습니다");
			System.out.println();
			return;
		};
		
		int cnt = service.deleteMember(memId);
		if(cnt > 0) {
			System.out.println("삭제 작업 성공");
		} else {
			System.out.println("삭제 작업 실패");
		}	
	}
	
	private void updateMember() {
		int cnt = 0;
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = sc.next();
		int count = service.getMemberIdCount(memId);
		
		if(count == 0) {
			System.out.println("수정할 ID가 존재하지 않습니다");
			System.out.println();
			return;
		}
		
		System.out.print("새로운 비밀번호 >> ");
		String newPass = sc.next();
		System.out.print("새로운 이름 >> ");
		String newName = sc.next();
		System.out.print("새로운 전화번호 >> ");
		String newTel = sc.next();
		sc.nextLine();
		System.out.print("새로운 주소 >> ");
		String newAddr = sc.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newPass);
		memVo.setMem_name(newName);
		memVo.setMem_tel(newTel);
		memVo.setMem_addr(newAddr);
		
		cnt = service.updateMember(memVo);
		
		if(cnt > 0) {
			System.out.println("insert 작업 성공");
		} else {
			System.out.println("insert 작업 실패");
		}
	}
	
	private void selectUpdateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = sc.next();
		int count = service.getMemberIdCount(memId);
		
		if(count == 0) {
			System.out.println("수정할 ID가 존재하지 않습니다");
			System.out.println();
			return;
		}
		String selectStr = "";
		int select = 0;
		do {
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1. 비밀번호 2. 이름 3. 전화번호 4. 주소");
			System.out.print("선택 >> ");
			select = sc.nextInt();
			switch(select) {
				case 1: 
					selectStr = "MEM_PASS";
					System.out.print("새로운 비밀번호 >> ");
					break;
				case 2: 
					selectStr = "MEM_NAME";
					System.out.print("새로운 이름 >> ");
					break;
				case 3: 
					selectStr = "MEM_TEL";
					System.out.print("새로운 전화번호 >> "); 
					break;
				case 4: 
					selectStr = "MEM_ADDR";
					System.out.print("새로운 주소 >> "); 
					break;
				default:
					System.out.println("수정 항목 선택이 잘못되었습니다 다시선택하세요");
			}
		} while(select <1 || select > 4);
		
		sc.nextLine();
		String newValue = sc.nextLine();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("MEM_ID", memId);
		data.put("FIELD", selectStr);
		data.put("DATA", newValue);
		
		int cnt = service.selectUpdateMember(data);
		
		if(cnt > 0) {
			System.out.println("수정 작업 성공");
		} else {
			System.out.println("수정 작업 실패");
		}
	}
	
	private void printAllMember() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("ID\tPassword\t이름\t전화번호\t\t주소");
		System.out.println("---------------------------------------------------");
		
		List<MemberVO> list = service.getAllMember();
		
		
		if(list.size() == 0 || list == null) {
			System.out.println("등록된 회원이 없습니다");
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.printf("%s\t%s\t\t%s\t%s\t%s\n",
						list.get(i).getMem_id(),
						list.get(i).getMem_pass(), 
						list.get(i).getMem_name(),
						list.get(i).getMem_tel(),
						list.get(i).getMem_addr());
			}
			System.out.println("\n총" + list.size() +"명의 회원이 출력되었습니다");
		}
		System.out.println("---------------------------------------------------");
	
	}
	
}
