<h1>안드로이드 버전 기본 단어장</h1>

> 개발환경
> + Android Studio 4.1.2</br>
> + MacBook Pro (Retina, 13-inch, Early 2015) Big Sur 11.2</br>

</br></br>

> 소스코드 설명
> + Voca.java (생성자 부분, 객체 초기화를 통해 사용할 준비하기)
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
> 
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
>```
> /* Voca객체에 데이터 추가하는 부분 */
> 
> private void show() {
>        arr.clear();
>        //arr객체가 표시될 때 중복을 피하기 위해 clear시켜주기
>        
>        for (int i = 0; i <Storage.vocaArr.size() ; i++) {
>            Voca  v = Storage.vocaArr.get(i);
>            arr.add(v.eng + " : " + v.kor);
>        }
>  
>        adapter.notifyDataSetChanged();
>        //adapter객체에 변경 내용을 반영시켜주기
>}
> ```
> 
>
>
>


> 이슈

