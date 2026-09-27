# pcbang

생활코딩 Java 강좌를 따라 만든 **PC방 관리 프로그램** (Java Swing + Maven + H2/MySQL).

## 참고 자료

- 생활코딩 강좌: https://opentutorials.org/module/987
- 영상: https://www.youtube.com/watch?v=3QoCOVajx74

## 구조

| 경로 | 내용 |
|---|---|
| `src/main/Main.java` | 진입점 |
| `src/view/` | 로그인 · 관리 화면 · 좌석 패널 (Swing) |
| `src/controller/` | PC 서버 백그라운드 |
| `src/dao/` | 로그인 DAO · H2 DB 초기화 |
| `src/asset/` | DB 커넥션 관리 · 설정 |
| `src/chat/` | 채팅 서버/클라이언트 |
| `img/` | 화면 이미지 리소스 |
| `test/` | 리플렉션 · 이터레이터 실험 코드 |

## 빌드

```bash
mvn compile
```

Java 1.8 기준. 의존성은 `pom.xml` 참고 (mysql-connector-java, h2).
