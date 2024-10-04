# 내일배움캠프 스프링 5주차 개인 과제 일정 알람 앱 서버 만들기
## 프로젝트 소개 스프링 2주차 강의를 참고하여 만든 과제입니다.
## 개발 기간
* 24.09.26 ~ 24.10.4
### 기술 스택
* Java
* Spring
* MYSQL
* git
* github
  
## 주요 기능 
### 1. 일정 생성(일정 작성하기)
* 할일, 작성자명, 비밀번호, 작성/수정일을 저장할 수 있습니다.
* 작성/수정일은 날짜와 시간을 모두 포함한 형태로 구현했습니다.
* 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리하고 있습니다.
  

### 2. 일정 조회(등록된 일정 불러오기)
* 생성된 일정을 조회할 수 있습니다.


### 3. 일정 수정 및 삭제
* 선택한 일정을 수정 및 삭제할 수 있습니다.
* 수정 시 수정한 날짜로 수정일이 변경되게 구현했습니다.

 
## 미완성한 부분
### 1. 선택 일정 조회(선택한 일정 정보 불러오기)(미완성)
* 조건을 바탕으로 등록된 일정 목록을 전부 조회하는 기능을 구현하지 못했습니다.

### 2. 비밀번호 기능(미완)
* 비밀번호 기능을 사용하여 일정 관리에 보안성을 높이려고 노력했지만 구현하지 못했습니다.

### 3. html (미완)
* put 기능이 html과 연동이 되질 않아 postman에는 가능하나, 웹 사이트에서의 작동은 구현하지 못했습니다.

## API 명세서- 일정 관리 시스템
#### 노션 API 명세서 URL : https://giddy-ox-904.notion.site/1114bd51dfda80d18cecd33516e3ea81?v=d519c9f6c1514ce3802b4638bea4c258
### 1. 일정 생성
* URL: /api/schedule
* Method: POST
* Request Body
* 
json<br/>
{ <br/>
"username": "홍길동",<br/>
"password": "비밀번호",<br/>
"title": "제목",<br/>
"contents": "내용"<br/>
}

* Response:
201 Created<br/>
json<br/>
{<br/>
"id": 1,<br/>
"username": "홍길동",<br/>
"password": "비밀번호",<br/>
"title": "제목",<br/>
"contents": "내용”,<br/>
"createAt": "등록 시간",<br/>
"updateAt": "변경된 시간"<br/>
}

### 2. 일정 조회
* URL: /api/schedule
* Method: GET
* Response:
200 OK<br/>
json<br/>
[<br/>
{<br/>
"id": 1,<br/>
"username": "홍길동",<br/>
"title": "제목",<br/>
"contents": "내용”,<br/>
"createAt": "등록 시간",<br/>
"updateAt": "변경된 시간"<br/>
}<br/>
]

### 3. 일정 수정
* URL: /api/schedule/{id}
* Method: PUT
* Request Body:<br/>
json<br/>
{<br/>
"username": "홍길동",<br/>
"password": "비밀번호",<br/>
"title": "제목",<br/>
"contents": "내용"<br/>
}

* Response:
200 OK<br/>
json<br/>
{<br/>
"id": 1,<br/>
"username": "홍길동",<br/>
"password": "비밀번호",<br/>
"title": "제목",<br/>
"contents": "내용”,<br/>
"updateAt": "변경된 시간"<br/>
}

### 4. 일정 삭제
* URL: /api/schedule/{id}
* Method: DELETE
* Response:
204 No Content
