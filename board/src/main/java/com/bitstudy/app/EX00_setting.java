package com.bitstudy.app;
/*
* 1) pom.xml 설정
*       mvn repo에서 mybatis 검색 > mybatis, mybatis spring 모듈 pom.xml에 넣고 업데이트
* 2) web.xml 한글 필터 설정
*       게시판에서 한글 깨짐 방지
* 3) root-context.xml 설정
*       <bean id="sqlSessionFactory"></bean>
*       <bean id="sqlSession"></bean>
*       검색해서 집어넣기
* 4) servlet-context.xml 설정
*       <view-controller path="/" view-name="index"/>
*       이거 들어있으면 홈컨트롤러 없이 index로 보낼수있음
* 5) boardMapper.xml 생성
*       main > resource 에 mapper 폴더 생성, 그 안에 boardMapper.xml 생성
*       여기에 모든 sql문들을 다 넣어서 관리
* 6) mybatis-config.xml 생성
*       boardMapper 에서 사용할 별칭 문서 (Dto경로를 "Dto"으로 지정)
*       main > resource 폴더에 생성 (webapp 안의 resource가 아님!)
* 7) view 파일들 복사해오기
* 8) com.bitstudy.app 밑에 controller, dao, domain 패키지 생성
* 9) controller 패키지 안에 LoginController 만들기
* 10) userDao 등록
* 11)
* */
public class EX00_setting {
}
