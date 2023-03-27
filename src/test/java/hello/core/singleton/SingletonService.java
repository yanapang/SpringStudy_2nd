package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    //SingletonService를 Spring서버 시작시 1회 생성하여 instance에 저장.
    //이후 생성을 막아 외부에서 다시 new 를 통해 생성되지 못하게 막음.
    //싱글톤 적용.

    public static SingletonService getInstance(){//조회시 사용
        return instance;
    }

    private SingletonService(){
        //외부 생성을 막기 위해 private으로 생성자 생성.
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
