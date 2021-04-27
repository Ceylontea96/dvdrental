# 오라클 JDBC 라이브러리(ojdbc.jar)
- 파일 경로 'C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib'
- 프로젝트 src/main/webapp/WEB-INF/lib에 ojdbc6.jar 복사
- build.gradle에 아래 코드 추가
```groovy
//라이브러리 의존성 관리(라이브러리 다운로드 설정, 로딩 설정)
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine'

    //오라클 jdbc 라이브러리 설정
    compile fileTree(dir: '/src/main/webapp/WEB-INF/lib', includes: ['*.jar'])
}
```
-build.gradle에 spring core 추가

//스프링 코어 라이브러리

-implementation 'org.springframework:spring-core:5.2.9.RELEASE'