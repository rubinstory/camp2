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



