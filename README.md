## 개요

- Spring Security를 이용하여 간단한 form login, Oauth2 login 기능을 개발하였습니다.

### 빌드 & 실행

```bash
./gradlew clean build

java -jar build/libs/spring-security-0.0.1-SNAPSHOT.jar --GOOGLE_CLIENT_ID=${GOOGLE_CLIENT_ID} --GOOGLE_CLIENT_SECRET=${GOOGLE_CLIENT_SECRET}
```