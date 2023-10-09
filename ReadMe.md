## 요구사항
### 1. 채용공고를 등록합니다.
- `POST: /job-postings`
- 입력 필수값:
  - 회사_id
  - 채용포지션
  - 채용보상금
  - 채용내용
  - 사용기술
### 2. 채용공고를 수정합니다.
- `PATCH: /job-postings/{jobPostingId}`
- 입력 필수값
  - 채용공고_id (패스 파라미터)
- 입력 선택값
  - 채용포지션
  - 채용보상금
  - 채용내용
  - 사용기술
### 3. 채용공고를 삭제합니다.
- `DELETE: /job-postings/{jobPostingId}`
- 입력 필수값
  - 채용공고_id (패스 파라미터)
### 4. 채용공고 목록을 가져옵니다.<br/>
#### 4-1. 사용자는 채용공고 목록을 아래와 같이 확인할 수 있습니다.
  - `GET: /job-postings`
  - 출력값: 
    - 채용공고 엔티티
      - 채용공고_id
      - 채용포지션
      - 채용보상금
      - 사용기술
    - 회사 엔티티
      - 회사명
      - 국가
      - 지역
#### 4-2. 채용공고 검색 기능 구현(선택사항 및 가산점요소).
  - `GET: /job-postings?search="검색조건"`
  - 검색조건이 있는 경우
    - 검색 조건에 해당하는 응답을 줍니다.
  - 검색조건이 없는 경우
    - 모든 리스트를 검색하여 응답을 줍니다.
  - 입력 선택값
    - 검색조건
### 5. 채용 상세 페이지를 가져옵니다.
- `GET: /job-postings/{jobPostingId}`
- 해당 회사가 올린 다른 채용공고 가 추가적으로 포함됩니다
  - `채용공고 - 회사 - 회사가올린다른채용공고`를 조인으로 가져와서 응답해준다.
### 6. 사용자는 채용공고에 지원합니다(선택사항 및 가산점요소).
- `POST: /job-postings/{jobPostingId}/apply`
- 필수 입력값
  - 채용공고_id (패스 파라미터)
  - 사용자_id: 사용자_id가 필요하기 때문에 사용자를 등록하는 api가 필요하다.

## 패키지 구조
```
.
├── PreOnboardingBackendApplication.java
├── company
│   ├── controller
│   │   └── CompanyController.java
│   ├── dto
│   │   ├── CompanyPost.java
│   │   └── CompanyResponse.java
│   ├── entity
│   │   └── Company.java
│   ├── mapper
│   │   └── CompanyMapper.java
│   ├── repository
│   │   └── CompanyRepository.java
│   └── service
│       ├── CompanyService.java
│       └── CompanyServiceImpl.java
├── exception
│   ├── BusinessLogicException.java
│   ├── ExceptionAdvice.java
│   ├── ExceptionCode.java
│   └── dto
│       └── ErrorResponse.java
├── jobposting
│   ├── controller
│   │   └── JobPostingController.java
│   ├── dto
│   │   ├── ApplyPost.java
│   │   ├── ApplyResponse.java
│   │   ├── JobPostingDetailResponse.java
│   │   ├── JobPostingPatch.java
│   │   ├── JobPostingPost.java
│   │   ├── JobPostingPostResponse.java
│   │   └── JobPostingResponse.java
│   ├── entity
│   │   ├── Apply.java
│   │   └── JobPosting.java
│   ├── mapper
│   │   ├── ApplyMapper.java
│   │   └── JobPostingMapper.java
│   ├── repositroy
│   │   ├── ApplyRepository.java
│   │   ├── JobPostingRepository.java
│   │   └── querydsl
│   │       ├── JobPostingRepositoryImpl.java
│   │       └── JobPostingRepositoryQueryDSL.java
│   └── service
│       ├── ApplyService.java
│       ├── ApplyServiceImpl.java
│       ├── JobPostingService.java
│       └── JobPostingServiceImpl.java
├── response
│   └── SingleResponse.java
├── user
│   ├── controller
│   │   └── UserController.java
│   ├── dto
│   │   └── UserResponse.java
│   ├── entity
│   │   └── User.java
│   ├── mapper
│   │   └── UserMapper.java
│   ├── repository
│   │   └── UserRepository.java
│   └── service
│       ├── UserService.java
│       └── UserServiceImpl.java
└── validator
    ├── NotSpace.java
    └── NotSpaceValidator.java
```

## ERD
<img width="649" alt="스크린샷 2023-10-09 오전 12 04 26" src="https://github.com/qushe8r/wanted-pre-onboarding-backend/assets/115606959/1d7de9a2-6a07-419c-9f06-69a37e658e28">