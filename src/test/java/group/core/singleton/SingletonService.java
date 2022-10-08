package group.core.singleton;

public class SingletonService {
    
    //자기 자신을 내부에 private으로 하나 생성해서 가지고 있게 아래와 같이 new 키워드로 생성해서 static 변수로 선언해줌
    //[주의] 이 때 static임!
    //static으로 해주면 클래스 단계로 올라감, 메모리에 단 하나만 존재하게 됨
    //실행시 jvm 메모리가 뜰 때, static 영역에 자기 자신(SingletonService 객체)를 생성해서 아예 넣어둠
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }
    
    //나는 지금 하나만 만들어지게 하고 싶음
    //그래서 위에서 static 변수를 통해 현재 메모리에 static 변수로 하나만 올라가게 해둠
    //따라서 생성자는 private으로 선언해서 생성자를 통해 다른 데서 생성하는 것을 막음
    private SingletonService(){}
    

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    
    
}
