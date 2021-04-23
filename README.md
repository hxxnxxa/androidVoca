<h1>안드로이드 버전 기본 단어장</h1>

> 개발환경
> + Android Studio 4.1.2</br>
> + MacBook Pro (Retina, 13-inch, Early 2015) Big Sur 11.2</br>

</br>

> 소스코드 설명
> + Voca.java (생성자 부분, 객체 초기화를 통해 객체를 사용할 준비하기)
> ```
> public class Voca {
>    String eng;
>    String kor;
>
>    public Voca(String eng, String kor) {
>        this.eng = eng;
>        this.kor = kor;
>    }
> }
> ```
</br>
> + Storage.java (위에서 선언한 Voca객체를 담을 ArrayList 선언)
> ```
> public class Storage {
>    public static ArrayList<Voca> vocaArr = new ArrayList<>();
> }
> ```
> 
> + EditActivity.java (단어 추가, 수정, 삭제하는 부분)
> ```
> /* Voca객체를 담은 ArrayList 출력하는 부분 */
> 
> ArrayList<String> arr = new ArrayList<>();
> ArrayAdapter adapter;
> //ArrayList객체를 ListView에 띄우기 위해 ArrayAdapter 선언
> 
> @Override
>     public void onCreate(Bundle savedInstanceState) {
>       super.onCreate(savedInstanceState);
>       setContentView(R.layout.activity_edit);
>       
>       adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
>       //adapter객체에 ArrayList 연결
>       //android.R.layout.simple_list_item_1 : 한 줄에 하나의 아이템(Voca객체)만 보여주는 레이아웃 파일
>       //여기서의 this는 데이터가 저장되어있는 ArrayList 객체
>       
>       lvContent.setAdapter(adapter);
>       //ListView에 adapter객체 연결
>}
>```
> 그 외 소스코드는 자바 기초 문법이기때문에 Readme.md에서 제외하였습니다. 코드 브라우저로 확인 해 주세요. :)

</br>

> 이슈
> + 단어장 편집에서 입력 버튼을 누르지 않으면 Voca객체가 보이지 않음, 입력버튼을 눌러야만 Voca객체 불러오기 성공
> + 단어장 편집에서 입력 버튼을 누를 때마다 Voca객체가 clear되지 않음

