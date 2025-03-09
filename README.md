# MyBatis Member MVC Practice

## ✨ 프로젝트 개요

이 프로젝트는 **MyBatis**를 활용한 **MVC 패턴** 연습용 회원 관리 시스템입니다. Java 콘솔 환경에서 동작하며, 회원의 **추가, 삭제, 수정, 조회** 기능을 제공합니다.

## ✨ 사용 기술

- **Java (JDK 8 이상)**: 프로젝트의 기본 언어
- **MyBatis**: SQL 매퍼 프레임워크로 DB 연동 처리
- **JDBC (Oracle DB)**: 데이터베이스 연결 및 쿼리 실행
- **MVC 패턴**: Model-View-Controller 구조로 설계
- **DAO 패턴**: 데이터 접근 객체를 분리하여 캡슐화
- **Singleton 패턴**: Service 및 DAO 클래스의 인스턴스 관리
- **XML 기반 MyBatis 설정**: `mybatis-config.xml`, `member-mapper.xml` 활용

## ✨ 주요 기능

1. **회원 추가**
    - ID 중복 체크 후 새로운 회원 등록
2. **회원 삭제**
    - 특정 ID의 회원 삭제
3. **회원 수정**
    - 전체 정보 수정 또는 개별 필드 수정
4. **회원 목록 조회**
    - 모든 회원 정보 출력

```
javac -d bin src/member/**/*.java
java -cp bin member.controller.MemberController

```

## ✨ 데이터베이스 테이블

```sql
CREATE TABLE MYMEMBER (
    MEM_ID VARCHAR2(50) PRIMARY KEY,
    MEM_PASS VARCHAR2(100) NOT NULL,
    MEM_NAME VARCHAR2(100) NOT NULL,
    MEM_TEL VARCHAR2(20),
    MEM_ADDR VARCHAR2(255)
);

```

