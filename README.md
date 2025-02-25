# project-assignment1
#### 계산기 구현
#### 
## 개발 환경

#### Build : Gradle 8.8
#### Language : Java 17
#### Dependencies : Junit5, Mockito



## 실행 방법

#### com.assignment.AppInitializer 의 main method 실행
#### gradle build 후 프로젝트 하위의 build/lib/project-assignment1.jar 를 실행 (실행 가능 jar)

## 통합 테스트 방법

#### 실행 후 콘솔에서 수식 (예: 1.1 + 2.34) 입력하며 테스트
#### 콘솔에서 "exit" 입력하고 전송하면 애플리케이션 종료

## 사양

#### 사칙연산(+,-,*,/)이 가능한 간단한 계산기 기능 구현
#### 새로운 연산이 추가되기 용이한 구조로 구현


## 제약사항

#### 모든 요청과 응답은 표준 입출력으로 처리.
#### 수식 입력 횟수 제한 - 기본값 : 1000회