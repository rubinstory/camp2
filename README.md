#### 2021 KAIST 몰입캠프 겨울학기 2주차 과제
###### KAIST 전산학부 김서현, 부산대학교 정보컴퓨터공학부 이준영
# CATENA
#### 인플루언서 에이전시
## 프로젝트 소개
![main_img](https://images.velog.io/images/rubinstory/post/29661e7b-05cb-4162-876a-1a198c78129c/first.png)
## 과제 소개
2주차 과제의 경우 데이터베이스와 백엔드를 구현하고 이와 통신할 수 있는 모바일 서비스를 개발하는 것이다.
우리 조가 개발한 내용은 아래와 같다.
>
인스타그램, 유튜브 등에서 활동하는 인플루언서들의 프로필과 포트폴리오를 확인하고 소속사를 통해 광고 등의 계약을 체결할 수 있는 플랫폼
이번 프로젝트는 프론트와 백엔드로 나누어서 작업했다.
## Back - End
### Django-Rest-Framework
***
이번 프로젝트에서는 "Django-Rest-Framework"를 사용했다. 앱은 accounts, agency, contract 3개로 구분되어 있으며, accounts는 사용자, agency는 인플루언서, contract는 사용자와 인플루언서간의 계약을 model로 가진다. 

#### Models
다음은 각 앱에 대한 models를 나타낸 코드이다.
* accounts 


    accounts의 model은 "BaseUserManager"와 "AbstractBaseUser"Custom User를 사용했다. 
    ~~~python
    USERNAME_FIELD = 'username'
    ~~~
    을 지정하여, username으로 accounts를 구분할 수 있도록 구성했다. 


* agency
    ~~~ python
    class Influencer(models.Model):
        first_name = models.CharField(max_length = 50)
        last_name = models.CharField(max_length = 50)
        age = models.IntegerField()
        height = models.IntegerField()
        weight = models.IntegerField()
        country = models.CharField(max_length = 100)
        description = models.TextField()
        producer = models.ForeignKey(User, null = True, on_delete = models.CASCADE)
    ~~~

* contract
    ~~~python
    class Contract(models.Model):
        signature = models.ImageField(blank = True, upload_to = "" )
        influencer = models.ForeignKey(Influencer, null = True, related_name = 'contract', on_delete = models.CASCADE)
        user = models.ForeignKey(User, null = True, related_name = 'contract', on_delete = models.CASCADE)
    ~~~

### Serializers
serializers는 queryset이나 model instanse를 JSON으로 쉽게 변환할 수 있다. 이는 Rest Framework에서 기본적으로 제공해주는 기능이다.
각각의 serializer의 field는 다음과 같다.

~~~python
    class Meta:
        model = User
        fields = ['id','is_admin',  'username', 'email', 'password', 'profile_image', 'contract',]

    class Meta: 
        model = Influencer
        fields = ['id', 'first_name', 'last_name', 'age', 'height', 'weight', 'country', 'description', 'producer', 'image', 'video', 'contract',]

    class Meta:
        model = Contract
        fields = ['signature','user', 'influencer',]


    class Meta:
        model = Image_Portfolio
        fields = ['influencer','image']


    class Meta:
        model = Video_Portfolio
        fields = ['influencer','video']
~~~

### url
    admin page: admin/
    user list: accounts/
    user list by pk: accounts/<pk>
    influencer list: agency/Influencers
    contract list: contract/Contract
    access&refresh token viewer: token/
    refresh token: token/refresh/


### SQLite3
***
이번 프로젝트에서는 Database로 "Django-Rest-Framework"에서 기본으로 제공하고 있는 SQLite3를 사용했다. 

### Schema
다음은 이번 프로젝트의 schema와 관계를 시각화한 이미지이다. 
Entity는 "accounts_user, agency_influencer, contract_contract, agency_video_portfolio, agency_image_portfolio"가 있다. 

* account_user : 앱을 이용하는 사용자
* agency_influencer : agency 소속 인플루언서
* contract_contract: user와 agency간의 계약 (user와 influencer를 FK로 받음)
* agency_video_portfolio: 인플루언서의 비디오 포트폴리오 (influencer를 FK로 받음)
* agency_video_portfolio: 인플루언서의 사진 포트폴리오 (influencer를 FK로 받음)

![schema](https://user-images.githubusercontent.com/87957569/148932043-0b6bee9f-7f5b-4d38-96bf-efd448d524cd.png)

## Front - End
### Communication
***
이번 프로젝트에서는 서버와의 통신을 REST API를 기반으로 설계했다. 따라서 클라이언트(안드로이드 어플리케이션)에서도 REST 방식으로 통신을 해야한다.

그래서 REST API를 지원하는 Retrofit 라이브러리를 사용했다. Retrofit은 HTTP URI(Uniform Resource Identifier)를 통해 리소스를 명시하고, CRUD Operation을 적용한 것이다. 

```kotlin
private val retrofit by lazy { // Retrofit Initialize Example
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }
```

```kotlin
    @FormUrlEncoded // POST Request Query Example
    @POST("/users/token/verify/")
    fun verify_access_token(@Field("token") token: String ): Call<Token>

    @GET("/agency/Influencers/") // GET Request Query Example
    fun get_influencers(): Call<List<Influencer>>
```
### Design Pattern
***
웹서버와 통신을 해야하는 부분이 많기 때문에, 프로젝트 초창기에 MVVM 디자인 패턴을 적용한 설계를 진행했다.
![MVVMTITLE](https://user-images.githubusercontent.com/37971925/148937982-26361698-e908-4040-93d9-99ea9351a4a6.jpg)

*MVVM 패턴 예시*
<br>

MVVM 패턴은 마치 블랙박스처럼 작동하는데, 안드로이드의 Activity나 Fragment에 통신 모듈을 종속시키는게 아니라 외부 모듈로 만듦으로써 코드 구조를 명확하게 해준다.

```kotlin
data class Influencer ( // Data Model Example
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("country") val country: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val imageList: List<Image>,
    @SerializedName("video") val videoList: List<Video>,
) 
```

```kotlin
fun getInfluencers() { // View Model Function Example
        viewModelScope.launch {
           influencerRepository.getInfluencers().enqueue(object: Callback<List<Influencer>> {
               override fun onResponse(
                   call: Call<List<Influencer>>,
                   response: Response<List<Influencer>>
               ) {
                   if (response.isSuccessful) {
                       influencerList.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<List<Influencer>>, t: Throwable) {
                   Log.e("INFLUENCER", t.message.toString())
               }

           })
        }
    }
```


<br>

### Authentication & Signup
***
로그인의 경우 ID/PW 전송을 통한 로그인과 JWT(JSON Web Token) 기반의 토큰 인증방식 2가지로 구현했다. 웹서버에는 사용자가 로그인을 하면 Access와 Refresh, 두 종류의 토큰이 생성되고 응답으로 받는다. 이 중 Access 토큰을 저장하여 이후 자동 로그인을 위해 사용한다. 

```kotlin
@FormUrlEncoded // Auto - Login Query Example
    @POST("/users/token/verify/")
    fun verify_access_token(@Field("token") token: String ): Call<Token>
```
웹서버에서 자동 로그인을 위한 별도의 쿼리를 만들었으며, 클라이언트에서 POST 방식으로 전송하여 인증했다. 인증에 성공할 경우  HTTP Response Code 200을 수신하고 자동 로그인이 유지되며, 인증이 만료된 경우 로그인 페이지로 이동하게 된다. 원래는 Refresh 토큰도 이용하여야 하나, 일정이 빠듯하여 이 부분은 추후 구현 과제로 남겨두었다.

![2](https://user-images.githubusercontent.com/37971925/148938048-b3fbb609-6ab0-43ed-b4ed-dc8b7ad6bd51.png)

*로그인 및 회원가입 페이지*
<br>


### Influencer Detail & Contract
***
메인 화면에서 인플루언서의 프로필 카드를 선택하면 해당 인플루언서의 정보 및 포트폴리오를 조회할 수 있는 페이지로 이동할 수 있다. 
![4](https://user-images.githubusercontent.com/37971925/148938129-c4d649ec-999c-474c-9a1d-119dbd58108a.png)


메인 화면의 경우 ViewPager2에 Vertical Scroll Option을 더해 구현하였으며, 클릭 시 해당 모델의 정보를 확인할 수 있다. 그리고 Contract 버튼을 클릭할 경우 계약서를 확인할 수 있으며, 서명 후 Accept를 눌러 계약을 체결할 수 있다.

![5](https://user-images.githubusercontent.com/37971925/148938083-d49127b2-4073-44e4-886f-3384232f5ad2.jpg)

체결한 계약들은 사용자 계정 페이지에서 Contract History 버튼을 눌리면 확인할 수 있다.

![7](https://user-images.githubusercontent.com/37971925/148938144-a871d38e-2233-4437-823b-caf7f19da3ea.jpg)
