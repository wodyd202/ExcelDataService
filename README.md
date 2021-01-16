ExcelDataService는 엑셀 내부의 데이터를 <strong>개발자가 엑셀 데이터가 바인딩될 클래스(entity 혹은 dto 등 ExcelDataObject 어노테이션이 지정된 클래스) 내부의 어노테이션으로 지정한 형식과
사용자가 입력한 메타정보</strong>에 맞게 일관된 작업을 진행할 수 있도록 제작하였습니다.<br>
좀더 일관성있게 처리해보고자 입력받은 엑셀의 헤더(윗 부분)와 데이터 바디 부분의 양식을 체크하는 로직을 삽입했고 엑셀의 바디 데이터와 바인딩될 타입이 맞지 않을 경우
 Exception 처리를 하였습니다.

<h1>ExcelDataService 사용법</h1>
<ul>
<ol>ExcelDataReadService는 ExcelDataObject가 정의된 클래스의 ExcelColum 어노테이션 정보를 통해 데이터를 바인딩합니다.</ol><br>
<ol>ExcelDataReadService를 사용하기 위해서 반드시 ExcelDataReader<T> 와 ExcelFormValidator를 생성자를 통해 주입시켜주어야합니다.</ol>
<br>
<ol><strong>* String,int,double,Date 데이터 타입을 지원합니다.</strong></ol>
</ul>
<br><br>
<strong>-- 바인딩될 데이터 예시</strong>
<br>
* excelColum의 name 속성은 엑셀 헤더 컬럼을 의미합니다.
<br>
* 반드시 삽입되야할 데이터라면 isRequired를 true를 지정하면 됩니다.
<br><br>
<img src="https://user-images.githubusercontent.com/77535935/104814882-7518f880-5854-11eb-91aa-1e24c1d1f6a9.JPG">
<br><br>
<strong>-- Config 예시</strong>
<br><br>
<img src="https://user-images.githubusercontent.com/77535935/104814788-00de5500-5854-11eb-9f4a-0898dd5f230e.JPG">
<br><br>
<strong>-- 사용자 입력 예시</strong>
<br><br>
<img src="https://user-images.githubusercontent.com/77535935/104814919-c0cba200-5854-11eb-82b6-06c461bfa4d5.JPG">
<br><br>
<strong>-- 서비스 호출 예시</strong>
<br><br>
<img src="https://user-images.githubusercontent.com/77535935/104814968-1142ff80-5855-11eb-94f1-b60aa3a6631e.JPG">
<br><br>
<br><br>
-- UML
<img src="https://user-images.githubusercontent.com/77535935/104815214-819e5080-5856-11eb-81fa-0a8606ec5917.JPG">
